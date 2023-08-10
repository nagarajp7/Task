package com.java.tao.Impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.java.tao.Entity.Task;
import com.java.tao.constants.GenericConstants;
import com.java.tao.model.Statistics;
import com.java.tao.model.TaskDetail;
import com.java.tao.repository.TaskRepository;
import com.java.tao.service.TaskService;

@Service
@Transactional
public class TaskImplementation implements TaskService {

	@Autowired
	TaskRepository taskRepo;

	@Override
	public String createTask(TaskDetail taskData) {
		Task task = new Task();
		task.setTitle(taskData.getTitle());
		task.setDescription(taskData.getDescription());
		task.setDueDate(taskData.getDueDate());
		task.setStartDate(new Date());
		task.setStatus(GenericConstants.CREATED_STATUS);
		task.setProgress(0);
		taskRepo.save(task);
		return GenericConstants.SUCCESS_MSG;
	}

	@Override
	public String updateTask(TaskDetail taskData, Integer taskId) {
		Date completedDate = null;
		Integer progress = 0;
		if (taskData.getStatus().equalsIgnoreCase(GenericConstants.COMPLETED_STATUS)) {
			completedDate = new Date();
			progress = 100;
		}
		taskRepo.updateTask(taskId, taskData.getTitle(), taskData.getDescription(), taskData.getDueDate(),
				taskData.getStatus(), completedDate, progress);
		return GenericConstants.UPDATED_MSG;
	}

	@Override
	public String deleteTask(Integer taskId) {
		taskRepo.deleteById(taskId);
		return GenericConstants.DELETE_MSG;
	}

	@Override
	public Map<String, List<Task>> getAllTask() {
		List<Task> taskList = (List<Task>) taskRepo.findAll();
		return Collections.singletonMap("data", taskList);
	}

	@Override
	public String assignTask(Integer userId, Integer taskId) {
		taskRepo.assignTask(userId, taskId);
		return GenericConstants.ASSIGN_MSG + userId;
	}

	@Override
	public Map<String, List<Task>> getTasksByUserId(Integer userId) {
		List<Task> taskList = taskRepo.getTasksByUserId(userId);
		return Collections.singletonMap("data", taskList);
	}

	@Override
	public String updateProgress(Integer progress, Integer taskId) {
		taskRepo.updateProgress(progress, taskId);
		return GenericConstants.PROGRESS_MSG;
	}

	@Override
	public Map<String, List<Task>> getOverDueTaskList() {
		List<Task> taskList = (List<Task>) taskRepo.findAll();
		// taskList = taskList.stream().filter(taskList.)
		return Collections.singletonMap("data", taskList);
	}

	@Override
	public Map<String, List<Task>> getTaskListByStatus(String status) {
		List<Task> taskList = (List<Task>) taskRepo.findAll();
		taskList = taskList.stream().filter(x -> x.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		return Collections.singletonMap("data", taskList);
	}

	@Override
	public Map<String, List<Task>> getCompletedTask(Date startDate, Date endDate) {
		List<Task> taskList = (List<Task>) taskRepo.getCompletedTask(startDate, endDate);
		return Collections.singletonMap("data", taskList);
	}

	@Override
	public String getStatistics() throws JsonProcessingException {
		Statistics statistics = new Statistics();
		List<Task> taskList = (List<Task>) taskRepo.findAll();
		statistics.setTotalTask(taskList.size());
		taskList = taskList.stream().filter(x -> x.getStatus().equalsIgnoreCase(GenericConstants.COMPLETED_STATUS)).collect(Collectors.toList());
		statistics.setCompletedTask(taskList.size());
		statistics.setPercentage(100.0 * statistics.getCompletedTask()/statistics.getTotalTask());
		Gson gson = new Gson();
	    return gson.toJson(statistics); 
	}

}
