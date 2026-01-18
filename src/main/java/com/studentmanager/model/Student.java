package com.studentmanager.model;

/**
 * Representa un estudiante con nombre y calificación. Esta clase sigue SRP
 * manejando solo datos del estudiante.
 */
public class Student {
  private final String name;
  private double grade;

  /**
   * Crea un nuevo Estudiante.
   *
   * @param name  el nombre del estudiante
   * @param grade la calificación del estudiante
   * @throws IllegalArgumentException si el nombre es nulo o vacío, o la
   *                                  calificación es negativa
   */
  public Student(String name, double grade) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (grade < 0) {
      throw new IllegalArgumentException("Grade cannot be negative");
    }
    this.name = name;
    this.grade = grade;
  }

  /**
   * Obtiene el nombre del estudiante.
   *
   * @return el nombre
   */
  public String getName() {
    return name;
  }

  /**
   * Obtiene la calificación del estudiante.
   *
   * @return la calificación
   */
  public double getGrade() {
    return grade;
  }

  /**
   * Establece la calificación del estudiante.
   *
   * @param grade la nueva calificación
   * @throws IllegalArgumentException si la calificación es negativa
   */
  public void setGrade(double grade) {
    if (grade < 0) {
      throw new IllegalArgumentException("Grade cannot be negative");
    }
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "Student{name='" + name + "', grade=" + grade + "}";
  }
}
