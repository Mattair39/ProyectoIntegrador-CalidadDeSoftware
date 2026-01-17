package com.studentmanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void testStudentCreation() {
        Student student = new Student("John Doe", 85.5);
        assertEquals("John Doe", student.getName());
        assertEquals(85.5, student.getGrade());
    }

    @Test
    void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Student(null, 85.5));
        assertThrows(IllegalArgumentException.class, () -> new Student("", 85.5));
    }

    @Test
    void testInvalidGrade() {
        assertThrows(IllegalArgumentException.class, () -> new Student("John", -1));
    }

    @Test
    void testSetGrade() {
        Student student = new Student("John Doe", 85.5);
        student.setGrade(90.0);
        assertEquals(90.0, student.getGrade());
    }

    @Test
    void testSetInvalidGrade() {
        Student student = new Student("John Doe", 85.5);
        assertThrows(IllegalArgumentException.class, () -> student.setGrade(-10));
    }
}
