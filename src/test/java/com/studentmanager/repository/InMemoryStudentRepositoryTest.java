package com.studentmanager.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.studentmanager.model.Student;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryStudentRepositoryTest {
    private InMemoryStudentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryStudentRepository();
    }

    @Test
    void testAddStudent() {
        Student student = new Student("John Doe", 85.5);
        repository.add(student);
        assertEquals(1, repository.count());
    }

    @Test
    void testAddNullStudent() {
        assertThrows(IllegalArgumentException.class, () -> repository.add(null));
    }

    @Test
    void testFindAll() {
        repository.add(new Student("John Doe", 85.5));
        repository.add(new Student("Jane Smith", 90.0));

        List<Student> students = repository.findAll();
        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    void testFindByName() {
        Student john = new Student("John Doe", 85.5);
        repository.add(john);

        Student found = repository.findByName("John Doe");
        assertNotNull(found);
        assertEquals("John Doe", found.getName());
    }

    @Test
    void testFindByNameNotFound() {
        Student found = repository.findByName("Non Existent");
        assertNull(found);
    }

    @Test
    void testCount() {
        assertEquals(0, repository.count());
        repository.add(new Student("John Doe", 85.5));
        assertEquals(1, repository.count());
        repository.add(new Student("Jane Smith", 90.0));
        assertEquals(2, repository.count());
    }
}
