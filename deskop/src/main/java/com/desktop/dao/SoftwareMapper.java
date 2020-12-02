package com.desktop.dao;

import com.desktop.entity.Software;
import com.desktop.entity.SoftwareExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SoftwareMapper {
    long countByExample(SoftwareExample example);

    int deleteByExample(SoftwareExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Software record);

    int insertSelective(Software record);

    List<Software> selectByExample(SoftwareExample example);

    Software selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Software record, @Param("example") SoftwareExample example);

    int updateByExample(@Param("record") Software record, @Param("example") SoftwareExample example);

    int updateByPrimaryKeySelective(Software record);

    int updateByPrimaryKey(Software record);
}