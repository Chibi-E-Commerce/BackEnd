package com.example.Chibi.dto;

import java.util.List;
import java.util.function.Predicate;

public interface Search<T> {
    List<Predicate<T>> breakdown();
}
