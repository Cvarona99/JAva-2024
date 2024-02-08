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
import com.techelevator.projects.model.Department;

public class JdbcDepartmentDao implements DepartmentDao {

    private final String DEPARTMENT_SELECT = "SELECT d.department_id, d.name FROM department d ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcDepartmentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Department getDepartmentById(int id) {
        try {
            Department department = null;
            String sql = DEPARTMENT_SELECT + " WHERE d.department_id=?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                department = mapRowToDepartment(results);
            }
            return department;
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Error getting department with ID " + id, e);
        }
    }


    @Override
    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = DEPARTMENT_SELECT;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                departments.add(mapRowToDepartment(results));
            }
        } catch (DataAccessException e) {
            // Catch any DataAccessException and throw a new DaoException
            throw new DaoException("Unable to retrieve departments", e);
        }
        return departments;
    }

    @Override
    public Department createDepartment(Department department) {
        String insertSql = "INSERT INTO department (name) VALUES (?)";
        String sql = "SELECT department_id FROM department WHERE name = ? ORDER BY department_id DESC LIMIT 1";

        try {
            jdbcTemplate.update(insertSql, department.getName());
            // Assuming the name is unique, this will get the most recent entry.
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, department.getName());
            if (rowSet.next()) {
                int departmentId = rowSet.getInt("department_id");
                return getDepartmentById(departmentId);
            } else {
                throw new DaoException("Creating department failed, no ID obtained.");
            }
        } catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
            throw new DaoException("Unable to create department", e);
        }
    }

    @Override
    public Department updateDepartment(Department department) {
        if (department == null) {
            throw new DaoException("Department or Department ID is null");
        }
        String sql = "UPDATE department SET name = ? WHERE department_id = ?";
        try {
            int rowsUpdated = jdbcTemplate.update(sql, department.getName(), department.getId());
            if (rowsUpdated == 1) {
                return getDepartmentById(department.getId());
            } else if (rowsUpdated == 0) {
                throw new DaoException("No department found with ID " + department.getId());
            } else {
                throw new DaoException("More than one department was updated for ID " + department.getId());
            }

        } catch (DataAccessException e) {
            throw new DaoException("Unable to update department with ID " + department.getId(), e);
        }
    }

    @Override
    public int deleteDepartmentById(int id) {
        int rowsChanged = 0;
        String sqlDeleteProjectEmployee = "DELETE FROM project_employee WHERE employee_id IN (SELECT employee_id FROM employee WHERE department_id = ?)";
        String sqlDeleteEmployee = "DELETE FROM employee WHERE department_id = ?";
        String sqlDeleteDepartment = "DELETE FROM department WHERE department_id = ?";
        try {
            jdbcTemplate.update(sqlDeleteProjectEmployee, id);
            jdbcTemplate.update(sqlDeleteEmployee,id);
            rowsChanged = jdbcTemplate.update(sqlDeleteDepartment, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("unable to connect to server or database");
//            throw new DaoException("Error deleting department with ID " + id, e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return rowsChanged;
    }

//		int rowsChanged = 0;
//		String sqlEmployee = "DELETE FROM employee WHERE department_id = ?";
//		String sqlDepartment = "DELETE FROM department WHERE department_id = ?";
//		try {
////			 jdbcTemplate.update(sqlEmployee, id);
//			 rowsChanged = jdbcTemplate.update( sqlEmployee,sqlDepartment,id);
//		} catch (DataIntegrityViolationException e) {
//			throw new DaoException("Error deleting department with ID " + id, e);
//		}
//		return rowsChanged;

    private Department mapRowToDepartment(SqlRowSet results) {
        Department department = new Department();
        department.setId(results.getInt("department_id"));
        department.setName(results.getString("name"));
        return department;
    }

}
