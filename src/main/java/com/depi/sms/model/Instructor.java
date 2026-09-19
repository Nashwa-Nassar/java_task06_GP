package com.depi.sms.model;

/**
 * Abstract base class for every instructor. Extends Person and adds
 * the department they belong to. Concrete subclasses
 * (FullTimeInstructor, PartTimeInstructor) implement
 * getEmploymentType(), demonstrating polymorphism the same way
 * Student's subclasses do.
 */
public abstract class Instructor extends Person {

    private String department;

    protected Instructor(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    /** "Full-Time" or "Part-Time" - implemented per concrete subclass. */
    public abstract String getEmploymentType();

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public String toString() {
        return getEmploymentType() + " Instructor #" + getId() + ": " + getName()
                + " (" + department + ") <" + getEmail() + ">";
    }
}
