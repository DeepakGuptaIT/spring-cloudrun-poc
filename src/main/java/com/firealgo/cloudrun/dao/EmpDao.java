package com.firealgo.cloudrun.dao;


import com.firealgo.cloudrun.entity.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * In-memory DAO layer for Employee dataset.
 *
 * This class acts as a fake repository for learning
 * Java Streams API and Spring Boot concepts.
 */
@Repository
public class EmpDao {

    /**
     * Immutable employee dataset.
     */
    private static final List<Emp> EMPLOYEES = List.of(

            new Emp(1, "Deep", "Kumar", 8500, "IT"),
            new Emp(10, "Raj", "Kapoor", 7500, "HR"),
            new Emp(5, "Krishna", "Gopal", 5500, "HR"),
            new Emp(35, "Raj", "Kumar", 9500, "IT"),
            new Emp(25, "Asha", "Singh", 5500, "IT"),
            new Emp(45, "Alia", "Gupta", 6500, "FINANCE")
    );

    /**
     * Immutable employee dataset with duplicates.
     */
    private static final List<Emp> EMPLOYEES_WITH_DUPLICATES = List.of(

            new Emp(1, "Deep", "Kumar", 8500, "IT"),
            new Emp(10, "Raj", "Kapoor", 7500, "HR"),
            new Emp(5, "Krishna", "Gopal", 5500, "HR"),
            new Emp(5, "Krishna", "Gopal", 5500, "HR"),
            new Emp(35, "Raj", "Kumar", 9500, "IT"),
            new Emp(25, "Asha", "Singh", 5500, "IT"),
            new Emp(45, "Alia", "Gupta", 6500, "FINANCE"),
            new Emp(45, "Alia", "Gupta", 6500, "FINANCE")
    );

    /**
     * Returns all employees.
     */
    public List<Emp> allEmp() {

        return EMPLOYEES;
    }

    /**
     * Returns all employees including duplicates.
     */
    public List<Emp> allEmpWithDuplicates() {

        return EMPLOYEES_WITH_DUPLICATES;
    }
}