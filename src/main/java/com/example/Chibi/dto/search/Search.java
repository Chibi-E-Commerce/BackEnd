package com.example.Chibi.dto.search;

import java.util.List;
import java.util.function.Predicate;

public interface Search<T> {
    List<Predicate<T>> breakdown();
}
