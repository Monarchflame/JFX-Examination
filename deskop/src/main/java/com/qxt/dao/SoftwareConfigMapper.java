package com.qxt.dao;

import com.qxt.entity.SoftwareConfig;
import com.qxt.entity.SoftwareConfigExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SoftwareConfigMapper {
    long countByExample(SoftwareConfigExample example);

    int deleteByExample(SoftwareConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SoftwareConfig record);

    int insertSelective(SoftwareConfig record);

    List<SoftwareConfig> selectByExample(SoftwareConfigExample example);

    SoftwareConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SoftwareConfig record, @Param("example") SoftwareConfigExample example);

    int updateByExample(@Param("record") SoftwareConfig record, @Param("example") SoftwareConfigExample example);

    int updateByPrimaryKeySelective(SoftwareConfig record);

    int updateByPrimaryKey(SoftwareConfig record);
}