package com.talspektor.cruddemo.dao;

import com.talspektor.cruddemo.entity.Course;
import com.talspektor.cruddemo.entity.Instructor;
import com.talspektor.cruddemo.entity.InstructorDetail;
import com.talspektor.cruddemo.entity.Student;
import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int id);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int id);

    void deleteCourseById(int id);

    Course findCourseAndStudentByCourseId(int id);

    Student findStudentAndCoursesByStudentId(int id);

    void update(Student student);

    void deleteStudentById(int id);
}
