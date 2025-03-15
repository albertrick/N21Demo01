package com.example.n21demo01.Obj;

public class Student {

    private String stuName, stuID;
    private int gradeClass, stuClass;

    public Student (){}
    public Student (int gradeClass, int stuClass, String stuName, String stuID) {
        this.gradeClass = gradeClass;
        this.stuClass = stuClass;
        this.stuName = stuName;
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public int getGradeClass() {
        return gradeClass;
    }

    public void setGradeClass(int gradeClass) {
        this.gradeClass = gradeClass;
    }

    public int getStuClass() {
        return stuClass;
    }

    public void setStuClass(int stuClass) {
        this.stuClass = stuClass;
    }
}
