package com.jashburn.designpatterns.creational.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import com.jashburn.designpatterns.creational.model.Circle;
import org.junit.jupiter.api.Test;

public class CircleFactoryTests {

    @Test
    void factorySingleInstance() {
        CircleFactory factory = () -> new Circle();
        Circle circle = factory.newInstance();

        assertTrue(circle instanceof Circle);
    }

    @Test
    void factoryMultipleInstances() {
        CircleFactory factory = () -> new Circle();
        List<Circle> circles = factory.create5Circles();

        assertEquals(5, circles.size());
        circles.forEach(circle -> assertTrue(circle instanceof Circle));
    }
}
