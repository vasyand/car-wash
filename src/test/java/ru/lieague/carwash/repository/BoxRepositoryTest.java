package ru.lieague.carwash.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lieague.carwash.model.entity.Box;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoxRepositoryTest {
    private final static LocalDateTime TEST_DATE = LocalDateTime.of(2022, 8, 5, 19, 27);
    private final static Long BEST_BOX_AT_TEST_DATE_ID = 5L;
    private final static Integer CAR_WASHING_SERVICE_DURATION = 15;

    @Autowired
    private BoxRepository boxRepository;

    @Test
    @DisplayName("поиск лучше бокса в введенное время")
    void getBestBoxAtThisTime_Test() {
        Box box = boxRepository.getBestBoxAtThisTime(CAR_WASHING_SERVICE_DURATION, TEST_DATE).get();
        assertEquals(box.getId(), BEST_BOX_AT_TEST_DATE_ID);
    }
}