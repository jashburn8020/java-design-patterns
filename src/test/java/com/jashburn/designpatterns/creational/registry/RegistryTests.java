package com.jashburn.designpatterns.creational.registry;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.function.Consumer;
import java.util.function.Function;
import com.jashburn.designpatterns.creational.factory.GenericFactory;
import com.jashburn.designpatterns.creational.model.Rectangle;
import com.jashburn.designpatterns.creational.model.Shape;
import com.jashburn.designpatterns.creational.model.Triangle;
import org.junit.jupiter.api.Test;

class RegistryTests {

    @SuppressWarnings("unchecked")
    @Test
    void testRegistry() {
        Consumer<Builder<Shape>> rectangleRegistration =
                builder -> builder.register("rectangle", Rectangle::new);
        Consumer<Builder<Shape>> triangleRegistration =
                builder -> builder.register("triangle", Triangle::new);

        Consumer<Builder<Shape>> registration = rectangleRegistration.andThen(triangleRegistration);
        Function<String, GenericFactory<Shape>> errorFunction = unknownShape -> {
            throw new IllegalArgumentException("Unknown shape: " + unknownShape);
        };

        Registry<Shape> registry = Registry.createRegistry(registration, errorFunction);

        assertAll(() -> {
            // In general, buildShapeFactory returns a factory of a type of Shape.
            GenericFactory<? extends Shape> rectangleFactory =
                    registry.buildShapeFactory("rectangle");
            Shape rectangle = rectangleFactory.newInstance();
            assertTrue(rectangle instanceof Rectangle);
        }, () -> {
            // Explicitly cast to a specific shape if you somehow know the label (triangle) maps to
            // a corresponding shape (Triangle).
            GenericFactory<Triangle> triangleFactory =
                    (GenericFactory<Triangle>) registry.buildShapeFactory("triangle");
            Triangle triangle = triangleFactory.newInstance();
            assertTrue(triangle.toString().contains("Triangle"));
        });
    }

    @Test
    void buildUnregisteredShape() {
        Consumer<Builder<Shape>> registration =
                builder -> builder.register("triangle", Triangle::new);
        Function<String, GenericFactory<Shape>> errorFunction = unknownShape -> {
            throw new IllegalArgumentException("Unknown shape: " + unknownShape);
        };

        Registry<Shape> registry = Registry.createRegistry(registration, errorFunction);
        assertThrows(IllegalArgumentException.class, () -> registry.buildShapeFactory("square"));
    }
}
