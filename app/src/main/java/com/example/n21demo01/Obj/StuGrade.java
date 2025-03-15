package com.example.n21demo01.Obj;

public class StuGrade {

    private String stuID, stuName, classSubject, gradeType, gradeID;
    private int finalGrade;

    public StuGrade() {
    }

    public StuGrade(String stuID, String stuName, String classSubject, String gradeType, int finalGrade) {
        this.stuID = stuID;
        this.stuName = stuName;
        this.classSubject = classSubject;
        this.gradeType = gradeType;
        this.finalGrade = finalGrade;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getClassSubject() {
        return classSubject;
    }

    public void setClassSubject(String classSubject) {
        this.classSubject = classSubject;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getGradeID() {
        return gradeID;
    }

    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }
}
