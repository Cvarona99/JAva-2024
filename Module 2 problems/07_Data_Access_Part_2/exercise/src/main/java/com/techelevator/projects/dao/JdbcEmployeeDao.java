package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Employee;

public class JdbcEmployeeDao implements EmployeeDao {

    private final String EMPLOYEE_SELECT = "SELECT e.employee_id, e.department_id, e.first_name, e.last_name, " +
            "e.birth_date, e.hire_date FROM employee e ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcEmployeeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        String sql = EMPLOYEE_SELECT +
                " WHERE e.employee_id=?";

        try {


            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                employee = mapRowToEmployee(results);
            }


        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Error getting employee with ID" + id, e);
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> allEmployees = new ArrayList<>();
        try {
            String sql = EMPLOYEE_SELECT;

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Employee employeeResult = mapRowToEmployee(results);
                allEmployees.add(employeeResult);
            }
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Error getting employees", e);
        }

        return allEmployees;
    }

    @Override
    public List<Employee> getEmployeesByFirstNameLastName(String firstName, String lastName) {
        List<Employee> allEmployees = new ArrayList<>();
        String sql = EMPLOYEE_SELECT +
                " WHERE e.first_name ILIKE ? AND e.last_name ILIKE ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + firstName + "%", "%" + lastName + "%");
            while (results.next()) {
                Employee employeeResult = mapRowToEmployee(results);
                allEmployees.add(employeeResult);
            }
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Error getting employees", e);
        }

        return allEmployees;
    }

    @Override
    public List<Employee> getEmployeesByProjectId(int projectId) {
        List<Employee> allEmployees = new ArrayList<>();
        String sql = EMPLOYEE_SELECT +
                "JOIN project_employee pe ON e.employee_id = pe.employee_id " +
                "WHERE pe.project_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
            while (results.next()) {
                Employee employeeResult = mapRowToEmployee(results);
                allEmployees.add(employeeResult);
            }
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Error getting employee with project_id" + projectId, e);
        }

        return allEmployees;
    }

    @Override
    public List<Employee> getEmployeesWithoutProjects() {
        List<Employee> allEmployees = new ArrayList<>();
        String sql = EMPLOYEE_SELECT +
                " WHERE e.employee_id NOT IN (SELECT DISTINCT employee_id FROM project_employee)";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Employee employeeResult = mapRowToEmployee(results);
                allEmployees.add(employeeResult);
            }
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Error getting employees", e);
        }

        return allEmployees;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Employee createdEmployee = null;
        String insertSql = "INSERT INTO employee (department_id, first_name, last_name, birth_date, hire_date) VALUES (?, ?, ?, ?, ?) RETURNING employee_id";
        int employeeId = 0;
        try {
            employeeId = jdbcTemplate.queryForObject(insertSql, new Object[]{employee.getDepartmentId(), employee.getFirstName(), employee.getLastName(), employee.getBirthDate(), employee.getHireDate()}, Integer.class);
            createdEmployee = getEmployeeById(employeeId);
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Unable to create employee",e);
        }
        return createdEmployee;
    }



    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = null;
        if (employee == null) {
            throw new DaoException("employee or employee ID is null");
        }
        String sqlUpdate = "UPDATE employee SET department_id = ?, first_name = ?, last_name = ?, birth_date = ?, hire_date = ? WHERE employee_id = ?";
        try {
            int rowsChanged = jdbcTemplate.update(sqlUpdate, employee.getDepartmentId(), employee.getFirstName(),employee.getLastName(), employee.getBirthDate(),employee.getHireDate(), employee.getId());
            if (rowsChanged == 0) {
                throw new DaoException("No rows updated");
            } else {
                updatedEmployee = getEmployeeById(employee.getId());
            }
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Unable to update employee",e);
        }
        return updatedEmployee;
    }

    @Override
    public int deleteEmployeeById(int id) {
        int rowsChanged = 0;
        String sqlProjectEmployee = "DELETE FROM project_employee WHERE employee_id = ? ";
        String sqlEmployee = "DELETE FROM employee WHERE employee_id = ?";

        try {
            jdbcTemplate.update(sqlProjectEmployee,id);
            rowsChanged = jdbcTemplate.update(sqlEmployee, id);
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Unable to delete employee",e);
        }
        return rowsChanged;
    }

    @Override
    public int deleteEmployeesByDepartmentId(int departmentId) {
        int rowsChanged = 0;
        String sqlProjectEmployee = "DELETE FROM project_employee WHERE employee_id IN (SELECT employee_id FROM employee WHERE department_id = ?)";
        String sqlEmployee = "DELETE FROM employee WHERE department_id = ?";
        try {
            jdbcTemplate.update(sqlProjectEmployee, departmentId);
            rowsChanged = jdbcTemplate.update(sqlEmployee, departmentId);
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Unable to delete employees", e);
        }
        return rowsChanged;
    }

    private Employee mapRowToEmployee(SqlRowSet result) {
        Employee employee = new Employee();
        employee.setId(result.getInt("employee_id"));
        employee.setDepartmentId(result.getInt("department_id"));
        employee.setFirstName(result.getString("first_name"));
        employee.setLastName(result.getString("last_name"));
        employee.setBirthDate(result.getDate("birth_date").toLocalDate());
        employee.setHireDate(result.getDate("hire_date").toLocalDate());
        return employee;
    }
}
