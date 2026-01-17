package com.studentmanager.model;

/**
 * Represents a student with a name and grade.
 * This class follows SRP by only handling student data.
 */
public class Student {
    private String name;
    private double grade;

    /**
     * Creates a new Student.
     *
     * @param name  the student's name
     * @param grade the student's grade
     * @throws IllegalArgumentException if name is null or empty, or grade is
     *                                  negative
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
     * Gets the student's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the student's grade.
     *
     * @return the grade
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Sets the student's grade.
     *
     * @param grade the new grade
     * @throws IllegalArgumentException if grade is negative
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
