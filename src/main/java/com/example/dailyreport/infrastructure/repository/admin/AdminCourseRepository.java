package com.example.dailyreport.infrastructure.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.admin.Course;

@Repository
public interface AdminCourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByCourseName(String courseName);

	public long count();

}
