package NotModified.TimeTable.repository.jpa;

import NotModified.TimeTable.domain.Course;
import NotModified.TimeTable.repository.interfaces.CourseRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCourseRepository implements CourseRepository {
    private final EntityManager em;

    public JpaCourseRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Course save(Course course) {
        em.persist(course);
        return course;
    }

    @Override
    public List<Course> findAll(String userId) {
        return em.createQuery("select c from Course c where c.userId = :userId", Course.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Optional<Course> findById(Long id) {
        Course course = em.find(Course.class, id);
        return Optional.ofNullable(course);
    }
}
