package com.firealgo.cloudrun.rest;


import com.firealgo.cloudrun.entity.Emp;
import com.firealgo.cloudrun.service.EmpService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * REST Controller exposing Java Stream API examples
 * using Employee dataset.
 */
@RestController
@RequestMapping("/api/employees")

public class EmpController {

    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    // -------------------------------------------------------------------------
    // BASIC OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Get all employees",
            tags = {"Basic Operations"}
    )
    @GetMapping
    public List<Emp> getAllEmployees() {

        return empService.getAllEmp();
    }

    @Operation(
            summary = "Get all employees including duplicates",
            tags = {"Basic Operations"}
    )
    @GetMapping("/duplicates")
    public List<Emp> getAllEmployeesWithDuplicates() {

        return empService.getAllEmpWithDuplicates();
    }

    // -------------------------------------------------------------------------
    // FILTER OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Get employees with salary greater than given amount",
            tags = {"Filter Operations"}
    )
    @GetMapping("/well-paid/{salary}")
    public List<Emp> getWellPaidEmployees(@PathVariable int salary) {

        return empService.findWellPaidEmpList(salary);
    }

    @Operation(
            summary = "Get first names of well-paid employees",
            tags = {"Filter Operations"}
    )
    @GetMapping("/well-paid/{salary}/first-names")
    public List<String> getWellPaidEmployeeNames(@PathVariable int salary) {

        return empService.findWellPaidEmpNameList(salary);
    }

    @Operation(
            summary = "Get well-paid employees grouped by department",
            tags = {"Filter Operations"}
    )
    @GetMapping("/well-paid/{salary}/department")
    public Map<String, List<Emp>> getWellPaidEmployeesByDepartment(
            @PathVariable int salary
    ) {

        return empService.wellPaidEmpByDept(salary);
    }

    // -------------------------------------------------------------------------
    // DISTINCT OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Get distinct departments",
            tags = {"Distinct Operations"}
    )
    @GetMapping("/departments/distinct")
    public List<String> getDistinctDepartments() {

        return empService.findDistinctDeptList();
    }

    @Operation(
            summary = "Get department set",
            tags = {"Distinct Operations"}
    )
    @GetMapping("/departments/set")
    public Set<String> getDepartmentSet() {

        return empService.findDeptSet();
    }

    @Operation(
            summary = "Get distinct employees",
            tags = {"Distinct Operations"}
    )
    @GetMapping("/distinct")
    public List<Emp> getDistinctEmployees() {

        return empService.findAllDistinctEmp();
    }

    @Operation(
            summary = "Get distinct employees as Set",
            tags = {"Distinct Operations"}
    )
    @GetMapping("/distinct/set")
    public Set<Emp> getDistinctEmployeeSet() {

        return empService.findAllEmpSet();
    }

    // -------------------------------------------------------------------------
    // SORTING OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Get distinct sorted employee first names",
            tags = {"Sorting Operations"}
    )
    @GetMapping("/first-names/distinct/sorted")
    public List<String> getDistinctSortedEmployeeFirstNames() {

        return empService.findEmpFirstNameSortedAndDistinctList();
    }

    @Operation(
            summary = "Get reverse sorted distinct employee first names",
            tags = {"Sorting Operations"}
    )
    @GetMapping("/first-names/distinct/reverse-sorted")
    public List<String> getReverseSortedDistinctEmployeeFirstNames() {

        return empService.findEmpFirstNameReverseSortedAndDistinctList();
    }

    @Operation(
            summary = "Get employees sorted by ID",
            tags = {"Sorting Operations"}
    )
    @GetMapping("/sorted/id")
    public List<Emp> getEmployeesSortedById() {

        return empService.findEmpSortedByIdList();
    }

    @Operation(
            summary = "Get employees sorted by full name",
            tags = {"Sorting Operations"}
    )
    @GetMapping("/sorted/full-name")
    public List<Emp> getEmployeesSortedByFullName() {

        return empService.findEmpSortedByFullNameList();
    }

    @Operation(
            summary = "Get distinct employees sorted by full name",
            tags = {"Sorting Operations"}
    )
    @GetMapping("/sorted/full-name/distinct")
    public List<Emp> getDistinctEmployeesSortedByFullName() {

        return empService.findDistinctEmpSortedByFullNameList();
    }

    // -------------------------------------------------------------------------
    // JOINING OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Get comma-separated employee full names",
            tags = {"Joining Operations"}
    )
    @GetMapping("/full-names/comma-separated")
    public String getCommaSeparatedEmployeeNames() {

        return empService.findCommaSeparatedEmpFullNames();
    }

    // -------------------------------------------------------------------------
    // FIND + MATCH OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Find first employee in department",
            tags = {"Find & Match Operations"}
    )
    @GetMapping("/department/{dept}/first")
    public Emp getFirstEmployeeInDepartment(@PathVariable String dept) {

        return empService.findFirstEmpInDept(dept);
    }

    @Operation(
            summary = "Get employee count in department",
            tags = {"Find & Match Operations"}
    )
    @GetMapping("/department/{dept}/count")
    public long getEmployeeCountInDepartment(@PathVariable String dept) {

        return empService.findEmpCountInDept(dept);
    }

    @Operation(
            summary = "Check if any employee in department earns below salary",
            tags = {"Find & Match Operations"}
    )
    @GetMapping("/department/{dept}/salary-less-than/{salary}")
    public boolean hasLowPaidEmployee(
            @PathVariable String dept,
            @PathVariable int salary
    ) {

        return empService.checkAnyEmpIsLessPaidInGivenDept(dept, salary);
    }

    @Operation(
            summary = "Check if all employees earn above salary",
            tags = {"Find & Match Operations"}
    )
    @GetMapping("/salary-above/{salary}/all")
    public boolean areAllEmployeesWellPaid(@PathVariable int salary) {

        return empService.checkAllEmpGettingGoodSalary(salary);
    }

    // -------------------------------------------------------------------------
    // AGGREGATION OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Get highest paid employee",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/highest")
    public Emp getHighestPaidEmployee() {

        return empService.findHighestPaidEmp();
    }

    @Operation(
            summary = "Get second highest paid employee",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/second-highest")
    public Emp getSecondHighestPaidEmployee() {

        return empService.findSecondHighestPaidEmp();
    }

    @Operation(
            summary = "Get lowest paid employee",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/lowest")
    public Emp getLowestPaidEmployee() {

        return empService.findLowestPaidEmp();
    }

    @Operation(
            summary = "Get average employee salary",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/average")
    public double getAverageSalary() {

        return empService.averageSalaryOfEmps();
    }

    @Operation(
            summary = "Get salary statistics",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/statistics")
    public IntSummaryStatistics getSalaryStatistics() {

        return empService.summarizingSalaryOfAllEmps();
    }

    @Operation(
            summary = "Get total salary by department",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/department-total")
    public Map<String, Integer> getTotalSalaryByDepartment() {

        return empService.totalSalaryByDept();
    }

    @Operation(
            summary = "Get average and highest salary",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/average-highest")
    public Map<String, Double> getAverageAndHighestSalary() {

        return empService.findAverageAndHighestSalary();
    }

    @Operation(
            summary = "Get average salary of top 3 highest paid employees",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/top-3/average")
    public double getAverageSalaryOfTop3HighestPaidEmployees() {

        return empService.findAverageSalaryOfTop3HighestPaidEmps();
    }

    @Operation(
            summary = "Get salary frequency map",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/frequency")
    public Map<Integer, Long> getSalaryFrequencyMap() {

        return empService.findSalaryFrequencyMap();
    }

    @Operation(
            summary = "Get employee count by salary range",
            tags = {"Aggregation Operations"}
    )
    @GetMapping("/salary/range-count")
    public Map<String, Long> getEmployeeCountBySalaryRange() {

        return empService.countEmpBySalaryRange();
    }

    // -------------------------------------------------------------------------
    // GROUPING OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Group employees by department",
            tags = {"Grouping Operations"}
    )
    @GetMapping("/group-by/department")
    public Map<String, List<Emp>> groupEmployeesByDepartment() {

        return empService.groupEmpByDept();
    }

    @Operation(
            summary = "Group distinct sorted employees by department",
            tags = {"Grouping Operations"}
    )
    @GetMapping("/group-by/department/distinct-sorted")
    public Map<String, List<Emp>> groupDistinctSortedEmployeesByDepartment() {

        return empService.groupEmpByDeptWithSortedAndDistinctEmps();
    }

    @Operation(
            summary = "Get employee count by department",
            tags = {"Grouping Operations"}
    )
    @GetMapping("/group-by/department/count")
    public Map<String, Long> getEmployeeCountByDepartment() {

        return empService.empCountByDept();
    }

    @Operation(
            summary = "Get highest paid employee by department",
            tags = {"Grouping Operations"}
    )
    @GetMapping("/group-by/department/highest-paid")
    public Map<String, Emp> getHighestPaidEmployeeByDepartment() {

        return empService.highestPaidEmpByDept();
    }

    @Operation(
            summary = "Group employees by department and salary category",
            tags = {"Grouping Operations"}
    )
    @GetMapping("/group-by/department/salary-category")
    public Map<String, Map<String, List<Emp>>> groupEmployeesByDepartmentAndSalaryCategory() {

        return empService.groupEmpByDeptAndSalaryCategory();
    }

    @Operation(
            summary = "Get top 2 highest paid employees by department",
            tags = {"Grouping Operations"}
    )
    @GetMapping("/group-by/department/top-2-highest-paid")
    public Map<String, List<Emp>> getTop2HighestPaidEmployeesByDepartment() {

        return empService.findTop2HighestPaidEmpByDept();
    }

    // -------------------------------------------------------------------------
    // PARTITIONING OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Partition employees by salary",
            tags = {"Partitioning Operations"}
    )
    @GetMapping("/partition/salary")
    public Map<Boolean, List<Emp>> partitionEmployeesBySalary() {

        return empService.partitionEmpBySalary();
    }

    @Operation(
            summary = "Partition employee names by salary",
            tags = {"Partitioning Operations"}
    )
    @GetMapping("/partition/salary/first-names")
    public Map<Boolean, List<String>> partitionEmployeeNamesBySalary() {

        return empService.partitionEmpNamesBySalary();
    }

    // -------------------------------------------------------------------------
    // MAP OPERATIONS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Map employee ID to full name",
            tags = {"Map Operations"}
    )
    @GetMapping("/map/id-to-full-name")
    public Map<Integer, String> mapEmployeeIdToFullName() {

        return empService.mapEmpIdToEmpFullName();
    }

    @Operation(
            summary = "Map department to employee ID and full name",
            tags = {"Map Operations"}
    )
    @GetMapping("/map/department/id-full-name")
    public Map<String, Map<Integer, String>> mapDepartmentToEmployeeIdAndFullName() {

        return empService.mapDeptToEmpIdAndFullName();
    }

    // -------------------------------------------------------------------------
    // ADVANCED STREAM PROBLEMS
    // -------------------------------------------------------------------------

    @Operation(
            summary = "Get department with highest total salary",
            tags = {"Advanced Stream Problems"}
    )
    @GetMapping("/department/highest-total-salary")
    public String getDepartmentWithHighestTotalSalary() {

        return empService.findDeptWithHighestTotalSalary();
    }

    @Operation(
            summary = "Get department having most employees",
            tags = {"Advanced Stream Problems"}
    )
    @GetMapping("/department/max-employees")
    public String getDepartmentHavingMostEmployees() {

        return empService.findDeptHavingMostEmployees();
    }

    @Operation(
            summary = "Get duplicate employee first names",
            tags = {"Advanced Stream Problems"}
    )
    @GetMapping("/duplicates/first-names")
    public List<String> getDuplicateFirstNames() {

        return empService.findDuplicateFirstNames();
    }

    @Operation(
            summary = "Get duplicate employee full names",
            tags = {"Advanced Stream Problems"}
    )
    @GetMapping("/duplicates/full-names")
    public List<String> getDuplicateFullNames() {

        return empService.findDuplicateFullNames();
    }

    @Operation(
            summary = "Get top 3 highest paid employees",
            tags = {"Advanced Stream Problems"}
    )
    @GetMapping("/salary/top-3")
    public List<Emp> getTop3HighestPaidEmployees() {

        return empService.findTop3HighestPaidEmps();
    }

    @Operation(
            summary = "Get employees having same salary",
            tags = {"Advanced Stream Problems"}
    )
    @GetMapping("/salary/same")
    public Map<Integer, List<Emp>> getEmployeesHavingSameSalary() {

        return empService.findEmployeesWithSameSalary();
    }

    @Operation(
            summary = "Get employee with longest full name",
            tags = {"Advanced Stream Problems"}
    )
    @GetMapping("/full-name/longest")
    public Emp getEmployeeWithLongestFullName() {

        return empService.findEmpWithLongestFullName();
    }

    @Operation(
            summary = "Get employee closest to average salary",
            tags = {"Advanced Stream Problems"}
    )
    @GetMapping("/salary/closest-to-average")
    public Emp getEmployeeClosestToAverageSalary() {

        return empService.findEmpClosestToAverageSalary();
    }
}