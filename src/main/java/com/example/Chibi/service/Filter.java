package com.example.Chibi.service;

import com.example.Chibi.service.util.FilterService;

import java.util.List;
import java.util.function.Predicate;


public abstract class Filter {
    public static <T> List<T> applyFilters(List<T> models, List<Predicate<T>> filter_list) {
        FilterService<T> filterService = new FilterService<>();
        return filterService.filter(models, filter_list);
    }

    public static <T> boolean follows(List<T> models, List<Predicate<T>> filter_list) {
        FilterService<T> filterService = new FilterService<>();
        return filterService.follows(models, filter_list);
    }
}
