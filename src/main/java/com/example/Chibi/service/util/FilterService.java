package com.example.Chibi.service.util;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterService<T> {
    public List<T> filter(List<T> elements, List<Predicate<T>> filterList) {
        Stream<T> iterator = elements.stream();
        for (Predicate<T> filter : filterList) {
            iterator = iterator.filter(filter);
        }
        return iterator.collect(Collectors.toList());
    }
}
