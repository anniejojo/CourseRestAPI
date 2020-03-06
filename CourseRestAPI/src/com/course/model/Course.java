package com.course.model;

import java.sql.Date;



public class Course {
	// instance Variable
	private Integer courseId;
	private String courseName;
	private Integer faculityId;
	private Date startDate;
	private Date endDate;

	// default constructor
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	// getter and setters
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getFaculityId() {
		return faculityId;
	}

	public void setFaculityId(Integer faculityId) {
		this.faculityId = faculityId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
