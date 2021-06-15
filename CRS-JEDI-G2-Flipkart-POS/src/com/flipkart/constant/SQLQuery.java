/**
 * 
 */
package com.flipkart.constant;

/**
 * @author aysh
 *
 */
public class SQLQuery {

	// Admin DAO Queries
	public static final String ADD_COURSE = "insert into course (`courseName`, `department`) values (?, ?)";

	public static final String ADD_USER = "insert into auth (`email`, `password`, `userRole`) values (?, ?, ?)";
	public static final String ADD_PROFESSOR = "insert into professor (`profid`, `email`, `name`, `department`) values (?, ?, ?, ?)";
	public static final String ADD_STUDENT = "insert into student (`stuid`, `email`, `name`, `department`, `session`) values (?, ?, ?, ?, ?)";

	public static final String EDIT_COURSE_REGISTRATION = "update constants set value=? where `key`=?";
	public static final String EDIT_PAYMENT_FLAG = "update constants set value=? where `key`=?";

	public static final String EDIT_PROFESSOR_FLAG = "update constants set value=? where `key`=?";

	public static final String REMOVE_PROFESSOR_PROFILE = "delete from professor where profid=?";
	public static final String REMOVE_PROFESSOR_AUTH = "delete from auth where uid=? ";

	public static final String MODIFY_PROFESSOR = "update professor set name=?, department=? where profid=?";

	public static final String MODIFY_STUDENT = "update student set name=?, department=?, session=?, approved=? where email=?";

	public static final String REMOVE_COURSE_CATALOG = "delete from courseCatalog where courseid=?";

	public static final String MODIFY_COURSE = "update course set courseName=?, department=? where courseid=?";

	public static final String ADD_COURSE_CATALOG = "insert into courseCatalog (`courseid`, `profid`, `semester`, `session`, `credits`) values (?, ?, ?, ?, ?)";

	public static final String MODIFY_COURSE_CATALOG = "update courseCatalog set profid=?, semester=?, session=?, credits=? where courseid=?";

	public static final String GET_ADMIN_BY_ID = "select * from admin where adminid=?";

	public static final String GET_FLAG = "select value from constants where `key`=?";

	public static final String EDIT_STUDENT_PERMISSION = "update student set approved=? where stuid=? ";

	public static final String REMOVE_STUDENT_PROFILE = "delete from student where stuid=?";

	public static final String REMOVE_STUDENT_AUTH = "delete from auth where uid=? ";

	// Auth DAO Query
	public static final String UPDATE_PASSWORD = "update auth set password=? where email=?";
	public static final String VERIFY_USER = "select * from auth where email = ?";
	public static final String GET_ROLE = "select userRole from auth where uid=?";

	public static final String MAKE_PAYMENT = "insert into payment values(?,?,?,?,?,?,?)";
	public static final String GET_FEE_RECIEPT = "SELECT * FROM payment WHERE stuid=? AND semester=? AND status=?";

	// Professor DAO Queries
	public static final String SELECT_OPTED_COURSES_PROF = "select * from courseCatalog where profid = ?";
	public static final String OPT_IN_COURSE_PROF = "update courseCatalog set profid = ? where (courseid = ? and profid is null)";	
	public static final String GET_PROFESSOR_DETAIL = "Select * from professor where profid = ?";
	public static final String VIEW_ENROLLED_STUDENTS = "select stuid from registeredCourse where courseid = ? AND session = ?";
	public static final String SUBMIT_GRADES_PROF = "update registeredCourse set grade = ? where courseid = ? and stuid = ?";
	public static final String SELECT_ALL_PROFS = "select * from professor";

	// Course DAO Queries
	public static final String GET_COURSE_BY_ID = "select * from course where courseid=?";
	public static final String GET_ALL_COURSES = "select * from course";
	public static final String GET_COURSE_CATALOG_BY_ID = "select * from courseCatalog where courseid=?";
	public static final String GET_COURSE_CATALOG_BY_SESSION_SEMESTER = "select * from courseCatalog where session = ? and semester = ?";
	public static final String GET_DEPARTMENT_COURSE_CATALOG = "select cc.courseid, cc.profid, cc.semester, cc.session, cc.credits from courseCatalog as cc, course as c where cc.courseid = c.courseid and c.department = ?";
	public static final String GET_COURSE_CATALOG = "select * from courseCatalog";
	public static final String GET_CATALOG_BY_PROF_ID = "select * from courseCatalog where profid = ?";
	public static final String UPDATE_PROF_IN_COURSE = "update courseCatalog set profid=? where courseid=?";
	public static final String REMOVE_COURSE = "delete from course where courseid=?";

	// Student DAO Query
	public static final String STUDENT_REGISTERED_COURSES = "select * from registeredCourse where stuid = ? and semester = ?";
	// public static final String COURSE_CATALOG_BY_ID = "select * from
	// courseCatalog where courseid = ?";
	public static final String STUDENTS_IN_SESSION = "select * from student where session = ?";
	public static final String STUDENT_BY_ID = "select * from student where stuid = ?";

	// RegisteredCourse DAO Query
	public static final String SELECT_STUDENTS_IN_COURSE = "select * from registeredCourse where courseid = ? AND semester = ? AND session = ?";
	public static final String SELECT_STUDENT_BY_ID = "select * from student where stuid = ?";
	public static final String GET_REGISTERED_COURSES_BY_STUDENT_ID = "select * from registeredCourse where stuid = ? and semester = ?";
	public static final String ADD_REGISTERED_COURSE = "insert into registeredCourse (`courseid`, `stuid`, `semester`, `session`, `grade`) values (?, ?, ?, ?, ?)";

	// SemesterReportCard Queries
	public static final String ADD_SEMESTER_REPORT = "INSERT INTO semesterReportCard(stuid, sgpa, semester) VALUES (?,?,?)";
	public static final String GET_ALL_REPORTS = "SELECT * from semesterReportCard WHERE stuid=? ORDER BY semester";
}
