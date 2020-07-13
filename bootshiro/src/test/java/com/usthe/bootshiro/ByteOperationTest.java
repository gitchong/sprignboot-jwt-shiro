package com.usthe.bootshiro;

import org.junit.Test;

/**
 * @ClassName ByteOperationTest
 * @Description TODO
 * @Author fengxiaoxiao
 * @Date 2020/6/23 19:13
 * @Version 1.0
 */
public class ByteOperationTest {

    @Test
    public void testByte(){
        long refreshPeriodTime = 3600L;
        System.out.println( refreshPeriodTime >> 2);
    }
}
