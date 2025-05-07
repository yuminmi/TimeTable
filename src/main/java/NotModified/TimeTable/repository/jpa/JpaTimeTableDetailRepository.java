package NotModified.TimeTable.repository.jpa;

import NotModified.TimeTable.domain.TimeTableDetail;
import NotModified.TimeTable.repository.interfaces.TimeTableDetailRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaTimeTableDetailRepository implements TimeTableDetailRepository {
    private final EntityManager em;

    public JpaTimeTableDetailRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public TimeTableDetail save(TimeTableDetail timeTableDetail) {
        em.persist(timeTableDetail);
        return timeTableDetail;
    }

    @Override
    public List<TimeTableDetail> findAll(Long timeTableId) {
        return em.createQuery("select td from TimeTableDetail td " +
                                "where td.timeTable.id = :timeTableId", TimeTableDetail.class)
                                .setParameter("timeTableId", timeTableId)
                                .getResultList();
    }
}
