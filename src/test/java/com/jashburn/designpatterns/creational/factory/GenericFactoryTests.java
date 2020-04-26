package com.jashburn.designpatterns.creational.factory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.awt.Color;
import java.util.List;
import com.jashburn.designpatterns.creational.factory.model.Circle;
import org.junit.jupiter.api.Test;

class GenericFactoryTests {

    @Test
    void factorySingleInstance() {
        GenericFactory<Circle> factory = () -> new Circle();
        Circle circle = factory.newInstance();

        assertTrue(circle instanceof Circle);
    }

    @Test
    void factoryMultipleInstances() {
        GenericFactory<Circle> factory = () -> new Circle();
        List<Circle> circles = factory.create5();

        assertEquals(5, circles.size());
        circles.forEach(circle -> assertTrue(circle instanceof Circle));
    }

    @Test
    void createFactoryDifferentInstances() {
        GenericFactory<Circle> circleFactory = GenericFactory.createFactory(Circle::new);
        Circle circle = circleFactory.newInstance();

        assertAll(() -> {
            assertTrue(circle instanceof Circle);
        }, () -> {
            assertNotSame(circle, circleFactory.newInstance());
        });
    }

    @Test
    void createFactorySingletonInstance() {
        GenericFactory<Circle> singletonCircleFactory =
                GenericFactory.createFactoryOfSingleton(Circle::new);
        Circle circle = singletonCircleFactory.newInstance();

        assertAll(() -> {
            assertTrue(circle instanceof Circle);
        }, () -> {
            assertSame(circle, singletonCircleFactory.newInstance());
        });
    }

    @Test
    void createFactoryWithColor() {
        GenericFactory<Circle> redCircleFactory =
                GenericFactory.createFactory(Circle::new, Color.RED);

        assertTrue(redCircleFactory.newInstance().isColor(Color.RED));

    }
}
