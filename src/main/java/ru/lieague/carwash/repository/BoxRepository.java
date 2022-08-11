package ru.lieague.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lieague.carwash.model.entity.Box;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long>, JpaSpecificationExecutor<Box> {

    /**
     * Запрос сначала ищет боксы, которые не смогут выполнить данную услугу во время {@code time},
     * затем выбирает боксы, которые не входят в список занятых, и выбирает из них лучший.
     *
     * Проверки:
     * - смотрим брони только в нужный день
     * - работает ли бокс в это время
     * - пересечется ли по времени выполнение новой работы с уже забронированными работами в этом боксе
     *
     * @param standardDuration - стандартная длительность выполнения данной услуги
     * @param time             - время, запрошенное пользователем для брони
     * @return лучший свободный бокс, способный выполнить указанную услугу в данное время
     */
    @Query(nativeQuery = true, value = "select * " +
            "from boxes box " +
            "where box.is_worked and box.id not in (select box.id" +
            "      from boxes box" +
            "      inner join booking b on box.id = b.box_id" +
            "      where (date(:time) = date(b.washing_start_time)" +
            "         and (b.booking_status = 'ACTIVE' or b.booking_status = 'PAID')" +
            "         and ((:time not between box.start_working and box.end_working)" +
            "           or (time_pl_interval(:time, make_interval(secs \\:= box.coefficient * :duration * 60)) > box.end_working)" +
            "           or (timestamp_pl_interval(:time, make_interval(secs \\:= box.coefficient * :duration * 60)) between b.washing_start_time and b.washing_end_time)" +
            "           or (:time between b.washing_start_time and b.washing_end_time))))" +
            "order by box.coefficient asc " +
            "limit 1")
    Optional<Box> getBestBoxAtThisTime(@Param("duration") Integer standardDuration,
                                       @Param("time") LocalDateTime time);
}
