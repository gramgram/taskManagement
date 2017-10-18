package com.task.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.model.Task;
import com.task.repo.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE )
public class TaskServiceTest {
	
	@Autowired
	private TaskRepository repository;
	
	
	Task mockTask = new Task(1, new java.sql.Timestamp(System.currentTimeMillis()), 
			null, null, null, "test title", "test desc", 2, "test status" );
	
	
	@Test
	public void saveTask() {
		Task task = repository.save(mockTask);

		assertThat(task).hasFieldOrPropertyWithValue("id", 1L);
	}
	
	@Test
	public void retriveTaskByID() throws Exception {
		Task task1 = repository.save(mockTask);
		Task task2 = repository.findById(task1.getId()).get();
		
		assertThat(task1).isEqualTo(task2);
	}
	
/*	@Test
	public void deleteTask() throws Exception {
		Task task1 = repository.save(mockTask);
		repository.delete(task1.getId());
		
		assertThat(repository.findById(task1.getId()).get()==null);
	}*/
}
