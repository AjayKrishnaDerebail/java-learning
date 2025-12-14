package com.main.section.section7;

import java.util.function.Consumer;
import java.util.function.Predicate;

public record Rule<T>(Predicate<T> condition, Consumer<T> action) {}