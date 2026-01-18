package com.studentmanager.report;

import com.studentmanager.model.Student;
import java.util.List;

/**
 * Implementación de consola de ReportGenerator. Esta clase sigue SRP manejando
 * solo formato de
 * salida a consola. Puede coexistir con otras implementaciones como
 * HTMLReportGenerator (OCP).
 */
public class ConsoleReportGenerator implements ReportGenerator {

  @Override
  public void generate(List<Student> students) {
    if (students == null || students.isEmpty()) {
      System.out.println("No students to display.");
      return;
    }

    System.out.println("=== Student Report ===");
    for (Student student : students) {
      System.out.println("Student: " + student.getName() + ", Grade: " + student.getGrade());
    }
    System.out.println("Total students: " + students.size());
  }
}
