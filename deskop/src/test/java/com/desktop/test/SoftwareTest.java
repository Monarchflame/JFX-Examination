package com.desktop.test;

import com.desktop.controller.WinMainAppController;
import com.desktop.dao.SoftwareMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author qxt
 * @date 2020/12/2 15:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftwareTest {
    @Autowired
    private WinMainAppController controller;

    @Test
    public void select() {
        controller.getAll();
    }
}
