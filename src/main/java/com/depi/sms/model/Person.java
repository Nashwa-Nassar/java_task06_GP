package com.depi.sms.model;

/**
 * Abstract base class for anyone in the system with an identity -
 * students and instructors alike. Holds the encapsulated common
 * fields (id, name, email) and leaves getRole() abstract so
 * subclasses report what kind of person they are.
 */
public abstract class Person {

    private int id;
    private String name;
    private String email;

    protected Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /** "Student" or "Instructor". */
    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + " #" + id + ": " + name + " <" + email + ">";
    }
}
