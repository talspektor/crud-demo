package com.talspektor.cruddemo.dao;

import com.talspektor.cruddemo.entity.Course;
import com.talspektor.cruddemo.entity.Instructor;
import com.talspektor.cruddemo.entity.InstructorDetail;
import com.talspektor.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO {

    private final EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = findInstructorById(id);

        List<Course> courses = instructor.getCourses();

        courses.forEach(course -> course.setInstructor(null));

        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);

        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query = entityManager.createQuery(
            "from Course where instructor.id = :data", Course.class);

        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        TypedQuery<Instructor> query = entityManager.createQuery(
            "select i from Instructor i " + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDetail " + "where i.id = :data", Instructor.class);

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        TypedQuery<Course> query = entityManager.createQuery(
            "select c from Course c " + "join fetch c.reviews " + "where c.id = :data",
            Course.class);

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = findCourseById(id);

        entityManager.remove(course);
    }

    @Override
    public Course findCourseAndStudentByCourseId(int id) {

        TypedQuery<Course> query = entityManager.createQuery(
            "select c from Course c " + "join fetch c.students " + "where c.id = :data",
            Course.class);

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {

        TypedQuery<Student> query = entityManager.createQuery(
            "select s from Student s " + "join fetch s.courses " + "where s.id = :data",
            Student.class);

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {

        Student student = findStudentAndCoursesByStudentId(id);
        entityManager.remove(student);
    }
}
