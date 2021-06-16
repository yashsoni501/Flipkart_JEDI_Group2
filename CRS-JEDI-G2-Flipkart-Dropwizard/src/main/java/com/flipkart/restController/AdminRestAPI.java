/**
 * 
 */
package com.flipkart.restController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.flipkart.application.CRSApplication;
import com.flipkart.application.CRSAdminMenu;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.SemesterReportCard;
import com.flipkart.bean.Student;
import com.flipkart.constant.Constants;
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.EmptyCourseCatalogListExcpetion;
import com.flipkart.exception.EmptyCourseListExcpetion;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.NoProfessorsFoundException;
import com.flipkart.exception.NoRegisteredCoursesException;
import com.flipkart.exception.SemesterReportCardNotFound;
import com.flipkart.exception.UserEmailAlreadyInUseException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.CourseCatalogInterface;
import com.flipkart.service.CourseCatalogServiceImpl;
import com.flipkart.service.CourseInterface;
import com.flipkart.service.CourseServiceImpl;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorServiceImpl;
import com.flipkart.service.RegisteredCourseInterface;
import com.flipkart.service.RegisteredCourseServiceImpl;
import com.flipkart.service.SemesterReportCardInterface;
import com.flipkart.service.SemesterReportCardServiceImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceImpl;
import com.flipkart.utils.MenuOptionScanner;

/**
 * @author Tanmay
 *
 */
@Path("/admin")
public class AdminRestAPI {

	/** The admin interface. */
	AdminInterface adminInterface = AdminServiceImpl.getInstance();

	/** The professor interface. */
	ProfessorInterface professorInterface = ProfessorServiceImpl.getInstance();

	/** The student interface. */
	StudentInterface studentInterface = StudentServiceImpl.getInstance();

	/** The course catalog interface. */
	CourseCatalogInterface courseCatalogInterface = CourseCatalogServiceImpl.getInstance();

	/** The course interface. */
	CourseInterface courseInterface = CourseServiceImpl.getInstance();

	/** The semester report card interface. */
	SemesterReportCardInterface semesterReportCardInterface = SemesterReportCardServiceImpl.getInstance();

	/** The registered course interface. */
	RegisteredCourseInterface registeredCourseInterface = RegisteredCourseServiceImpl.getInstance();

	/** The admin. */
	Admin admin = null;

	@POST
	@Path("/generateReportCard")
	public Response generateReportCard(@NotNull @FormParam("session") String session,
			@NotNull @FormParam("semester") int semester) {

		ArrayList<Student> registeredStudents = new ArrayList<Student>();
		try {
			registeredStudents = studentInterface.getAllStudents(session);
			if (registeredStudents.size() == 0) {
				return Response.status(200).entity("No student found in the database").build();
			}
			boolean alreadyCreated = false;
			boolean checked = false;
			for (Student student : registeredStudents) {

//				if (!checked) {
//
//					try {
//
//						ArrayList<SemesterReportCard> reportCards = semesterReportCardInterface
//								.getSemesterReportCardByStudentId(student.getStudentID());
//						
//						for (SemesterReportCard semReportCard : reportCards) {
//							if (semReportCard.getCurrentSem() == semester) {
//								alreadyCreated = true;
//							}
//						}
//					} catch (Exception e) {
//						System.out.println("CHECK " + e.getMessage());
//					}
//
//					checked = true;
//				}
//
//				if (alreadyCreated) {
//					
//					return Response.status(200).entity("The Report cards are already generated").build();
//				}

				try {

					ArrayList<RegisteredCourse> courses = registeredCourseInterface.getRegisteredCourses(student.getStudentID(), semester);
					float sgpa = calculateSgpa(courses);
					semesterReportCardInterface.addSemesterReportCard(student.getStudentID(), semester, sgpa);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("GEN " + e.getMessage());
					continue;
				}

			}
		} catch (Exception e) {
			return Response.status(300).entity(e.getMessage()).build();
		}

		return Response.status(200).entity("Report Card generated Successfully").build();
	}

