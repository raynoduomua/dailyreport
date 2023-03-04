package com.example.dailyreport.infrastructure.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dailyreport.infrastructure.entity.admin.Course;

public interface AdminCourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByCourseName(String courseName);

}
