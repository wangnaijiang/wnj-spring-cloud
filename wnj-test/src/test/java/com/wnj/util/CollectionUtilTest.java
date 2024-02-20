package com.wnj.util;

import org.junit.jupiter.api.Test;
import com.wnj.util.bean.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionUtilTest {
    @Test
    public void toMap(){
        List<Person> persons = MockUtil.mockPersons(10);
        Map<Long, String> idNameMap = CollectionUtil.toStream(persons)
                .collect(CollectionUtil.toMap(Person::getId, p -> p.getName(), "id重复"));

        System.out.println(idNameMap);

        persons.add(MockUtil.mockPerson(5));
        idNameMap = CollectionUtil.toStream(persons)
                .collect(CollectionUtil.toMap(Person::getId, p -> p.getName(), "id重复"));

        System.out.println(idNameMap);
    }

    @Test
    public void sort(){
        List<Person> persons = MockUtil.mockPersons(10);
        CollectionUtil.sort(persons, Comparator.comparing(Person::getId).reversed());
        System.out.println(persons);

        CollectionUtil.sort(persons
                ,(p1,p2) ->{
                    if(p1.getAge() > p2.getAge()){
                        return 1;
                    }else if(p1.getAge() < p2.getAge()){
                        return -1;
                    }else{
                        return 0;
                    }
                });
        System.out.println(persons);

    }

    @Test
    public void isSeen(){
        List<Person> persons = MockUtil.mockPersons(10);
        CollectionUtil.toStream(persons)
                .filter(Person::isChild) // Predicate Boolean的Function
                .collect(Collectors.toList());

        //不会改变persons的值
        List<Person> personList = CollectionUtil.toStream(persons)
                .filter(CollectionUtil.isSeen(Person::getAge)) // Function
                .collect(Collectors.toList());

        System.out.println(persons);
        System.out.println(personList);
    }
}
