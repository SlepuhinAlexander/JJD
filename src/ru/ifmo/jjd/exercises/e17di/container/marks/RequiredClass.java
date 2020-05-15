package ru.ifmo.jjd.exercises.e17di.container.marks;

import java.lang.annotation.*;

/*
 * Specifies the targeted class as the required class for DI container.
 * Such class is supposed to be instantiated (only once) and stored in the DI container for further use.
 * Abstract @RequiredClass classes are not instantiated.
 *
 * Each @RequiredClass must have a @Required field of some type marked as @ConfigClass.
 * This @ConfigClass object is supposed to hold all fields and their values for this @RequiredClass class.
 * This @RequiredClass class instances are supposed to act through the reference to corresponding @ConfigClass
 * instances.
 *
 * @RequiredClass class might contain another @Required fields and methods.
 *
 * @RequiredClass class must have an accessible constructor without arguments.
 **/
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredClass {

}
