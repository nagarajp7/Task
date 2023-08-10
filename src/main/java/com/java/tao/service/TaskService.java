package com.java.tao.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.tao.Entity.Task;
import com.java.tao.model.TaskDetail;

@Service
public interface TaskService {
	
	public String createTask(TaskDetail taskDetail);

	public String updateTask(TaskDetail taskDetail, Integer taskId);

	public String deleteTask(Integer taskId);

	public Map<String, List<Task>> getAllTask();

	public String assignTask(Integer userId, Integer taskId);

	public Map<String, List<Task>> getTasksByUserId(Integer userId);

	public String updateProgress(Integer progress, Integer taskId);

	public Map<String, List<Task>> getOverDueTaskList();

	public Map<String, List<Task>> getTaskListByStatus(String status);

	public Map<String, List<Task>> getCompletedTask(Date startDate, Date endDate);

	public String getStatistics()throws JsonProcessingException;

}
