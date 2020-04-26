package com.jashburn.designpatterns.creational.registry;

import com.jashburn.designpatterns.creational.factory.GenericFactory;

@FunctionalInterface
public interface Builder<T> {

    /**
     * Register a label for a corresponding factory (supplier).
     */
    void register(String label, GenericFactory<T> factory);
}
