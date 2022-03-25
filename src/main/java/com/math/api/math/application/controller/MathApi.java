package com.math.api.math.application.controller;

import com.math.api.math.structure.error.ProcessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class MathApi {

    @GetMapping("/max")
    ResponseEntity<Double> maxValeu(@RequestParam("numbers") List<Double> numbers) throws ProcessException {
        return ResponseEntity.ok(numbers.stream().mapToDouble(Double::doubleValue).max().orElseThrow(() -> new ProcessException("Invalid list")));
    }

    @GetMapping("/min")
    ResponseEntity<Double> minValeu(@RequestParam("numbers") List<Double> numbers) throws ProcessException {
        return ResponseEntity.ok(numbers.stream().mapToDouble(Double::doubleValue).min().orElseThrow(() -> new ProcessException("Invalid list")));
    }

    @GetMapping("/avg")
    ResponseEntity<Double> avgValeu(@RequestParam("numbers") List<Double> numbers) throws ProcessException {
        return ResponseEntity.ok(numbers.stream().mapToDouble(Double::doubleValue).average().orElseThrow(() -> new ProcessException("Invalid list")));
    }

    @GetMapping("/median")
    ResponseEntity<Double> medianValeu(@RequestParam("numbers") List<Double> numbers) throws ProcessException{
        int size = numbers.size();
        return ResponseEntity.ok(numbers.stream().mapToDouble(Double::doubleValue).sorted()
                .skip((size-1)/2).limit(2-size%2).average().orElseThrow(() -> new ProcessException("Invalid list")));
    }

    @GetMapping("/percentile")
    ResponseEntity<List<Double>> percentileValeu(@RequestParam("numbers") List<Double> numbers) throws ProcessException{
        int size = numbers.size();
        if (Objects.isNull(numbers) || numbers.isEmpty()) {
            throw new ProcessException("Invalid list");
        }
        return ResponseEntity.ok(numbers.stream()
                .map(value -> {
                    int ordinalRank = BigDecimal.valueOf(value).
                        divide(BigDecimal.valueOf(100))
                        .multiply(BigDecimal.valueOf(size)).intValue();
                    return numbers.get(ordinalRank);
                })
                .collect(Collectors.toList()));
    }
}
