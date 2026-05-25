
package com.firealgo.cloudrun.service;


import com.firealgo.cloudrun.dao.EmpDao;
import com.firealgo.cloudrun.entity.Emp;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Service class demonstrating Java Stream API operations
 * using Employee dataset.
 */
@Service
public class EmpService {

    private static final int DEFAULT_WELL_PAID_SALARY = 7000;
    private static final int LOW_SALARY_LIMIT = 6000;
    private static final int HIGH_SALARY_LIMIT = 8000;
    private static final int TOP_EMPLOYEE_LIMIT = 3;

    private final EmpDao empDao;

    public EmpService(EmpDao empDao) {
        this.empDao = empDao;
    }

    // -------------------------------------------------------------------------
    // INTERNAL STREAM HELPERS
    // -------------------------------------------------------------------------

    private Stream<Emp> empStream() {
        return empDao.allEmp().stream();
    }

    private Stream<Emp> duplicateEmpStream() {
        return empDao.allEmpWithDuplicates().stream();
    }

    // -------------------------------------------------------------------------
    // BASIC FETCH OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Returns all employees.
     */
    public List<Emp> getAllEmp() {

        return List.copyOf(empDao.allEmp());
    }

    /**
     * Returns all employees including duplicates.
     */
    public List<Emp> getAllEmpWithDuplicates() {

        return List.copyOf(empDao.allEmpWithDuplicates());
    }

    // -------------------------------------------------------------------------
    // FILTER + MAP OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Finds employees earning more than given salary.
     */
    public List<Emp> findWellPaidEmpList(int salary) {

        return empStream()
                .filter(emp -> emp.isSalaryGreaterThan(salary))
                .toList();
    }

    /**
     * Finds first names of employees earning more than given salary.
     */
    public List<String> findWellPaidEmpNameList(int salary) {

        return empStream()
                .filter(emp -> emp.isSalaryGreaterThan(salary))
                .map(Emp::getFirstName)
                .toList();
    }

