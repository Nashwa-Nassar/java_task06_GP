# Student Management System

**DEPI – Software Testing Track | Part 01: Java Fundamentals**
Instructor: Mina Younan — Session 11, Task GP (A)

A Java Maven console application that lets an administrator manage
students, courses, instructors, grades, and attendance for a small
department — built around inheritance, polymorphism, and
encapsulation as required by the assignment.

## 1. Overview

The system models a university department where:
- **Instructors** (full-time or part-time) teach **courses**.
- **Students** (undergraduate or graduate) enroll in courses.
- Each enrollment can produce a **grade** and a series of
  **attendance** records.
- A central `StudentManagementSystem` service is the single place an
  administrator adds, edits, and deletes student and course records,
  and records/calculates grades and attendance.

## 2. Requirements traceability

| Requirement | Where it's satisfied |
|---|---|
| Classes for students, courses, instructors, and grades | `Student`, `Course`, `Instructor`, `Grade` (plus `Attendance` for the attendance-tracking part of the brief) |
| Inheritance / polymorphism (different types of students or instructors) | `Student` → `UndergraduateStudent` / `GraduateStudent`; `Instructor` → `FullTimeInstructor` / `PartTimeInstructor`. Both hierarchies share a common `Person` base |
| Encapsulation | Every model field is `private`, exposed only through getters/setters; the master record lists live only inside `StudentManagementSystem` |
| Add / edit / delete student records | `addStudent()`, `editStudent()`, `deleteStudent()` |
| Course management | `addCourse()`, `editCourse()`, `deleteCourse()` |
| Record and calculate grades | `recordGrade()`, `calculateGPA()` (credit-weighted, 4.0 scale) |

## 3. Class design

### Model (`com.depi.sms.model`)

- **`Person`** (abstract) — `id`, `name`, `email`; abstract `getRole()`.
- **`Student`** (abstract, extends `Person`) — `enrolledCourses` (`List<Course>`), `grades` (`List<Grade>`); abstract `getStudentType()`.
  - **`UndergraduateStudent`** — adds `yearLevel`; `getStudentType()` → `"Undergraduate"`.
  - **`GraduateStudent`** — adds `researchArea`; `getStudentType()` → `"Graduate"`.
- **`Instructor`** (abstract, extends `Person`) — `department`; abstract `getEmploymentType()`.
  - **`FullTimeInstructor`** — adds `annualSalary`; `getEmploymentType()` → `"Full-Time"`.
  - **`PartTimeInstructor`** — adds `hourlyRate`; `getEmploymentType()` → `"Part-Time"`.
- **`Course`** — `id`, `name`, `credits`, `instructor` (`Instructor`), `enrolledStudents` (`List<Student>`).
- **`Grade`** — `student` (`Student`), `course` (`Course`), `score`; `getLetterGrade()` and `getGradePoints()` (4.0-scale conversion).
- **`Attendance`** — `student`, `course`, `date`, `status` (`AttendanceStatus`: `PRESENT` / `ABSENT` / `LATE`).

**Composition**, throughout: `Course` has-a `Instructor` and a `List<Student>`; `Student` has-a `List<Course>` and a `List<Grade>`; `Grade` and `Attendance` each has-a `Student` and a `Course`.

### Service (`com.depi.sms.system`)

- **`StudentManagementSystem`** — owns the master `List<Student>`,
  `List<Course>`, `List<Instructor>`, `List<Grade>`, and
  `List<Attendance>`. All CRUD, enrollment, grading, and attendance
  operations go through this one class, which is what keeps every
  list encapsulated and both sides of a relationship (e.g. a
  student's courses and a course's roster) in sync.

## 4. How grades and attendance are calculated

- `Grade.getLetterGrade()` converts a 0–100 score to A/B/C/D/F.
- `Grade.getGradePoints()` maps that letter to the standard 4.0 scale.
- `StudentManagementSystem.calculateGPA(student)` averages a
  student's grade points, weighted by each course's credit hours.
- `StudentManagementSystem.getAttendanceRate(student, course)`
  returns the percentage of recorded sessions marked `PRESENT`.

## 5. What `Main` demonstrates

1. Creates one full-time and one part-time instructor.
2. Creates two courses, one per instructor.
3. Creates one undergraduate and one graduate student.
4. Enrolls both students in courses.
5. Records grades and marks several attendance entries.
6. Edits a student's email and deletes a course, to exercise the
   management operations.
7. Prints a report: instructors, remaining courses, each student with
   their GPA, and attendance rates.

## 6. Project structure

```
student-management-system/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── depi/
                    └── sms/
                        ├── model/
                        │   ├── Person.java
                        │   ├── Student.java
                        │   ├── UndergraduateStudent.java
                        │   ├── GraduateStudent.java
                        │   ├── Instructor.java
                        │   ├── FullTimeInstructor.java
                        │   ├── PartTimeInstructor.java
                        │   ├── Course.java
                        │   ├── Grade.java
                        │   ├── Attendance.java
                        │   └── AttendanceStatus.java
                        ├── system/
                        │   └── StudentManagementSystem.java
                        └── Main.java
```

## 7. Requirements

- Java 11+
- Maven 3.6+

## 8. Build & run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.depi.sms.Main"

# or
mvn package
java -jar target/student-management-system.jar
```

## 9. Notes on scope

The assignment's hint suggests preparing a formal project report
(requirements, design, implementation, results) as practice, without
requiring it to be submitted. This README covers that ground at a
level appropriate for a repo — the requirements traceability table in
section 2 and the class design in section 3 double as a condensed
version of that report.

## License

Coursework project for the DEPI Software Testing Track — free to use
for learning and reference.
