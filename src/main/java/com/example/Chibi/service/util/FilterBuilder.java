package com.example.Chibi.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilterBuilder<T> {
    private final List<Predicate<T>> filters;

    public FilterBuilder() {
        filters = new ArrayList<>();
    }

    public void add(Predicate<T> filter) {
        this.filters.add(filter);
    }

    public void add(Predicate<T> filter, boolean condition) {
        if (condition) {
            add(filter);
        }
    }

    public void addNotNull(Predicate<T> filter, Object anchor) {
        if (anchor != null) {
            add(filter);
        }
    }

    public List<Predicate<T>> unwrap() {
        return filters;
    }
}
