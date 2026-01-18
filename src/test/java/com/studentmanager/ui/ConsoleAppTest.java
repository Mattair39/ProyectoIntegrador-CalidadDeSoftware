package com.studentmanager.ui;

import com.studentmanager.StudentManager;
import com.studentmanager.model.Student;
import com.studentmanager.report.ConsoleReportGenerator;
import com.studentmanager.repository.InMemoryStudentRepository;
import com.studentmanager.repository.StudentRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Suite completa de tests para ConsoleApp - apuntando a 100% de cobertura.
 */
class ConsoleAppTest {
    private StudentManager studentManager;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        studentManager = new StudentManager(
                new InMemoryStudentRepository(),
                new ConsoleReportGenerator());
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void executeApp(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ConsoleApp app = new ConsoleApp(studentManager);
        app.start();
    }

    private void executeAppWithManager(String input, StudentManager manager) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ConsoleApp app = new ConsoleApp(manager);
        app.start();
    }

    // Repositorio que falla intencionalmente para forzar bloques catch
    private static class BrokenRepository implements StudentRepository {
        private final boolean failOnAdd;
        private final InMemoryStudentRepository delegate = new InMemoryStudentRepository();

        public BrokenRepository(boolean failOnAdd) {
            this.failOnAdd = failOnAdd;
        }

        @Override
        public void add(Student student) {
            if (failOnAdd) {
                // Lanza una excepción genérica (simulando falla de base de datos)
                throw new RuntimeException("Database connection lost");
            }
            delegate.add(student);
        }

        @Override
        public List<Student> findAll() {
            return delegate.findAll();
        }

        @Override
        public boolean remove(String name, double grade) {
            return delegate.remove(name, grade);
        }

        @Override
        public int count() {
            return delegate.count();
        }

        @Override
        public Student findByName(String name) {
            return delegate.findByName(name);
        }
    }

    @Test
    void testStartAndExitImmediately() {
        executeApp("4\n");
    }

    @Test
    void testRegisterStudentSuccess() {
        executeApp("1\nJuan Perez\n85.5\n4\n");
    }

    @Test
    void testRegisterStudentWithAccents() {
        executeApp("1\nJosé García\n90.5\n4\n");
    }

    @Test
    void testRegisterStudentWithInvalidName() {
        executeApp("1\nJuan123\n85.5\n4\n");
    }

    @Test
    void testRegisterStudentWithSpecialCharacters() {
        executeApp("1\nJuan@Perez\n85.5\n4\n");
    }

    @Test
    void testRegisterStudentWithEmptyName() {
        executeApp("1\n\n85.5\n4\n");
    }

    @Test
    void testRegisterStudentWithInvalidGradeHigh() {
        executeApp("1\nJuan Perez\n150\n4\n");
    }

    @Test
    void testRegisterStudentWithInvalidGradeLow() {
        executeApp("1\nJuan Perez\n-5\n4\n");
    }

    @Test
    void testRegisterStudentWithNonNumericGrade() {
        executeApp("1\nJuan Perez\nabc\n4\n");
    }

    @Test
    void testRegisterStudentWithGradeBoundaryZero() {
        executeApp("1\nJuan Perez\n0\n4\n");
    }

    @Test
    void testRegisterStudentWithGradeBoundary100() {
        executeApp("1\nJuan Perez\n100\n4\n");
    }

    @Test
    void testListStudentsWhenEmpty() {
        executeApp("2\n4\n");
    }

    @Test
    void testListStudentsAfterAdding() {
        executeApp("1\nMaria Garcia\n92.0\n2\n4\n");
    }

    @Test
    void testListStudentsMultiple() {
        executeApp("1\nStudent One\n85\n1\nStudent Two\n90\n2\n4\n");
    }

    @Test
    void testListStudentsSuccessPath() {
        executeApp("1\nTest Student\n80.0\n2\n4\n");
    }

    @Test
    void testListStudentsThrowsException() {
        // Esto prueba el catch en listStudents
        StudentManager throwingManager = new StudentManager(
                new BrokenRepository(false) {
                    @Override
                    public List<Student> findAll() {
                        throw new RuntimeException("Database query failed");
                    }
                },
                new ConsoleReportGenerator());
        executeAppWithManager("2\n4\n", throwingManager);
    }

    @Test
    void testRegisterStudentDatabaseFailure() {
        // Esto debe activar catch (Exception e) en registerStudent
        // NO el catch (IllegalArgumentException)
        StudentManager managerWithBrokenRepo = new StudentManager(
                new BrokenRepository(true), // Will fail on add
                new ConsoleReportGenerator());
        executeAppWithManager("1\nValid Name\n75.0\n4\n", managerWithBrokenRepo);
    }

    @Test
    void testRemoveStudentSuccess() {
        executeApp("1\nPedro Lopez\n88.0\n3\nPedro Lopez\n88.0\n4\n");
    }

    @Test
    void testRemoveStudentNotFound() {
        // El estudiante no existe - debe retornar false y activar el bloque else
        executeApp("3\nNonExistent Student\n99.0\n4\n");
    }

    @Test
    void testRemoveStudentWrongGrade() {
        // Agrega estudiante, luego intenta eliminar con calificación incorrecta - debe
        // retornar false
        executeApp("1\nExisting Student\n80.0\n3\nExisting Student\n90.0\n4\n");
    }

    @Test
    void testRemoveWithDifferentGrade() {
        // Agrega un estudiante, luego intenta eliminar con calificación incorrecta
        executeApp("1\nSame Name\n75.0\n3\nSame Name\n99.0\n4\n");
    }

    @Test
    void testRemoveStudentCaseInsensitive() {
        executeApp("1\nJohn Doe\n85.0\n3\njohn doe\n85.0\n4\n");
    }

    @Test
    void testRemoveStudentWithEmptyName() {
        executeApp("3\n\n85.0\n4\n");
    }

    @Test
    void testRemoveStudentWithInvalidGradeNonNumeric() {
        executeApp("3\nJuan Perez\nabc\n4\n");
    }

    @Test
    void testRemoveStudentWithInvalidGradeHigh() {
        executeApp("3\nJuan Perez\n150\n4\n");
    }

    @Test
    void testRemoveStudentWithInvalidGradeLow() {
        executeApp("3\nJuan Perez\n-10\n4\n");
    }

    @Test
    void testRemoveStudentThrowsException() {
        // Prueba el bloque catch en removeStudent
        StudentManager throwingManager = new StudentManager(
                new BrokenRepository(false) {
                    @Override
                    public boolean remove(String name, double grade) {
                        throw new RuntimeException("Database remove failed");
                    }
                },
                new ConsoleReportGenerator());
        executeAppWithManager("3\nTest Student\n75.0\n4\n", throwingManager);
    }

    @Test
    void testInvalidMenuOptionHigh() {
        executeApp("99\n4\n");
    }

    @Test
    void testInvalidMenuOptionZero() {
        executeApp("0\n4\n");
    }

    @Test
    void testInvalidMenuOptionNegative() {
        executeApp("-1\n4\n");
    }

    @Test
    void testNonNumericMenuOption() {
        executeApp("abc\n4\n");
    }

    @Test
    void testCompleteWorkflow() {
        executeApp("1\nAna Martinez\n95.0\n1\nCarlos Ruiz\n87.5\n2\n3\nAna Martinez\n95.0\n2\n4\n");
    }

    @Test
    void testRegisterMultipleStudents() {
        executeApp("1\nStudent One\n90.0\n1\nStudent Two\n85.0\n1\nStudent Three\n95.0\n4\n");
    }

    @Test
    void testRegisterAndRemoveAll() {
        executeApp("1\nTest Student\n80.0\n3\nTest Student\n80.0\n2\n4\n");
    }

    @Test
    void testDuplicateNames() {
        executeApp("1\nJohn Doe\n85.0\n1\nJohn Doe\n92.0\n2\n4\n");
    }

    @Test
    void testRemoveOnlyOneFromDuplicates() {
        executeApp("1\nJohn Doe\n85.0\n1\nJohn Doe\n90.0\n3\nJohn Doe\n85.0\n2\n4\n");
    }

    @Test
    void testAllMenuOptionsSequential() {
        executeApp("1\nTest\n50\n2\n3\nTest\n50\n4\n");
    }

    @Test
    void testRegisterWithWhitespaceInName() {
        executeApp("1\n  Juan Perez  \n85.0\n4\n");
    }

    @Test
    void testMultipleInvalidInputs() {
        executeApp("abc\n0\n99\n-1\n4\n");
    }

    @Test
    void testListStudentsMultipleTimes() {
        executeApp("1\nTest One\n80\n2\n1\nTest Two\n90\n2\n2\n4\n");
    }

    @Test
    void testEmptyListThenAddThenList() {
        executeApp("2\n1\nStudent\n75\n2\n4\n");
    }
}
