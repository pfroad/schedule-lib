package com.airparking.schd.annotations;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface APTaskHandler {
    String name() default "";

    String tag();
}