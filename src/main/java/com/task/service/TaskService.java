package com.task.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Task;
import com.task.repo.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskService {

	@Autowired
	private TaskRepository repository;

	/**
	 * Saves task in database
	 * @param Task task
	 * @return Task with new ID
	 */
	
	@Async
	@Transactional
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value="save")
	@ResponseBody
	public CompletableFuture<Task> saveTask(@RequestBody Task task) throws InterruptedException {
	
		Task response =  repository.save(task);
		return CompletableFuture.completedFuture(response);
	}
	
	/**
	 * Fetches task by ID
	 * @param id long
	 * @return Task
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Async
	@RequestMapping(method = RequestMethod.GET, value="fetchById/{id}")
	@ResponseBody
	public Task fetchTaskByID(@PathVariable long id) throws InterruptedException, ExecutionException {
		CompletableFuture<Task> t = repository.findById(id);
		return t.get();
	}

	/**
	 * Fetches all tasks from database
	 * @return List of Task objects
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Async
	@RequestMapping(method = RequestMethod.GET, value="fetchAllTasks")
	@ResponseBody
	public List<Task> fetchAllTasks() throws InterruptedException, ExecutionException {
		CompletableFuture<List<Task>> tasks = repository.findAllTasks();
		return tasks.get();
	}
	
	/**
	 * Updates task in database
	 * @param Task task 
	 * @return Task updated
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Async
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value="update")
	@ResponseBody
	public Task updateTask(@RequestBody Task task) throws InterruptedException, ExecutionException {
		task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		Task response =  repository.save(task);
		return CompletableFuture.completedFuture(response).get();
	}
	
	/**
	 * Deletes task from database
	 * @param id long
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Async
	@RequestMapping(method = RequestMethod.GET, value="delete/{id}")
	public void deleteByID(@PathVariable long id) throws InterruptedException, ExecutionException {
		repository.delete(id);
	}
	
	
}
