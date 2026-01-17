package com.studentmanager;

import com.studentmanager.model.Student;
import com.studentmanager.report.ConsoleReportGenerator;
import com.studentmanager.report.ReportGenerator;
import com.studentmanager.repository.InMemoryStudentRepository;
import com.studentmanager.repository.StudentRepository;

/**
 * Main manager class that coordinates student operations.
 * This class follows SRP by only coordinating between components.
 * It delegates storage to StudentRepository and reporting to ReportGenerator.
 */
public class StudentManager {
    private final StudentRepository repository;
    private final ReportGenerator reportGenerator;

    /**
     * Creates a StudentManager with specified repository and report generator.
     * This constructor follows DIP (Dependency Inversion Principle) by depending
     * on interfaces rather than concrete implementations.
     *
     * @param repository      the student repository
     * @param reportGenerator the report generator
     */
    public StudentManager(StudentRepository repository, ReportGenerator reportGenerator) {
        this.repository = repository;
        this.reportGenerator = reportGenerator;
    }

    /**
     * Adds a student with the given name and grade.
     *
     * @param name  the student's name
     * @param grade the student's grade
     */
    public void addStudent(String name, double grade) {
        Student student = new Student(name, grade);
        repository.add(student);
        System.out.println("Student added.");
    }

    /**
     * Lists all students using the configured report generator.
     */
    public void listStudents() {
        reportGenerator.generate(repository.findAll());
    }

    /**
     * Main method demonstrating the usage.
     *
     * @param args command line arguments
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
