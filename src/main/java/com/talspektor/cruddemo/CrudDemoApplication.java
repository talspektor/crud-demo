package com.talspektor.cruddemo;

import com.talspektor.cruddemo.dao.AppDAO;
import com.talspektor.cruddemo.entity.Course;
import com.talspektor.cruddemo.entity.Instructor;
import com.talspektor.cruddemo.entity.InstructorDetail;
import com.talspektor.cruddemo.entity.Review;
import com.talspektor.cruddemo.entity.Student;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
//		return runner -> deleteStudent(appDAO);
//	}

	private void deleteStudent(AppDAO appDAO) {

		int id = 2;

		appDAO.deleteStudentById(id);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {

		int id = 10;

		appDAO.deleteCourseById(id);

		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int id = 2;

		Student student = appDAO.findStudentAndCoursesByStudentId(id);

		Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
		Course tempCourse2 = new Course("Atari 2600 - Game Development");

		student.addCourse(tempCourse1);
		student.addCourse(tempCourse2);

		System.out.println("Updating student: " + student);
		System.out.println("associated courses: " + student.getCourses());

		appDAO.update(student);

		System.out.println("Done!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int id = 1;

		Student student = appDAO.findStudentAndCoursesByStudentId(id);

		System.out.println("student: " + student);
		System.out.println("courses: " + student.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int id = 10;

		Course course = appDAO.findCourseAndStudentByCourseId(id);

		System.out.println("course: " + course);
		System.out.println("students: " + course.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		Course course = new Course("Pacman - How To Score One Million Points");

		Student tempStudent1 = new Student("John1", "Doe1", "main1@mail.com");
		Student tempStudent2 = new Student("John2", "Doe2", "main2@mail.com");

		course.addStudent(tempStudent1);
		course.addStudent(tempStudent2);

		System.out.println("Saving course: " + course);
		System.out.println("associated students: " + course.getStudents());

		appDAO.save(course);

		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int id = 10;

		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		appDAO.deleteCourseById(id);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		int id = 10;

		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		System.out.println("course: " + course);
		System.out.println("reviews: " + course.getReviews());
	}

	private void createCourseAndReview(AppDAO appDAO) {

		Course course = new Course("PCman how to score million point");

		course.addReview(new Review("Great course1"));
		course.addReview(new Review("Great course2"));
		course.addReview(new Review("Great course3"));

		appDAO.save(course);

	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructorDetail id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {

		int id = 10;

		Course course = appDAO.findCourseById(10);

		course.setTitle("TEMP TITLE");

		appDAO.update(course);
	}

	private void updateInstructor(AppDAO appDAO) {

		int id = 1;

		Instructor instructor = appDAO.findInstructorById(id);

		instructor.setLastName("TESTER");

		appDAO.update(instructor);

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int id = 1;

		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("instructor: " + instructor);
		System.out.println("associated courses : " + instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int id = 1;

		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("the instructor: " + instructor);

		System.out.println("Finding courses for instructor id : " + id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);

		System.out.println("the associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int id = 1;

		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("the instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor =
			new Instructor("Madhu1", "Patel1", "madhu1@luv2code.com");

		InstructorDetail tempInstructorDetail =
			new InstructorDetail(
				"http://www.luv2code.com/youtube",
				"Guitar!!!");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("Saving courses: " + tempInstructor.getCourses());

		appDAO.save(tempInstructor);
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {

		int theId = 7;
		System.out.println("Deleting instructorDetail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetailById(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructorDetail id: " + theId);

		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);

		System.out.println("Found instructorDetail: " + instructorDetail);
		System.out.println("the associated instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructorById(AppDAO appDAO) {

		int theId = 6;
		System.out.println("Deleting instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructorById(AppDAO appDAO) {

		int theId = 6;
		System.out.println("Finding instructor id: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Found instructor: " + instructor);
		System.out.println("the associated instructorDetail: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create the objects
//		Instructor tempInstructor =
//			new Instructor("Chad", "Darby", "darby@luv2code.com");
//
//		InstructorDetail tempInstructorDetail =
//			new InstructorDetail(
//				"http://www.luv2code.com/youtube",
//				"Luv 2 code!!!");

		Instructor tempInstructor =
			new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		InstructorDetail tempInstructorDetail =
			new InstructorDetail(
				"http://www.luv2code.com/youtube",
				"Guitar!!!");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}