	/**
	 * Calculate sgpa.
	 *
	 * @param courses the courses
	 * @return the float
	 */
	private float calculateSgpa(ArrayList<RegisteredCourse> courses) {
		// Auto-generated method stub
		float totalCredit = 0, totalScore = 0;
		try {
			for (RegisteredCourse temp : courses) {
				CourseCatalog currcourse;
				currcourse = courseCatalogInterface.getCourseCatalog(temp.getCourseId());
				totalCredit += currcourse.getCredits();
				totalScore += currcourse.getCredits() * weightage(temp.getGrade());
			}

			return totalScore / totalCredit;
		} catch (CourseCatalogEntryNotFoundException e) {
			System.out.println(e.getMessage());
			return 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	/**
	 * Weightage.
	 *
	 * @param str the str
	 * @return the int
	 */
	private int weightage(String str) {
		if (str.equals("A"))
			return 10;
		if (str.equals("B"))
			return 9;
		if (str.equals("C"))
			return 8;
		if (str.equals("D"))
			return 7;
		if (str.equals("E"))
			return 6;
		if (str.equals("F"))
			return 5;
		if (str.equals("G"))
			return 4;
		if (str.equals("H"))
			return 3;
		if (str.equals("I"))
			return 2;
		if (str.equals("J"))
			return 1;
		return 0;
	}

	@POST
	@Path("/constants")
	public Response modifyConstants(@NotNull @FormParam("professor") String professor,
			@NotNull @FormParam("student") String student, @NotNull @FormParam("payment") String payment) {

//		hello
		try {
			adminInterface.setCourseRegistrationFlag(student.equalsIgnoreCase(Constants.TRUE));
			adminInterface.setProfessorFlag(professor.equalsIgnoreCase(Constants.TRUE));
			adminInterface.setPaymentFlag(payment.equalsIgnoreCase(Constants.TRUE));

			return Response.status(500).entity("Flags Modified").build();
		} catch (SQLException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/**
	 * Adds the course.
	 */
	@POST
	@Path("/addCourse")
	public Response addCourse(@NotNull @FormParam("courseName") String courseName,
			@NotNull @FormParam("department") String department) {
		// Auto-generated method stub

		try {
			if (adminInterface.addCourse(courseName, department)) {
				return Response.status(200).entity("Course Added Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/**
	 * Removes the course.
	 */
	@POST
	@Path("/removeCourse")
	public Response removeCourse(@NotNull @FormParam("courseId") String courseId) {
		// Auto-generated method stub

		try {
			if (adminInterface.removeCourse(courseId)) {
				return Response.status(200).entity("Course Removed Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/**
	 * Modify course.
	 */
	@POST
	@Path("/modifyCourse")
	public Response modifyCourse(@NotNull @FormParam("courseId") String courseId,
			@NotNull @FormParam("courseName") String courseName, @NotNull @FormParam("department") String department) {
		// Auto-generated method stub

		try {
			if (adminInterface.modifyCourse(courseId, courseName, department)) {
				return Response.status(200).entity("Course Updated Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	/**
	 * Adds the course catalog.
	 */
	@POST
	@Path("/addCourseCatalog")
	public Response addCourseCatalog(@NotNull @FormParam("courseId") String courseId,
			@NotNull @FormParam("semester") int semester, @NotNull @FormParam("session") String session,
			@NotNull @FormParam("credits") float credits) {
		// Auto-generated method stub

		try {
			if (adminInterface.addCourseCatalog(courseId, semester, session, credits, null)) {
				return Response.status(200).entity("Course Added to Catalog").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/**
	 * Removes the course catalog.
	 */
	@POST
	@Path("/removeCourseCatalog")
	public Response removeCourseCatalog(@NotNull @FormParam("courseId") String courseId) {
		// Auto-generated method stub

		try {
			if (adminInterface.removeCourseCatalog(courseId)) {
				return Response.status(200).entity("Course Catalog Removed Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	/**
	 * Modify course catalog.
	 */
	@POST
	@Path("/modifyCourseCatalog")
	public Response modifyCourseCatalog(@NotNull @FormParam("courseId") String courseId,
			@NotNull @FormParam("semester") int semester, @NotNull @FormParam("session") String session,
			@NotNull @FormParam("credits") float credits) {
		// Auto-generated method stub

		try {
			if (adminInterface.modifyCourseCatalog(courseId, semester, session, credits, null)) {
				return Response.status(200).entity("Course Catalog Updated Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	/**
	 * Adds the student.
	 */
	@POST
	@Path("/approveStudent")
	public Response approveStudent(@NotNull @FormParam("studentId") String studentId) {
		// Auto-generated method stub

		try {
			if (adminInterface.editStudentPermission(studentId, true)) {
				return Response.status(200).entity("Student Approved Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/disApproveStudent")
	public Response disApproveStudent(@NotNull @FormParam("studentId") String studentId) {
		// Auto-generated method stub

		try {
			if (adminInterface.editStudentPermission(studentId, false)) {
				return Response.status(200).entity("Student Permission Revoked Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	/**
	 * Adds the professor.
	 */
	@POST
	@Path("/addProfessor")
	public Response addProfessor(@NotNull @FormParam("professorName") String professorName,
			@NotNull @FormParam("department") String department, @NotNull @FormParam("userEmail") String userEmail,
			@NotNull @FormParam("password") String password) {
		// Auto-generated method stub

		try {
			if (adminInterface.addProfessor(professorName, userEmail, password, department)) {
				return Response.status(200).entity("Professor Added Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	/**
	 * Removes the professor.
	 */
	@POST
	@Path("/removeProfessor")
	public Response removeProfessor(@NotNull @FormParam("profId") String profId) {
		// Auto-generated method stub

		try {
			if (adminInterface.removeProfessor(profId)) {
				return Response.status(200).entity("Professor Removed Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	/**
	 * Modify professor.
	 */
	@POST
	@Path("/modifyProfessor")
	public Response modifyProfessor(@NotNull @FormParam("profId") String profId,
			@NotNull @FormParam("professorName") String professorName,
			@NotNull @FormParam("department") String department) {
		// Auto-generated method stub

		try {
			if (adminInterface.modifyProfessor(profId, professorName, department)) {
				return Response.status(200).entity("Professor Updated Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	/**
	 * Gets the all course catalog.
	 *
	 * @return the all course catalog
	 */
	@GET
	@Path("/getAllCourseCatalog")
	public Response getAllCourseCatalog() {
		// Auto-generated method stub
		try {
			ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
			arr = courseCatalogInterface.getAllCourseCatalog();

			return Response.status(200).entity(arr).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	/**
	 * Gets the all courses.
	 *
	 * @return the all courses
	 */
	@GET
	@Path("/getAllCourses")
	public Response getAllCourses() {
		// Auto-generated method stub
		try {
			ArrayList<Course> arr;
			arr = courseInterface.getAllCourses();
			return Response.status(200).entity(arr).build();

		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/**
	 * Gets the all students.
	 *
	 * @return the all students
	 */
	@GET
	@Path("/getAllStudents")
	public Response getAllStudents(@NotNull @QueryParam("session") String session) {
		// Auto-generated method stub
		List<Student> arr = new ArrayList<Student>();
		try {
			arr = studentInterface.getAllStudents(session);
			return Response.status(200).entity(arr).build();
		} catch (Exception e) {
			// Auto-generated catch block
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	@POST
	@Path("/removeStudent")
	public Response removeStudent(@NotNull @FormParam("profId") String studentId) {
		// Auto-generated method stub

		try {
			if (adminInterface.removeStudent(studentId)) {
				return Response.status(200).entity("Student Removed Successfully").build();
			} else {
				return Response.status(200).entity("Something went wrong").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/**
	 * Gets the all professors.
	 *
	 * @return the all professors
	 */
	@GET
	@Path("/getAllProfessors")
	public Response getAllProfessors() {
		// Auto-generated method stub
		ArrayList<Professor> arr = new ArrayList<Professor>();

		try {
			arr = professorInterface.getAllProfessor();
			return Response.status(200).entity(arr).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/*
	 * return Response.status(200).entity(e.getMessage()).build();
	 */

}
