package com.studentmanager.repository;

import com.studentmanager.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * In-memory implementation of StudentRepository.
 * This class follows SRP by only handling data storage.
 * It can be replaced with other implementations (e.g.,
 * DatabaseStudentRepository)
 * without affecting client code (LSP).
 */
public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students;

    /**
     * Creates a new in-memory repository.
     */
    public InMemoryStudentRepository() {
        this.students = new ArrayList<>();
    }

    @Override
    public void add(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        students.add(student);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students); // Return copy to prevent external modification
    }

    @Override
    public Student findByName(String name) {
        if (name == null) {
            return null;
        }
        return students.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {
        return students.size();
    }
}
