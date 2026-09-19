package com.depi.sms.system;

import com.depi.sms.model.Attendance;
import com.depi.sms.model.AttendanceStatus;
import com.depi.sms.model.Course;
import com.depi.sms.model.Grade;
import com.depi.sms.model.Instructor;
import com.depi.sms.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The administrator-facing service that ties every model class
 * together: it owns the master lists of students, courses,
 * instructors, grades, and attendance records, and exposes the CRUD
 * and reporting operations an administrator needs.
 *
 * Encapsulation: every list here is private; callers only ever touch
 * the data through the methods below.
 */
public class StudentManagementSystem {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();
    private List<Attendance> attendanceRecords = new ArrayList<>();

    // ------------------------------------------------------------------
    // Student management: add, edit, delete
    // ------------------------------------------------------------------

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    /** Edits a student's name and/or email. Pass null to leave a field unchanged. */
    public boolean editStudent(int studentId, String newName, String newEmail) {
        Student student = findStudentById(studentId);
        if (student == null) return false;
        if (newName != null) student.setName(newName);
        if (newEmail != null) student.setEmail(newEmail);
        return true;
    }

    /** Deletes a student and un-enrolls them from every course they were in. */
    public boolean deleteStudent(int studentId) {
        Student student = findStudentById(studentId);
        if (student == null) return false;
        for (Course course : new ArrayList<>(student.getEnrolledCourses())) {
            course.removeStudent(student);
        }
        students.remove(student);
        return true;
    }

    public List<Student> getStudents() {
        return students;
    }

    // ------------------------------------------------------------------
    // Course management: add, edit, delete
    // ------------------------------------------------------------------

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course findCourseById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    /** Edits a course's name, credits and/or instructor. Pass null/-1 to leave a field unchanged. */
    public boolean editCourse(int courseId, String newName, Integer newCredits, Instructor newInstructor) {
        Course course = findCourseById(courseId);
        if (course == null) return false;
        if (newName != null) course.setName(newName);
        if (newCredits != null) course.setCredits(newCredits);
        if (newInstructor != null) course.setInstructor(newInstructor);
        return true;
    }

    /** Deletes a course and removes it from every enrolled student's record. */
    public boolean deleteCourse(int courseId) {
        Course course = findCourseById(courseId);
        if (course == null) return false;
        for (Student student : new ArrayList<>(course.getEnrolledStudents())) {
            student.dropCourse(course);
        }
        courses.remove(course);
        return true;
    }

    public List<Course> getCourses() {
        return courses;
    }

    // ------------------------------------------------------------------
    // Instructor management
    // ------------------------------------------------------------------

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    // ------------------------------------------------------------------
    // Enrollment (keeps both sides of the Student <-> Course link in sync)
    // ------------------------------------------------------------------

    public void enrollStudentInCourse(Student student, Course course) {
        student.enrollInCourse(course);
        course.addStudent(student);
    }

    // ------------------------------------------------------------------
    // Grades: record and calculate
    // ------------------------------------------------------------------

    /** Records a new grade for a student in a course. */
    public Grade recordGrade(Student student, Course course, double score) {
        Grade grade = new Grade(student, course, score);
        grades.add(grade);
        student.addGrade(grade);
        return grade;
    }

    /** Calculates a student's GPA on a 4.0 scale, weighted by each course's credits. */
    public double calculateGPA(Student student) {
        List<Grade> studentGrades = student.getGrades();
        if (studentGrades.isEmpty()) return 0.0;

        double totalPoints = 0;
        int totalCredits = 0;
        for (Grade grade : studentGrades) {
            int credits = grade.getCourse().getCredits();
            totalPoints += grade.getGradePoints() * credits;
            totalCredits += credits;
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    // ------------------------------------------------------------------
    // Attendance
    // ------------------------------------------------------------------

    public void markAttendance(Student student, Course course, LocalDate date, AttendanceStatus status) {
        attendanceRecords.add(new Attendance(student, course, date, status));
    }

    /** Percentage of recorded sessions where the student was marked PRESENT for that course. */
    public double getAttendanceRate(Student student, Course course) {
        int total = 0;
        int present = 0;
        for (Attendance record : attendanceRecords) {
            if (record.getStudent().equals(student) && record.getCourse().equals(course)) {
                total++;
                if (record.getStatus() == AttendanceStatus.PRESENT) present++;
            }
        }
        return total == 0 ? 0.0 : (present * 100.0) / total;
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }
}
