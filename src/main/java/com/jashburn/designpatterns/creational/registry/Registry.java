package com.jashburn.designpatterns.creational.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import com.jashburn.designpatterns.creational.factory.GenericFactory;

@FunctionalInterface
public interface Registry<T> {

    /**
     * When called, this method executes the implementation returned by createRegistry().
     */
    GenericFactory<? extends T> buildShapeFactory(String shape);

    /**
     * Example {@code registration}: {@code builder -> builder.register("triangle", Triangle::new)}
     * 
     * @param <T>
     * @param registration
     * @param errorFunction
     * @return
     */
    public static <T> Registry<T> createRegistry(Consumer<Builder<T>> registration,
            Function<String, GenericFactory<T>> errorFunction) {

        Map<String, GenericFactory<T>> map = new HashMap<>();

        /*
         * Builder is a functional interface, and so the following defines the implementation of its
         * register() method.
         */
        Builder<T> builder = (label, factory) -> map.put(label, factory);
        // accept() executes the call to builder.register(...), which in turn executes the above.
        registration.accept(builder);

        // As Registry is a functional interface, the following is the implementation for the
        // buildShapeFactory() method.
        return shape -> map.computeIfAbsent(shape, errorFunction);
    }
}
