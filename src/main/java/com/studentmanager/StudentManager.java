package com.studentmanager;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<String> students = new ArrayList<>();
    private List<Double> grades = new ArrayList<>();

    public void addStudent(String s, double g) {
        students.add(s);
        grades.add(g);
        System.out.println("Student added.");
    }

    public void listStudents() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println("Student: " + students.get(i) + ", Grade: " + grades.get(i));
        }
    }

    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        sm.addStudent("John Doe", 85.5);
        sm.listStudents();
    }
}
