package ru.lieague.carwash.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.lieague.carwash.model.CarWashServiceType;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.model.entity.CarWashService_;
import ru.lieague.carwash.model.filter.CarWashServiceFilter;

import static org.springframework.data.jpa.domain.Specification.where;

public class CarWashServiceSpecification {

    public static Specification<CarWashService> generateSpecification(CarWashServiceFilter carWashServiceFilter) {
        return carWashServiceFilter != null
                ?
                where(findByType(carWashServiceFilter.getCarWashServiceType()))
                        .and(findByDurationGreaterThan(carWashServiceFilter.getDurationGreaterThan()))
                        .and(findByDurationLessThan(carWashServiceFilter.getDurationLessThan()))
                        .and(findByDescription(carWashServiceFilter.getDescription()))
                        .and(findByCostGreaterThan(carWashServiceFilter.getCostGreaterThan()))
                        .and(findByCostLessThan(carWashServiceFilter.getCostLessThan()))
                :
                where(null);
    }

    public static Specification<CarWashService> findByType(CarWashServiceType carWashServiceType) {
        if (carWashServiceType == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(CarWashService_.CAR_WASH_SERVICE_TYPE), carWashServiceType);
    }

    public static Specification<CarWashService> findByDescription(String description) {
        if (description == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(CarWashService_.DESCRIPTION), "%" + description + "%");
    }

    public static Specification<CarWashService> findByDurationGreaterThan(Integer value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get(CarWashService_.DURATION), value);
    }

    public static Specification<CarWashService> findByDurationLessThan(Integer value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get(CarWashService_.DURATION), value);
    }

    public static Specification<CarWashService> findByCostGreaterThan(Double value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get(CarWashService_.COST), value);
    }

    public static Specification<CarWashService> findByCostLessThan(Double value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get(CarWashService_.COST), value);
    }

}
