package com.wjf.beacontower.util;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author xiaom
 * Time: 2020/3/13 8:51
 * Author: Lin.Li
 * Description:
 */
public final class OptionalUtil<T> {
    /**
     * Common instance for {@code empty()}.
     */
    private static final OptionalUtil<?> EMPTY = new OptionalUtil<>();

    /**
     * If non-null, the value; if null, indicates no value is present
     */
    private final T value;

    private OptionalUtil() {
        this.value = null;
    }

    public static<T> OptionalUtil<T> empty() {
        @SuppressWarnings("unchecked")
        OptionalUtil<T> t = (OptionalUtil<T>) EMPTY;
        return t;
    }
    private OptionalUtil(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> OptionalUtil<T> of(T value) {
        return new OptionalUtil<>(value);
    }

    public static <T> OptionalUtil<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public boolean isPresent() {
        return value != null;
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

}
