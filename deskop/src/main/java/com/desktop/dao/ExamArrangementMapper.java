package com.desktop.dao;

import com.desktop.entity.ExamArrangement;
import com.desktop.entity.ExamArrangementExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ExamArrangementMapper {
    long countByExample(ExamArrangementExample example);

    int deleteByExample(ExamArrangementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExamArrangement record);

    int insertSelective(ExamArrangement record);

    List<ExamArrangement> selectByExample(ExamArrangementExample example);

    ExamArrangement selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExamArrangement record, @Param("example") ExamArrangementExample example);

    int updateByExample(@Param("record") ExamArrangement record, @Param("example") ExamArrangementExample example);

    int updateByPrimaryKeySelective(ExamArrangement record);

    int updateByPrimaryKey(ExamArrangement record);
}