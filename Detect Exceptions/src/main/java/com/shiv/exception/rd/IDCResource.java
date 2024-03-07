package com.shiv.exception.rd;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface IDCResource {
    String displayName();

    String resourceCode();
    boolean status () default false;
}
