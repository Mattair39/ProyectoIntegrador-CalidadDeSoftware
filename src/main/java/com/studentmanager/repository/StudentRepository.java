package com.studentmanager.repository;

import com.studentmanager.model.Student;
import java.util.List;

/**
 * Repository interface for Student storage operations.
 * This interface follows OCP by allowing different implementations
 * without modifying client code.
 */
public interface StudentRepository {
    /**
     * Adds a student to the repository.
     *
     * @param student the student to add
     */
    void add(Student student);

    /**
     * Retrieves all students.
     *
     * @return list of all students
     */
    List<Student> findAll();

    /**
     * Finds a student by name.
     *
     * @param name the student's name
     * @return the student, or null if not found
     */
    Student findByName(String name);

    /**
     * Returns the total number of students.
     *
     * @return student count
     */
    int count();
}
