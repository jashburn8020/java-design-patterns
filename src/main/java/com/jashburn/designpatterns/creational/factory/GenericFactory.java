package com.jashburn.designpatterns.creational.factory;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface GenericFactory<T> extends Supplier<T> {

    default T newInstance() {
        return get();
    }

    default List<T> create5() {
        return IntStream.range(0, 5).mapToObj(index -> newInstance()).collect(Collectors.toList());
    }

    /**
     * Create a factory that returns new instances.
     */
    static <T> GenericFactory<T> createFactory(Supplier<T> supplier) {
        return () -> supplier.get();
    }

    /**
     * Create a factory that returns a singleton instance.
     * <p>
     * When this static method is called, supplier.get() is called immediately to obtain an instance
     * (from the static method supplier argument), and the instance is provided to the factory (also
     * a supplier) that is returned.
     * <p>
     * Subsequent newInstance() calls on the returned factory will call get() against the factory
     * (which is implemented by () -> singleton), which simply returns the instance that was
     * provided to the factory.
     */
    static <T> GenericFactory<T> createFactoryOfSingleton(Supplier<T> supplier) {
        T singleton = supplier.get();

        return () -> singleton;
    }

    /**
     * Create a factory that returns new instances, instances created with the supplied argument.
     */
    static <T, P> GenericFactory<T> createFactory(Function<P, T> constructor, P argument) {
        return () -> constructor.apply(argument);
    }
}
