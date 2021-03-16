package com.desktop.dao;

import com.desktop.entity.SelectSoftware;
import com.desktop.entity.SelectSoftwareExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SelectSoftwareMapper {
    long countByExample(SelectSoftwareExample example);

    int deleteByExample(SelectSoftwareExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SelectSoftware record);

    int insertSelective(SelectSoftware record);

    List<SelectSoftware> selectByExample(SelectSoftwareExample example);

    SelectSoftware selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SelectSoftware record, @Param("example") SelectSoftwareExample example);

    int updateByExample(@Param("record") SelectSoftware record, @Param("example") SelectSoftwareExample example);

    int updateByPrimaryKeySelective(SelectSoftware record);

    int updateByPrimaryKey(SelectSoftware record);
}