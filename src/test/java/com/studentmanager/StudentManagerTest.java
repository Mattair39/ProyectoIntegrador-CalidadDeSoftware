package com.studentmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.studentmanager.report.ConsoleReportGenerator;
import com.studentmanager.repository.InMemoryStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentManagerTest {
    private StudentManager studentManager;
    private InMemoryStudentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryStudentRepository();
        studentManager = new StudentManager(repository, new ConsoleReportGenerator());
    }

    @Test
    void testAddStudent() {
        studentManager.addStudent("John Doe", 85.5);
        assertEquals(1, repository.count());
    }

    @Test
    void testListStudents() {
        studentManager.addStudent("John Doe", 85.5);
        studentManager.addStudent("Jane Smith", 90.0);

        assertNotNull(repository.findAll());
        assertEquals(2, repository.count());
    }

    @Test
    void testRemoveStudent() {
        studentManager.addStudent("John Doe", 85.5);
        assertEquals(1, repository.count());

        boolean removed = studentManager.removeStudent("John Doe", 85.5);
        assertTrue(removed);
        assertEquals(0, repository.count());
    }

    @Test
    void testRemoveNonExistentStudent() {
        studentManager.addStudent("John Doe", 85.5);

        boolean removed = studentManager.removeStudent("Jane Smith", 90.0);
        assertFalse(removed);
        assertEquals(1, repository.count());
    }

    @Test
    void testMainMethod() {
        // Test that main method executes without exceptions
        StudentManager.main(new String[] {});
    }
}
