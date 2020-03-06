package com.course.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.course.model.Course;

public class CourseDao {
	// springframwork template
	private JdbcTemplate jdbcTemplate;

	// setter for JDBCtemplate
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// query for INSERT course
	public static String INSERT_COURSE = "INSERT INTO COURSETABLE (CourseName,FaculityId,StartDate,EndDate) values (?,?,?,?)";

	// query to DISPLAY course
	public static String DISPLAY_COURSE = "SELECT * FROM COURSETABLE";

	// query to SEARCH (by ID) course
	public static String SEARCH_BY_ID = "SELECT * FROM COURSETABLE WHERE CourseId=?";

	// query to SEARCH (by NAME) course
	public static String SEARCH_BY_NAME = "SELECT * FROM COURSETABLE WHERE CourseName=?";

	// query to UPDATE course
	public static String UPDATE_COURSE = "UPDATE COURSETABLE SET CourseName=?,FaculityId=?,StartDate=?,EndDate=? WHERE CourseId=?";

	// query to DELETE course
	public static String DELETE_COURSE = "DELETE FROM COURSETABLE WHERE CourseId=?";

	// INSERT course
	public void insertCourse(final Course course) {

		jdbcTemplate.execute(INSERT_COURSE,
				new PreparedStatementCallback<Boolean>() {
					SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
					String startDate = ft.format(course.getStartDate());
					String endDate = ft.format(course.getEndDate());

					@Override
					public Boolean doInPreparedStatement(
							PreparedStatement statement) throws SQLException,
							DataAccessException {
						// TODO Auto-generated method stub
						statement.setString(1, course.getCourseName());
						statement.setInt(2, course.getFaculityId());
						statement.setString(3, startDate);
						statement.setString(4, endDate);
						return statement.execute();
					}
				});
	}

	// DISPLAY course
	public List<Course> listCourse() {
		return jdbcTemplate.query(DISPLAY_COURSE, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				// TODO Auto-generated method stub
				Course course = new Course();
				course.setCourseId(rs.getInt("CourseId"));
				course.setCourseName(rs.getString("CourseName"));
				course.setFaculityId(rs.getInt("FaculityId"));
				course.setStartDate(rs.getDate("StartDate"));
				course.setEndDate(rs.getDate("EndDate"));
				return course;
			}

		});
	}

	// SEARCH course(by ID)
	public Course getCourseById(int id) {
		return jdbcTemplate.queryForObject(SEARCH_BY_ID, new Object[] { id },
				new BeanPropertyRowMapper<Course>(Course.class));

	}

	// SEARCH course(by NAME)
	public Course getCourseByName(String courseName) {
		return jdbcTemplate.queryForObject(SEARCH_BY_NAME,
				new Object[] { courseName }, new BeanPropertyRowMapper<Course>(
						Course.class));

	}

	// UPDATE course
	public void updateCourse(final Course course, final int id) {

		jdbcTemplate.execute(UPDATE_COURSE,
				new PreparedStatementCallback<Boolean>() {
					SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
					String startDate = ft.format(course.getStartDate());
					String endDate = ft.format(course.getEndDate());

					@Override
					public Boolean doInPreparedStatement(
							PreparedStatement statement) throws SQLException,
							DataAccessException {
						// TODO Auto-generated method stub
						statement.setString(1, course.getCourseName());
						statement.setInt(2, course.getFaculityId());
						statement.setString(3, startDate);
						statement.setString(4, endDate);
						statement.setInt(5, course.getCourseId());

						return statement.execute();
					}
				});
	}

	// To DELETE course

	public void deleteCourse(final int id) {
		jdbcTemplate.execute(DELETE_COURSE,
				new PreparedStatementCallback<Boolean>() {

					@Override
					public Boolean doInPreparedStatement(
							PreparedStatement statment) throws SQLException,
							DataAccessException {

						statment.setInt(1, id);
						return statment.execute();
					}

				});
	}
}
