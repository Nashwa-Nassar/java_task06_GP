package com.depi.sms;

import com.depi.sms.model.AttendanceStatus;
import com.depi.sms.model.Course;
import com.depi.sms.model.FullTimeInstructor;
import com.depi.sms.model.GraduateStudent;
import com.depi.sms.model.Instructor;
import com.depi.sms.model.PartTimeInstructor;
import com.depi.sms.model.Student;
import com.depi.sms.model.UndergraduateStudent;
import com.depi.sms.system.StudentManagementSystem;

import java.time.LocalDate;

/**
 * DEPI - Software Testing Track - Session 11 - Task GP (A)
 * Student Management System.
 *
 * Demo entry point: wires up instructors, courses and students,
 * exercises add/edit/delete, records grades and attendance, then
 * prints a small report - all through the StudentManagementSystem
 * service, using the model classes polymorphically throughout.
 */
public class Main {

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();

        // ---- 1) Instructors (two different employment types) ----
        Instructor drAmr = new FullTimeInstructor(1, "Dr. Amr Fathy", "amr.fathy@uni.edu", "Computer Science", 90000);
        Instructor engSara = new PartTimeInstructor(2, "Eng. Sara Adel", "sara.adel@uni.edu", "Computer Science", 45.0);
        sms.addInstructor(drAmr);
        sms.addInstructor(engSara);

        // ---- 2) Courses ----
        Course dataStructures = new Course(101, "Data Structures", 3, drAmr);
        Course javaLab = new Course(102, "Java Programming Lab", 2, engSara);
        sms.addCourse(dataStructures);
        sms.addCourse(javaLab);

        // ---- 3) Students (two different student types) ----
        Student nashwa = new UndergraduateStudent(201, "Nashwa Nassar", "nashwa@uni.edu", 3);
        Student omar = new GraduateStudent(202, "Omar Khaled", "omar@uni.edu", "Software Testing");
        sms.addStudent(nashwa);
        sms.addStudent(omar);

        // ---- 4) Enrollment ----
        sms.enrollStudentInCourse(nashwa, dataStructures);
        sms.enrollStudentInCourse(nashwa, javaLab);
        sms.enrollStudentInCourse(omar, javaLab);

        // ---- 5) Record grades ----
        sms.recordGrade(nashwa, dataStructures, 92);
        sms.recordGrade(nashwa, javaLab, 85);
        sms.recordGrade(omar, javaLab, 78);

        // ---- 6) Mark attendance ----
        sms.markAttendance(nashwa, javaLab, LocalDate.of(2026, 9, 1), AttendanceStatus.PRESENT);
        sms.markAttendance(nashwa, javaLab, LocalDate.of(2026, 9, 8), AttendanceStatus.PRESENT);
        sms.markAttendance(nashwa, javaLab, LocalDate.of(2026, 9, 15), AttendanceStatus.LATE);
        sms.markAttendance(omar, javaLab, LocalDate.of(2026, 9, 1), AttendanceStatus.ABSENT);
        sms.markAttendance(omar, javaLab, LocalDate.of(2026, 9, 8), AttendanceStatus.PRESENT);

        // ---- 7) Demonstrate edit / delete for student and course management ----
        sms.editStudent(202, null, "omar.khaled@uni.edu"); // update email only
        sms.deleteCourse(101); // remove Data Structures entirely

        // ---- 8) Report ----
        System.out.println("=== Instructors ===");
        for (Instructor instructor : sms.getInstructors()) {
            System.out.println("  " + instructor);
        }

        System.out.println();
        System.out.println("=== Courses ===");
        for (Course course : sms.getCourses()) {
            System.out.println("  " + course);
        }

        System.out.println();
        System.out.println("=== Students & GPA ===");
        for (Student student : sms.getStudents()) {
            System.out.println("  " + student);
            System.out.printf("    GPA: %.2f%n", sms.calculateGPA(student));
        }

        System.out.println();
        System.out.println("=== Attendance rate in Java Programming Lab ===");
        System.out.printf("  Nashwa: %.1f%%%n", sms.getAttendanceRate(nashwa, javaLab));
        System.out.printf("  Omar:   %.1f%%%n", sms.getAttendanceRate(omar, javaLab));
    }
}
