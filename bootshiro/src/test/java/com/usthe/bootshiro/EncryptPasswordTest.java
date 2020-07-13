package com.usthe.bootshiro;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName EncryptPasswordTest
 * @Description TODO
 * @Author fengxiaoxiao
 * @Date 2020/6/23 16:24
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptPasswordTest {

    @Autowired
    private StringEncryptor jasyptStringEncryptor;

    @Test
    public void encrypt() {
        String encryptStr = jasyptStringEncryptor.encrypt("root");
        System.out.println(encryptStr);
    }

    @Test
    public void decrypt() {
        String encryptStr = jasyptStringEncryptor.decrypt("qXbfhq9uzyXSgFm0I7b7aj+E0VrWqkyBjG6O/ZNUQnM4YsdYQVwea660VIitJk9N");
        System.out.println(encryptStr);
    }

    @Test
    public void stand(){
        //StandardPBEStringEncryptor
    }

}
