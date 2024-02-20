package com.wnj.mybatis;

import org.junit.Test;

public class IdGeneratorTest {

    @Test
    public void id32() {
        IdGenerator idGenerator = new IdGenerator();
        System.out.println(idGenerator.id32(TableEnum.USER));
    }
}