package com.studentmanager.report;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.studentmanager.model.Student;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleReportGeneratorTest {
    private ConsoleReportGenerator generator;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        generator = new ConsoleReportGenerator();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testGenerateWithStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe", 85.5));
        students.add(new Student("Jane Smith", 92.0));

        assertDoesNotThrow(() -> generator.generate(students));
        String output = outputStream.toString();

        // Verify output contains expected content
        assert output.contains("Student Report");
        assert output.contains("John Doe");
        assert output.contains("Jane Smith");
        assert output.contains("Total students: 2");
    }

    @Test
    void testGenerateWithEmptyList() {
        List<Student> students = new ArrayList<>();

        assertDoesNotThrow(() -> generator.generate(students));
        String output = outputStream.toString();

        assert output.contains("No students to display");
    }

    @Test
    void testGenerateWithNull() {
        assertDoesNotThrow(() -> generator.generate(null));
        String output = outputStream.toString();

        assert output.contains("No students to display");
    }

    @Test
    void testGenerateWithSingleStudent() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice Johnson", 95.0));

        assertDoesNotThrow(() -> generator.generate(students));
        String output = outputStream.toString();

        assert output.contains("Alice Johnson");
        assert output.contains("95.0");
        assert output.contains("Total students: 1");
    }
}
