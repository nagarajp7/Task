package com.java.tao.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.tao.Entity.Task;
import com.java.tao.model.TaskDetail;
import com.java.tao.model.TaskProgress;
import com.java.tao.model.UserDetail;
import com.java.tao.service.TaskService;

@RestController
public class Controller {
	
	@Autowired
	TaskService taskService;

	@PostMapping("/tasks")
	public String createTask(@Valid @RequestBody TaskDetail taskDetail){
		return taskService.createTask(taskDetail);
	}
	
	@PutMapping("/tasks/{taskId}")
	public String updateTask(@Valid @RequestBody TaskDetail taskDetail, @PathVariable("taskId") Integer taskId){
		return taskService.updateTask(taskDetail, taskId);
	}

	@DeleteMapping("/tasks/{taskId}")
	public String deleteTask(@PathVariable("taskId") Integer taskId) {
		return taskService.deleteTask(taskId);
	}
	
	@GetMapping("/tasks")
	public Map<String, List<Task>> getAllTask() {
		return taskService.getAllTask();
	}
	
	@PostMapping("/tasks/{taskId}/assign")
	public String assignTask(@Valid @RequestBody UserDetail userDetail, @PathVariable("taskId") Integer taskId){
		return taskService.assignTask(userDetail.getUserId(), taskId);
	}
	
	@GetMapping("/users/{userId}/tasks")
	public Map<String, List<Task>> getTasksByUserId(@PathVariable("userId") Integer userId){
		return taskService.getTasksByUserId(userId);
	}
	
	@PutMapping("/tasks/{taskId}/progress")
	public String updateProgress(@Valid @RequestBody TaskProgress taskProgress,@PathVariable("taskId") Integer taskId) {
		return taskService.updateProgress(taskProgress.getProgress(),taskId);
	}
	
	@GetMapping("/tasks/overdue")
	public Map<String, List<Task>> getOverDueTaskList(){
		return taskService.getOverDueTaskList();
	}
	
	@GetMapping("/tasks/status/{status}")
	public Map<String, List<Task>> getTaskListByStatus(@PathVariable("status") String status){
		return taskService.getTaskListByStatus(status);
	}
	
	@GetMapping("/tasks/completed")
	public Map<String, List<Task>> getCompletedTask(@RequestHeader("startDate") Date startDate,@RequestHeader("endDate") Date endDate){
		return taskService.getCompletedTask(startDate,endDate);
	}
	@GetMapping("/tasks/statistics")
	public String getStatistics() throws JsonProcessingException {
		return taskService.getStatistics();
	}
	
}
