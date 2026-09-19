package com.depi.sms.model;

import java.time.LocalDate;

/**
 * A single attendance record: whether a Student was present, absent,
 * or late for a Course on a given date.
 * Composition: an Attendance has-a Student and has-a Course.
 */
public class Attendance {

    private Student student;
    private Course course;
    private LocalDate date;
    private AttendanceStatus status;

    public Attendance(Student student, Course course, LocalDate date, AttendanceStatus status) {
        this.student = student;
        this.course = course;
        this.date = date;
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getDate() {
        return date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return date + " - " + student.getName() + " - " + course.getName() + ": " + status;
    }
}
