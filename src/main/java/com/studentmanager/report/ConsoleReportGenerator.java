package com.studentmanager.report;

import com.studentmanager.model.Student;
import java.util.List;

/**
 * Console implementation of ReportGenerator.
 * This class follows SRP by only handling console output formatting.
 * It can coexist with other implementations like HTMLReportGenerator (OCP).
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
            System.out.println("Student: " + student.getName()
                    + ", Grade: " + student.getGrade());
        }
        System.out.println("Total students: " + students.size());
    }
}
