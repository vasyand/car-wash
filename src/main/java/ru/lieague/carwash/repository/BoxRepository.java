package ru.lieague.carwash.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lieague.carwash.model.entity.Box;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {

    /**
     * Запрос сначала ищет боксы, которые могут выполнить данную услугу во время @time
     * (проверка, пересечется ли по времени выполнение новой работы с уже заброннированными работами),
     * затем из этих боксов выбирается тот, который быстрее всех сделает эту услугу
     * @param standardDuration - стандартная длительность выполнения данной услуги
     * @param time - время, зарпошенное пользователем для брони
     * @return лучший бокс, способный выполнить указанную услугу в данное время
     */
    @Query (nativeQuery = true, value = "select * from boxes box " +
            "         inner join booking b on box.id = b.box_id " +
            "where (b.washing_start_time not between date (:time) " +
            "+  box.coefficient * :duration * interval '1 minute' and :time) " +
            "  and (b.washing_end_time not between date (:time) " +
            "+  box.coefficient * :duration * interval '1 minute' and :time) " +
            "order by coefficient desc " +
            "limit 1")
    Optional<Box> getBestBoxAtThisTime(@Param("duration") Integer standardDuration,
                                       @Param("time") LocalDateTime time);
}
