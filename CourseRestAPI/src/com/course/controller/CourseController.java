package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.course.dao.CourseDao;
import com.course.model.Course;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CourseController {
	
	@Autowired
	private CourseDao courseDao;
	
	//To DISPLAY the courses
	@RequestMapping(value="/course",method=RequestMethod.GET)
	public List getCourseDetails(){
		List list;
		System.out.println("The List of courses");
		list=courseDao.listCourse();
		return list;
		
	}
	
	//To INSERT Courses
		@RequestMapping(value = "/insertcourse", method = RequestMethod.POST)
		public void insertDetails(@RequestBody Course cou) {
			courseDao.insertCourse(cou);
		}
	
	
		// To SEARCH by id
		@RequestMapping(value = "/coursebyid/{id}", method = RequestMethod.GET)
		public Course getEmployee(@PathVariable("id") int id) {

			System.out.println("single course details");
			Course course = courseDao.getCourseById(id);
			return course;
		}
		
		//To SEARCH by Name
		
		@RequestMapping(value = "/coursebyname/{courseName}", method = RequestMethod.GET)
		public Course getCourse(@PathVariable("courseName") String courseName) {

			System.out.println("single course details");
			Course course = courseDao.getCourseByName(courseName);
			return course;
		}
		
		//To UPDATE course
		@RequestMapping(value = "/updatecourse/{id}", method = RequestMethod.PUT)
		public void updateDetails(@RequestBody Course cou,
				@PathVariable("id") int id) {

			courseDao.updateCourse(cou, id);
		}
		
		//To DELETE course
		@RequestMapping(value = "/deletecourse/{id}", method = RequestMethod.DELETE)
		public void deleteDetails(@PathVariable("id") int id) {
			courseDao.deleteCourse(id);
		}

}
