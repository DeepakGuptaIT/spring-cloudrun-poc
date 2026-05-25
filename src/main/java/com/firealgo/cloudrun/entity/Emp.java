package com.firealgo.cloudrun.entity;

import java.util.Objects;

/**
 * Employee entity representing employee details.
 *
 * Equality is based only on employee ID.
 */
public class Emp {

    /**
     * Unique employee identifier.
     */
    private final int id;

    /**
     * Employee first name.
     */
    private final String firstName;

    /**
     * Employee last name.
     */
    private final String lastName;

    /**
     * Employee salary.
     */
    private final int salary;

    /**
     * Employee department.
     */
    private final String dept;

    /**
     * Constructs Employee object.
     *
     * @param id employee ID
     * @param firstName first name
     * @param lastName last name
     * @param salary salary
     * @param dept department
     */
    public Emp(int id,
               String firstName,
               String lastName,
               int salary,
               String dept) {

        this.id = id;
        this.firstName = Objects.requireNonNull(firstName, "firstName cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName cannot be null");
        this.dept = Objects.requireNonNull(dept, "dept cannot be null");
        this.salary = salary;
    }

    // -------------------------------------------------------------------------
    // GETTERS
    // -------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public String getDept() {
        return dept;
    }

    // -------------------------------------------------------------------------
    // UTILITY METHODS
    // -------------------------------------------------------------------------

    /**
     * Returns employee full name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Checks whether employee belongs to given department.
     */
    public boolean isInDept(String dept) {
        return this.dept.equalsIgnoreCase(dept);
    }

    /**
     * Checks whether employee salary is greater than given amount.
     */
    public boolean isSalaryGreaterThan(int amount) {
        return salary > amount;
    }

    // -------------------------------------------------------------------------
    // OBJECT METHODS
    // -------------------------------------------------------------------------

    /**
     * Equality based only on employee ID.
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Emp emp)) {
            return false;
        }

        return id == emp.id;
    }

    /**
     * Hashcode based only on employee ID.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    /**
     * Human-readable employee representation.
     */
    @Override
    public String toString() {

        return "Emp{" +
                "id=" + id +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + salary +
                ", dept='" + dept + '\'' +
                '}';
    }
}