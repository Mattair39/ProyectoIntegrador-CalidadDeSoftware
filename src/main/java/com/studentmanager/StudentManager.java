package com.studentmanager;

import com.studentmanager.model.Student;
import com.studentmanager.report.ConsoleReportGenerator;
import com.studentmanager.report.ReportGenerator;
import com.studentmanager.repository.InMemoryStudentRepository;
import com.studentmanager.repository.StudentRepository;

/**
 * Clase principal de gestión que coordina las operaciones de estudiantes. Esta
 * clase sigue SRP solo
 * coordinando entre componentes. Delega almacenamiento a StudentRepository y
 * reportes a
 * ReportGenerator.
 */
public class StudentManager {
  private final StudentRepository repository;
  private final ReportGenerator reportGenerator;

  /**
   * Crea un StudentManager con el repositorio y generador de reportes
   * especificados. Este constructor
   * sigue DIP (Principio de Inversión de Dependencias) dependiendo de interfaces
   * en lugar de
   * implementaciones concretas.
   *
   * @param repository      el repositorio de estudiantes
   * @param reportGenerator el generador de reportes
   */
  public StudentManager(StudentRepository repository, ReportGenerator reportGenerator) {
    this.repository = repository;
    this.reportGenerator = reportGenerator;
  }

  /**
   * Agrega un estudiante con el nombre y calificación dados.
   *
   * @param name  el nombre del estudiante
   * @param grade la calificación del estudiante
   */
  public void addStudent(String name, double grade) {
    Student student = new Student(name, grade);
    repository.add(student);
    System.out.println("Student added.");
  }

  /** Lista todos los estudiantes usando el generador de reportes configurado. */
  public void listStudents() {
    reportGenerator.generate(repository.findAll());
  }

  /**
   * Remueve un estudiante por nombre y calificación.
   *
   * @param name  el nombre del estudiante
   * @param grade la calificación del estudiante
   * @return true si fue removido, false en caso contrario
   */
  public boolean removeStudent(String name, double grade) {
    return repository.remove(name, grade);
  }

  /**
   * Método principal demostrando el uso.
   *
   * @param args argumentos de línea de comandos
   */
  public static void main(String[] args) {
    // Using dependency injection with concrete implementations
    StudentRepository repository = new InMemoryStudentRepository();
    ReportGenerator reportGenerator = new ConsoleReportGenerator();
    StudentManager sm = new StudentManager(repository, reportGenerator);

    sm.addStudent("John Doe", 85.5);
    sm.listStudents();
  }
}