    /**
     * Returns well-paid employees grouped by department.
     */
    public Map<String, List<Emp>> wellPaidEmpByDept(int salary) {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        filtering(
                                emp -> emp.isSalaryGreaterThan(salary),
                                toList()
                        )
                ));
    }

    /**
     * Returns well-paid employee first names grouped by department.
     */
    public Map<String, List<String>> wellPaidEmpFirstNameByDept(int salary) {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        filtering(
                                emp -> emp.isSalaryGreaterThan(salary),
                                mapping(Emp::getFirstName, toList())
                        )
                ));
    }

    // -------------------------------------------------------------------------
    // DISTINCT + SET OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Returns distinct department names.
     */
    public List<String> findDistinctDeptList() {

        return empStream()
                .map(Emp::getDept)
                .distinct()
                .sorted()
                .toList();
    }

    /**
     * Returns distinct departments as Set.
     */
    public Set<String> findDeptSet() {

        return empStream()
                .map(Emp::getDept)
                .collect(toSet());
    }

    /**
     * Returns distinct employees.
     */
    public List<Emp> findAllDistinctEmp() {

        return duplicateEmpStream()
                .distinct()
                .toList();
    }

    /**
     * Returns distinct employees as Set.
     */
    public Set<Emp> findAllEmpSet() {

        return duplicateEmpStream()
                .collect(toSet());
    }

    // -------------------------------------------------------------------------
    // SORTING OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Returns employee first names.
     */
    public List<String> findEmpFirstNameList() {

        return empStream()
                .map(Emp::getFirstName)
                .toList();
    }

    /**
     * Returns employee full names.
     */
    public List<String> findEmpFullNameList() {

        return empStream()
                .map(Emp::getFullName)
                .toList();
    }

    /**
     * Returns sorted employee first names.
     */
    public List<String> findEmpFirstNameSortedList() {

        return empStream()
                .map(Emp::getFirstName)
                .sorted()
                .toList();
    }

    /**
     * Returns distinct sorted employee first names.
     */
    public List<String> findEmpFirstNameSortedAndDistinctList() {

        return empStream()
                .map(Emp::getFirstName)
                .distinct()
                .sorted()
                .toList();
    }

    /**
     * Returns reverse sorted distinct employee first names.
     */
    public List<String> findEmpFirstNameReverseSortedAndDistinctList() {

        return empStream()
                .map(Emp::getFirstName)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    /**
     * Returns sorted distinct employee first names as TreeSet.
     */
    public Set<String> findEmpFirstNameSortedAndDistinctSet() {

        return empStream()
                .map(Emp::getFirstName)
                .collect(toCollection(TreeSet::new));
    }

    /**
     * Returns employees sorted by ID.
     */
    public List<Emp> findEmpSortedByIdList() {

        return empStream()
                .sorted(Comparator.comparingInt(Emp::getId))
                .toList();
    }

    /**
     * Returns employees reverse sorted by ID.
     */
    public List<Emp> findEmpReverseSortedByIdList() {

        return empStream()
                .sorted(Comparator.comparingInt(Emp::getId).reversed())
                .toList();
    }

    /**
     * Returns employees sorted by full name.
     */
    public List<Emp> findEmpSortedByFullNameList() {

        return empStream()
                .sorted(Comparator.comparing(Emp::getFullName))
                .toList();
    }

    /**
     * Returns distinct employees sorted by full name.
     */
    public List<Emp> findDistinctEmpSortedByFullNameList() {

        return duplicateEmpStream()
                .distinct()
                .sorted(Comparator.comparing(Emp::getFullName))
                .toList();
    }

    // -------------------------------------------------------------------------
    // JOINING OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Returns comma-separated employee full names.
     */
    public String findCommaSeparatedEmpFullNames() {

        return empStream()
                .map(Emp::getFullName)
                .collect(joining(", "));
    }

    // -------------------------------------------------------------------------
    // FIND + MATCH OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Finds first employee in given department.
     */
    public Emp findFirstEmpInDept(String dept) {

        return empStream()
                .filter(emp -> emp.isInDept(dept))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks whether any employee in department earns below given salary.
     */
    public boolean checkAnyEmpIsLessPaidInGivenDept(String dept, int salary) {

        return empStream()
                .anyMatch(emp -> emp.isInDept(dept) && emp.getSalary() < salary);
    }

    /**
     * Checks whether all employees earn above given salary.
     */
    public boolean checkAllEmpGettingGoodSalary(int salary) {

        return empStream()
                .allMatch(emp -> emp.isSalaryGreaterThan(salary));
    }

    // -------------------------------------------------------------------------
    // MAX / MIN / COUNT OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Finds highest paid employee.
     */
    public Emp findHighestPaidEmp() {

        return empStream()
                .max(Comparator.comparingInt(Emp::getSalary))
                .orElse(null);
    }

    /**
     * Finds lowest paid employee.
     */
    public Emp findLowestPaidEmp() {

        return empStream()
                .min(Comparator.comparingInt(Emp::getSalary))
                .orElse(null);
    }

    /**
     * Counts employees in given department.
     */
    public long findEmpCountInDept(String dept) {

        return empStream()
                .filter(emp -> emp.isInDept(dept))
                .count();
    }

    // -------------------------------------------------------------------------
    // AGGREGATION OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Calculates total salary of employees in department.
     */
    public int totalSalaryInDept(String dept) {

        return empStream()
                .filter(emp -> emp.isInDept(dept))
                .mapToInt(Emp::getSalary)
                .sum();
    }

    /**
     * Calculates average salary of all employees.
     */
    public double averageSalaryOfEmps() {

        return empStream()
                .mapToInt(Emp::getSalary)
                .average()
                .orElse(0);
    }

    /**
     * Returns salary statistics of all employees.
     */
    public IntSummaryStatistics summarizingSalaryOfAllEmps() {

        return empStream()
                .collect(summarizingInt(Emp::getSalary));
    }

    // -------------------------------------------------------------------------
    // GROUPING OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Groups employees by department.
     */
    public Map<String, List<Emp>> groupEmpByDept() {

        return empStream()
                .collect(groupingBy(Emp::getDept));
    }

    /**
     * Groups distinct sorted employees by department.
     */
    public Map<String, List<Emp>> groupEmpByDeptWithSortedAndDistinctEmps() {

        return duplicateEmpStream()
                .distinct()
                .sorted(Comparator.comparingInt(Emp::getId))
                .collect(groupingBy(Emp::getDept));
    }

    /**
     * Groups employee first names by department.
     */
    public Map<String, List<String>> groupEmpFirstNameByDept() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        mapping(Emp::getFirstName, toList())
                ));
    }

    /**
     * Groups employee full names by department.
     */
    public Map<String, List<String>> groupEmpFullNameByDept() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        mapping(Emp::getFullName, toList())
                ));
    }

    /**
     * Calculates total salary by department.
     */
    public Map<String, Integer> totalSalaryByDept() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        summingInt(Emp::getSalary)
                ));
    }

    /**
     * Counts employees by department.
     */
    public Map<String, Long> empCountByDept() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        counting()
                ));
    }

    /**
     * Finds highest paid employee by department.
     */
    public Map<String, Optional<Emp>> highestSalariedEmpByDept() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        maxBy(Comparator.comparingInt(Emp::getSalary))
                ));
    }

    /**
     * Finds highest salary amount by department.
     */
    public Map<String, Optional<Integer>> highestSalaryByDept() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        mapping(
                                Emp::getSalary,
                                maxBy(Integer::compareTo)
                        )
                ));
    }

    // -------------------------------------------------------------------------
    // PARTITIONING OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Partitions employees into well-paid and less-paid groups.
     */
    public Map<Boolean, List<Emp>> partitionEmpBySalary() {

        return empStream()
                .collect(partitioningBy(
                        emp -> emp.isSalaryGreaterThan(DEFAULT_WELL_PAID_SALARY)
                ));
    }

    /**
     * Partitions employee names into well-paid and less-paid groups.
     */
    public Map<Boolean, List<String>> partitionEmpNamesBySalary() {

        return empStream()
                .collect(partitioningBy(
                        emp -> emp.isSalaryGreaterThan(DEFAULT_WELL_PAID_SALARY),
                        mapping(Emp::getFirstName, toList())
                ));
    }

    // -------------------------------------------------------------------------
    // MAP OPERATIONS
    // -------------------------------------------------------------------------

    /**
     * Creates map of employee ID to full name.
     */
    public Map<Integer, String> mapEmpIdToEmpFullName() {

        return empStream()
                .collect(toMap(
                        Emp::getId,
                        Emp::getFullName
                ));
    }

    /**
     * Creates sorted TreeMap of employee ID to full name.
     */
    public Map<Integer, String> mapEmpIdToEmpFullNameSorted() {

        return empStream()
                .collect(toMap(
                        Emp::getId,
                        Emp::getFullName,
                        (oldValue, newValue) -> newValue,
                        TreeMap::new
                ));
    }

    /**
     * Creates nested map of department -> employee ID -> full name.
     */
    public Map<String, Map<Integer, String>> mapDeptToEmpIdAndFullName() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        toMap(
                                Emp::getId,
                                Emp::getFullName,
                                (oldValue, newValue) -> newValue,
                                TreeMap::new
                        )
                ));
    }

    // -------------------------------------------------------------------------
    // ADVANCED STREAM PROBLEMS
    // -------------------------------------------------------------------------

    /**
     * Finds second highest salaried employee.
     */
    public Emp findSecondHighestPaidEmp() {

        return empStream()
                .sorted(Comparator.comparingInt(Emp::getSalary).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    /**
     * Finds department having highest total salary.
     */
    public String findDeptWithHighestTotalSalary() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        summingInt(Emp::getSalary)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Finds highest-paid employee for each department.
     */
    public Map<String, Emp> highestPaidEmpByDept() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Emp::getSalary)),
                                Optional::get
                        )
                ));
    }

    /**
     * Groups employees first by department and then by salary category.
     */
    public Map<String, Map<String, List<Emp>>> groupEmpByDeptAndSalaryCategory() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        groupingBy(emp ->
                                emp.isSalaryGreaterThan(DEFAULT_WELL_PAID_SALARY)
                                        ? "HIGH"
                                        : "LOW"
                        )
                ));
    }

    /**
     * Finds duplicate employee first names.
     */
    public List<String> findDuplicateFirstNames() {

        return empStream()
                .collect(groupingBy(Emp::getFirstName, counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Finds duplicate employee full names.
     */
    public List<String> findDuplicateFullNames() {

        return empStream()
                .collect(groupingBy(Emp::getFullName, counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Finds top 3 highest paid employees.
     */
    public List<Emp> findTop3HighestPaidEmps() {

        return empStream()
                .sorted(Comparator.comparingInt(Emp::getSalary).reversed())
                .limit(TOP_EMPLOYEE_LIMIT)
                .toList();
    }

    /**
     * Finds employees having same salary.
     */
    public Map<Integer, List<Emp>> findEmployeesWithSameSalary() {

        return empStream()
                .collect(groupingBy(Emp::getSalary))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    /**
     * Finds employee having longest full name.
     */
    public Emp findEmpWithLongestFullName() {

        return empStream()
                .max(Comparator.comparingInt(emp -> emp.getFullName().length()))
                .orElse(null);
    }

    /**
     * Finds average salary and highest salary together.
     */
    public Map<String, Double> findAverageAndHighestSalary() {

        DoubleSummaryStatistics statistics = empStream()
                .collect(summarizingDouble(Emp::getSalary));

        Map<String, Double> result = new LinkedHashMap<>();
        result.put("averageSalary", statistics.getAverage());
        result.put("highestSalary", statistics.getMax());

        return result;
    }

    /**
     * Finds top 2 highest paid employees in each department.
     */
    public Map<String, List<Emp>> findTop2HighestPaidEmpByDept() {

        return empStream()
                .collect(groupingBy(
                        Emp::getDept,
                        collectingAndThen(
                                toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt(Emp::getSalary).reversed())
                                        .limit(2)
                                        .toList()
                        )
                ));
    }

    /**
     * Finds employee whose salary is closest to average salary.
     */
    public Emp findEmpClosestToAverageSalary() {

        double averageSalary = empStream()
                .mapToInt(Emp::getSalary)
                .average()
                .orElse(0);

        return empStream()
                .min(Comparator.comparingDouble(
                        emp -> Math.abs(averageSalary - emp.getSalary())
                ))
                .orElse(null);
    }

    /**
     * Finds average salary of top 3 highest paid employees.
     */
    public double findAverageSalaryOfTop3HighestPaidEmps() {

        return empStream()
                .sorted(Comparator.comparingInt(Emp::getSalary).reversed())
                .limit(TOP_EMPLOYEE_LIMIT)
                .mapToInt(Emp::getSalary)
                .average()
                .orElse(0);
    }

    /**
     * Groups employees by salary range.
     */
    public Map<String, Long> countEmpBySalaryRange() {

        return empStream()
                .collect(groupingBy(
                        emp -> {
                            if (emp.getSalary() < LOW_SALARY_LIMIT) {
                                return "LOW";
                            }

                            if (emp.getSalary() < HIGH_SALARY_LIMIT) {
                                return "MEDIUM";
                            }

                            return "HIGH";
                        },
                        counting()
                ));
    }

    /**
     * Finds department having maximum employees.
     */
    public String findDeptHavingMostEmployees() {

        return empStream()
                .collect(groupingBy(Emp::getDept, counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Finds frequency of each salary.
     */
    public Map<Integer, Long> findSalaryFrequencyMap() {

        return empStream()
                .collect(groupingBy(
                        Emp::getSalary,
                        TreeMap::new,
                        counting()
                ));
    }
}