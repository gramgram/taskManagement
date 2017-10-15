package com.task.repo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import com.task.model.Task;


public interface TaskRepository extends CrudRepository<Task, Long>{    
	
	@Async
	CompletableFuture<Task> findById(long id);
	
	@Async
	@Query("select t from Task t")
	CompletableFuture<List<Task>> findAllTasks();
}