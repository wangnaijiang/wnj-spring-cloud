package com.wnj.util.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {
    private long id;
    private String name;
    private int age;
    public boolean isChild(){
        return age < 18;
    }
}
