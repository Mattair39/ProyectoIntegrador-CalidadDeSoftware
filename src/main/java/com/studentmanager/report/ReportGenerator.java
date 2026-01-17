package com.studentmanager.report;

import com.studentmanager.model.Student;
import java.util.List;

/**
 * Interface for generating student reports.
 * This interface follows OCP by allowing different report formats
 * without modifying existing code.
 */
public interface ReportGenerator {
    /**
     * Generates a report from a list of students.
     *
     * @param students the list of students to report
     */
    void generate(List<Student> students);
}
