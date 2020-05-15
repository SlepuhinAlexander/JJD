package ru.ifmo.jjd.exercises.e17di.container.marks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * This annotation is used only in classes marked as @RequiredClass or @ConfigClass.
 * Other classes by default would ignore the @Required annotation.
 *
 * All fields in a @ConfigClass class are considered @Required at once.
 *
 * @Required field is supposed to be initialized as soon as the declaring @RequiredClass class is instantiated.
 * Initial values for the @Required field are taken from the @ConfigClass, corresponding to the declaring
 * @RequiredClass.
 *
 * @Required method indicates a setter-method that must be invoked for initializing the @Required field.
 * If a @Required field has a corresponding @Required setter method, the setter method is invoked with initial value as
 * it's argument.
 * Otherwise a @Required field would have been assigned an initial value directly, ignoring a setter method if any.
 *
 * Other @Required methods are ignored by default.
 * */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
}
