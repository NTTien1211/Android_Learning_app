package com.hqsoft.esales.doancuoiky.Course;

public class Course {

    private String nameCourse;
    private String idVdeo;
    private String descriptionCourse;

    public Course(String nameCourse, String idVdeo, String descriptionCourse) {
        this.nameCourse = nameCourse;
        this.idVdeo = idVdeo;
        this.descriptionCourse = descriptionCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getIdVdeo() {
        return idVdeo;
    }

    public void setIdVdeo(String idVdeo) {
        this.idVdeo = idVdeo;
    }

    public String getDescriptionCourse() {
        return descriptionCourse;
    }

    public void setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
    }
}
