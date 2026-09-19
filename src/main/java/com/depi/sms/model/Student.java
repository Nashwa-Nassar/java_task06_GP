package com.depi.sms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for every student. Extends Person and adds the
 * courses a student is enrolled in and the grades they've earned.
 * Concrete subclasses (UndergraduateStudent, GraduateStudent)
 * implement getStudentType(), which is what lets a single
 * List<Student> hold different kinds of students polymorphically.
 */
public abstract class Student extends Person {

    private List<Course> enrolledCourses;
    private List<Grade> grades;

    protected Student(int id, String name, String email) {
        super(id, name, email);
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    /** Adds a course to this student's own record. */
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    /** Removes a course from this student's own record. */
    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    /** Adds an already-created grade to this student's record. */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    /** "Undergraduate" or "Graduate" - implemented per concrete subclass. */
    public abstract String getStudentType();

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return getStudentType() + " Student #" + getId() + ": " + getName() + " <" + getEmail() + ">";
    }
}
