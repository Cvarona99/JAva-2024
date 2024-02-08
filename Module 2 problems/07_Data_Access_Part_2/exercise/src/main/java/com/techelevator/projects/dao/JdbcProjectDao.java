package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.projects.model.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final String PROJECT_SELECT = "SELECT p.project_id, p.name, p.from_date, p.to_date FROM project p";

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProjectById(int projectId) {
		Project project = null;
		try {



			String sql = PROJECT_SELECT +
					" WHERE p.project_id=?";

			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
			if (results.next()) {
				project = mapRowToProject(results);
			}


		} catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
			throw new DaoException("Error getting project with ID " + projectId, e);
		}
		return project;
	}

	@Override
	public List<Project> getProjects() {
		List<Project> allProjects = new ArrayList<>();
		try {
			String sql = PROJECT_SELECT;

			SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			while (results.next()) {
				Project projectResult = mapRowToProject(results);
				allProjects.add(projectResult);
			}
		} catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
			throw new DaoException("Error getting projects", e);
		}

		return allProjects;
	}

	@Override
	public Project createProject(Project newProject) {
		int id = 0;
		Project createdProject = null;
		String insertSql = "INSERT INTO project (name, from_date, to_date) VALUES (?, ?, ?) RETURNING project_id";
		try {
			id = jdbcTemplate.queryForObject(insertSql, int.class, newProject.getName(), newProject.getFromDate(), newProject.getToDate());
			createdProject = getProjectById(id);
		} catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
			throw new DaoException("Unable to create employee",e);
		}
		return createdProject;
	}
	
	@Override
	public void linkProjectEmployee(int projectId, int employeeId) {
		String addEmployeeSql = "INSERT INTO project_employee (project_id, employee_id) VALUES (?, ?)";
		try {
			jdbcTemplate.update(addEmployeeSql, projectId, employeeId);
		} catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
			throw new DaoException("Unable to link projectEmployee",e);
		}
	}

	@Override
	public void unlinkProjectEmployee(int projectId, int employeeId) {
		String removeEmployeeSql = "DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?";
		try {
			jdbcTemplate.update(removeEmployeeSql, projectId, employeeId);
		} catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
			throw new DaoException("Unable to unlink projectEmployee",e);
		}
	}

	@Override
	public Project updateProject(Project project) {
		Project updatedProject = null;
		String sqlUpdate  = "UPDATE project SET name = ?, from_date = ?, to_date = ? WHERE project_id = ?";
		try {
			int rowsChanged = jdbcTemplate.update(sqlUpdate, project.getName(), project.getFromDate(), project.getToDate(), project.getId());
			if (rowsChanged == 0) {
				throw new DaoException("No rows updated");
			} else {
				updatedProject = getProjectById(project.getId());
			}
		} catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
			throw new DaoException("Unable to update project",e);
		}
		return updatedProject;
	}
	@Override
	public int deleteProjectById(int projectId) {
		int rowsChanged = 0;
		String sqlDeleteProjectEmployee = "DELETE FROM project_employee WHERE project_id = ?";
		String sqlDeleteProject = "DELETE FROM project WHERE project_id = ?";
		try {
			jdbcTemplate.update(sqlDeleteProjectEmployee, projectId);
			rowsChanged = jdbcTemplate.update(sqlDeleteProject,projectId);
		} catch (CannotGetJdbcConnectionException | DataIntegrityViolationException e) {
			throw new DaoException("Unable to delete project",e);
		}

		return rowsChanged;
	}
	
	private Project mapRowToProject(SqlRowSet results) {
		Project project = new Project();
		project.setId(results.getInt("project_id"));
		project.setName(results.getString("name"));
		if (results.getDate("from_date") != null) {
			project.setFromDate(results.getDate("from_date").toLocalDate());
		}
		if (results.getDate("to_date") != null) {
			project.setToDate(results.getDate("to_date").toLocalDate());
		}
		return project;
	}

}
