package com.wnj.util;

import com.google.common.collect.Lists;
import com.wnj.util.bean.Person;

import java.util.List;

public class MockUtil {
    public static Person mockPerson(long id){
        Person person = new Person();
        person.setId(id);
        person.setName("name-"+id);
        person.setAge(RandomUtil.random(10));
        return person;
    }

    public static List<Person> mockPersons(int size){
        List<Person> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            list.add(mockPerson(i));
        }
        return list;
    }
}
