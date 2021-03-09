package com.chiaki.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 学生实体类
 * @author chenliang258
 * @date 2021/3/3 11:32
 */
@Data
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = -2630741492557954098L;

    private int id;

    private String name;

    private String sex;

    public Student() {
    }

    public Student(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
