/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.DAO.CourseDAOImpl;
import com.flipkart.DAO.CourseDAOInterface;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.EmptyCourseCatalogListExcpetion;
import com.flipkart.exception.InvalidCCSessionSemesterException;
import com.flipkart.exception.InvalidDepartmentException;

/**
 * The Class CourseCatalogServiceImpl.
 *
 * @author yashsoni501
 */
public class CourseCatalogServiceImpl implements CourseCatalogInterface {

	/** The instance. */
	public static volatile CourseCatalogServiceImpl instance = null;

	/** The course DAO. */
	CourseDAOInterface courseDAO = CourseDAOImpl.getInstance();

	/**
	 * Gets the single instance of CourseCatalogServiceImpl.
	 *
	 * @return single instance of CourseCatalogServiceImpl
	 */
	public static CourseCatalogServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (CourseCatalogServiceImpl.class) {
				instance = new CourseCatalogServiceImpl();
			}
		}
		return instance;
	}

	/**
	 * Gets the course catalog.
	 *
	 * @param courseId the course id
	 * @return the course catalog
	 * @throws CourseCatalogEntryNotFoundException the course catalog entry not
	 *                                             found exception
	 * @throws SQLException                        the SQL exception
	 */
	@Override
	public CourseCatalog getCourseCatalog(String courseId) throws CourseCatalogEntryNotFoundException, SQLException {

		return courseDAO.getCourseCatalog(courseId);
	}

	/**
	 * Gets the course catalog by session semester.
	 *
	 * @param session  the session
	 * @param semester the semester
	 * @return the course catalog by session semester
	 * @throws InvalidCCSessionSemesterException the invalid CC session semester
	 *                                           exception
	 * @throws SQLException                      the SQL exception
	 */
	@Override
	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester)
			throws InvalidCCSessionSemesterException, SQLException {

		return courseDAO.getCourseCatalogBySessionSemester(session, semester);
	}

	/**
	 * Gets the department course catalog.
	 *
	 * @param department the department
	 * @return the department course catalog
	 * @throws InvalidDepartmentException the invalid department exception
	 * @throws SQLException               the SQL exception
	 */
	@Override
	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department)
			throws InvalidDepartmentException, SQLException {

		return courseDAO.getDepartmentCourseCatalog(department);
	}

	/**
	 * Update professor id.
	 *
	 * @param courseId    the course id
	 * @param professorId the professor id
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean updateProfessorId(String courseId, String professorId) throws SQLException {

		return courseDAO.updateProfessorId(courseId, professorId);
	}

	/**
	 * Gets the course catalog by professor id.
	 *
	 * @param userId the user id
	 * @return the course catalog by professor id
	 * @throws SQLException                    the SQL exception
	 * @throws EmptyCourseCatalogListExcpetion the empty course catalog list
	 *                                         excpetion
	 */
	@Override
	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId)
			throws SQLException, EmptyCourseCatalogListExcpetion {

		return courseDAO.getCourseCatalogByProfessorId(userId);
	}

	/**
	 * Gets the all course catalog.
	 *
	 * @return the all course catalog
	 * @throws SQLException                    the SQL exception
	 * @throws EmptyCourseCatalogListExcpetion the empty course catalog list
	 *                                         excpetion
	 */
	@Override
	public ArrayList<CourseCatalog> getAllCourseCatalog() throws SQLException, EmptyCourseCatalogListExcpetion {
		// Auto-generated method stub
		return courseDAO.getAllCourseCatalog();
	}

}
