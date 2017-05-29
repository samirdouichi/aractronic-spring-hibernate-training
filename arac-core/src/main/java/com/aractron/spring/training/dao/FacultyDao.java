package com.aractron.spring.training.dao;

import java.util.List;

import com.aractron.spring.training.domain.Faculty;

/**
 * Data Access interface for managing Faculty objects.
 * 
 * @author vanessa.ortiz
 */
public interface FacultyDao extends UserDao<Faculty>{

	/**
	 * Method that retrieves all of the Faculty objects according to 
	 * department name.
	 * 
	 * @param departmentName The name of the department the Faculty belongs to.
	 * @return An ArrayList of Faculty objects.
	 */
	public List<Faculty> findAllFacultyByDepartment(String departmentName);
	
}
