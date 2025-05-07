package NotModified.TimeTable.repository.jpa;

import NotModified.TimeTable.domain.TimeTable;
import NotModified.TimeTable.repository.interfaces.TimeTableRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaTimeTableRepository implements TimeTableRepository {
    private final EntityManager em;

    public JpaTimeTableRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public TimeTable save(TimeTable timeTable) {
        em.persist(timeTable);
        return timeTable;
    }

    @Override
    public List<TimeTable> findAll(String userId) {
        return em.createQuery("select t from TimeTable t where t.userId = :userId", TimeTable.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Optional<TimeTable> findById(Long id) {
        TimeTable timeTable = em.find(TimeTable.class, id);
        return Optional.ofNullable(timeTable);
    }
}

