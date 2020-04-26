# Implementing Design Patterns Using Java 8 Lambda

- From the course on [Pluralsight](https://app.pluralsight.com/library/courses/implementing-design-patterns-java-8-lambda-expression/) by José Paumard

## From Factory and Registry to Builder Using Lambda Expressions

### Factory

See:

- [`creational/factory/CircleFactory.java`](src/main/java/com/jashburn/designpatterns/creational/factory/CircleFactory.java)
- [`creational/factory/CircleFactoryTests.java`](src/test/java/com/jashburn/designpatterns/creational/factory/CircleFactoryTests.java)
- [`creational/factory/GenericFactory.java`](src/main/java/com/jashburn/designpatterns/creational/factory/GenericFactory.java)
- [`creational/factory/GenericFactoryTests.java`](src/test/java/com/jashburn/designpatterns/creational/factory/GenericFactoryTests.java)

### Registry

- A registry has the same role as factory - to build other objects
- The difference is a registry works with a key
  - pass the key to a factory method to create the instance of the object that you need
- Adding elements dynamically to a registry can be achieved using the builder pattern
  - once elements are added to the registry, it needs to be sealed to prevent further elements being added to it

See:

- [`creational/registry/Builder.java`](src/main/java/com/jashburn/designpatterns/creational/registry/Builder.java)
- [`creational/registry/Registry.java`](src/main/java/com/jashburn/designpatterns/creational/registry/Registry.java)
- [`creational/registry/RegistryTests.java`](src/test/java/com/jashburn/designpatterns/creational/registry/RegistryTests.java)
