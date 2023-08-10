package com.java.tao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.tao.Entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{
	
	@Modifying
	@Query("update Task set progress=:progress , completedDate=:completedDate, description=:description, dueDate=:dueDate, status=:status, title=:title where taskId=:taskId")
	void updateTask(Integer taskId, String title, String description, Date dueDate, String status, Date completedDate, Integer progress);

	@Modifying
	@Query("update Task set userId=:userId where taskId=:taskId")
	void assignTask(Integer userId, Integer taskId);

	@Query("from Task where userId =:userId")
	List<Task> getTasksByUserId(Integer userId);

	@Modifying
	@Query("update Task set progress=:progress where taskId=:taskId")
	void updateProgress(Integer progress, Integer taskId);

	@Query("from Task where status ='completed' and startDate >=:startDate and completedDate <=:endDate")
	List<Task> getCompletedTask(Date startDate,Date endDate);

}
