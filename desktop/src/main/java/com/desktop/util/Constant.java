package com.desktop.util;

import com.desktop.entity.Exam;
import com.desktop.entity.Student;

/**
 * 常量类
 *
 * @author desktop
 * @date 2020/12/7 22:13
 */
public class Constant {
    /**
     * 登录的学生
     */
    public static ThreadLocal<Student> student = new ThreadLocal<>();

    public static void setStudent(Student s) {
        student.set(s);
    }

    public static Student getStudent() {
        return student.get();
    }

    /**
     * 正在进行的考试
     */
    public static ThreadLocal<Exam> exam = new ThreadLocal<>();

    public static void setExam(Exam s) {
        exam.set(s);
    }

    public static Exam getExam() {
        return exam.get();
    }
}
