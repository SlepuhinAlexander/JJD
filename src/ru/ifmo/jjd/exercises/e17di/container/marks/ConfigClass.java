package ru.ifmo.jjd.exercises.e17di.container.marks;

import java.lang.annotation.*;

/*
 * Specifies the targeted class as a configuration holder for corresponding @Required class.
 * A @ConfigClass class would be single-instantiated in DI container.
 * An instance of targeted class is supposed to hold all fields for corresponding @Required class, while the @Required
 * class itself would have only a reference to this @ConfigClass.
 *
 * Abstract @ConfigClass classes are not instantiated.
 *
 * All fields in @ConfigClass class are considered @Required meaning they must have been initialized.
 * Initial values for the fields are taken from the properties-file set in file() parameter.
 *
 * @ConfigClass class must have an accessible constructor without arguments.
 *
 * @param file() sets the absolute or relative path to properties-file containing actual values for fields in this
 *              class.
 * @param prefix() sets the string prefix in keys stored in this properties-file. The prefix is added to this class
 *              fields' names when looking up for values in the properties-file.
 **/
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigClass {
    String file() default "";
    String prefix() default "";
}
