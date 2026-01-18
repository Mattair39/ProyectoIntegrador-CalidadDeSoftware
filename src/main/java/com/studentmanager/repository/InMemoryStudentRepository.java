package com.studentmanager.repository;

import com.studentmanager.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación en memoria de StudentRepository. Esta clase sigue SRP
 * manejando solo
 * almacenamiento de datos. Puede ser reemplazada con otras implementaciones
 * (ej., DatabaseStudentRepository) sin
 * afectar el código cliente (LSP).
 */
public class InMemoryStudentRepository implements StudentRepository {
  private final List<Student> students;

  /** Crea un nuevo repositorio en memoria. */
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
    return students.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
  }

  @Override
  public int count() {
    return students.size();
  }

  @Override
  public boolean remove(String name, double grade) {
    if (name == null || name.trim().isEmpty()) {
      return false;
    }
    return students.removeIf(s -> s.getName().equalsIgnoreCase(name) && s.getGrade() == grade);
  }
}
