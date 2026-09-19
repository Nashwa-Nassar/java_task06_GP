package com.depi.sms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A course offered by the department, taught by one Instructor and
 * taken by zero or more Students.
 *
 * Composition: a Course has-a Instructor and has-a List<Student>
 * (its roster).
 */
public class Course {

    private int id;
    private String name;
    private int credits;
    private Instructor instructor;
    private List<Student> enrolledStudents;

    public Course(int id, String name, int credits, Instructor instructor) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.instructor = instructor;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /** Adds a student to this course's own roster. */
    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }

    /** Removes a student from this course's own roster. */
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return "Course #" + id + ": " + name + " (" + credits + " credits, taught by "
                + instructor.getName() + ") - " + enrolledStudents.size() + " student(s) enrolled";
    }
}
