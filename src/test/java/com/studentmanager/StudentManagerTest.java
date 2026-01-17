package com.studentmanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {
    private StudentManager studentManager;

    @BeforeEach
    void setUp() {
        studentManager = new StudentManager();
    }

    @Test
    void testAddStudent() {
        // Aquí se pueden agregar pruebas unitarias
        assertNotNull(studentManager);
    }

    @Test
    void testListStudents() {
        // Aquí se pueden agregar pruebas unitarias
        assertNotNull(studentManager);
    }
}
