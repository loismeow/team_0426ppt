package com.team.sec02;
//교수 클래스
public class Professor {

    //필드
    private String id;
    private String jumin;
    private String name;
    private int department;
    private String grade;
    private String hireDate;

    //기본생성자
    public Professor() {}

    //오버로딩 생성자
    public Professor(String id, String jumin, String name, int department, String grade, String hireDate) {
        this.id = id;
        this.jumin = jumin;
        this.name = name;
        this.department = department;
        this.grade = grade;
        this.hireDate = hireDate;
    }

    //getter/setter 메소드
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getJumin() {
        return jumin;
    }
    public void setJumin(String jumin) {
        this.jumin = jumin;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDepartment() {
        return department;
    }
    public void setDepartment(int department) {
        this.department = department;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getHireDate() {
        return hireDate;
    }
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Professor [id=" + id + ", Jumin=" + jumin + ", name=" + name + ", department=" + department + ", grade="
                + grade + ", hireDate=" + hireDate + "]";
    }



}