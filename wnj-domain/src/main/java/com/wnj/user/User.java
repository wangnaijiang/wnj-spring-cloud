package com.wnj.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author WangNaiJiang
 * @since 2023-12-21 10:45
 */
@Getter
@Setter
public class User {
    private long id;
    private String name;
    private int age;
    public boolean isChild(){
        return age < 18;
    }
}
