package com.desktop.controller;

import com.desktop.dao.SoftwareMapper;
import com.desktop.entity.Software;
import com.desktop.entity.SoftwareExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author qxt
 * @date 2020/12/2 15:09
 */
@Component
public class WinMainAppController {
    @Autowired
    private SoftwareMapper softwareMapper;

    public void getAll() {
        List<Software> softwares = softwareMapper.selectByExample(new SoftwareExample());
        for (Software software : softwares) {
            System.out.println(software.getName());
        }
    }
}
