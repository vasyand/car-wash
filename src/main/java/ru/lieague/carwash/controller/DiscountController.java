package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.discount.DiscountFullDto;
import ru.lieague.carwash.model.dto.discount.DiscountSetMaxDto;
import ru.lieague.carwash.model.dto.discount.DiscountSetMinDto;
import ru.lieague.carwash.service.DiscountService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/discounts")
public class DiscountController {
    private final DiscountService discountService;

    @ResponseStatus(OK)
    @GetMapping
    public DiscountFullDto get() {
        return discountService.get();
    }

    @ResponseStatus(OK)
    @PutMapping("/set-min")
    public DiscountFullDto setMin(@RequestBody @Valid DiscountSetMinDto discountSetMinDto) {
        return discountService.setMin(discountSetMinDto);
    }

    @ResponseStatus(OK)
    @PutMapping("/set-max")
    public DiscountFullDto setMax(@RequestBody @Valid DiscountSetMaxDto discountSetMaxDto) {
        return discountService.setMax(discountSetMaxDto);
    }
}
