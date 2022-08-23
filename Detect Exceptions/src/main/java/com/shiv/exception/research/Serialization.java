package com.shiv.exception.research;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class Serialization implements Serializable {
    private int id;
    private String name;
    private String dept;
}

