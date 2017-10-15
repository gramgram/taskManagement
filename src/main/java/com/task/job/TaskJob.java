package com.task.job;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.task.model.Task;

@Component
public class TaskJob {
	
	private final Logger logger = LoggerFactory.getLogger(TaskJob.class);
	
	/**
	 * Creates and saves a new task in the database every 5 seconds
	 */
	
	@Scheduled(fixedRate = 5000)
	public void createTasks() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://localhost:8080/task/save";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");
		
		JSONObject taskJSON = createRandomTask();
		
		HttpEntity <String> httpEntity = new HttpEntity <String> (taskJSON.toString(), httpHeaders);
		
		Task task = restTemplate.postForObject(url, httpEntity, Task.class);
		
		logger.debug("Created task with ID: " + task.getId());
	}
	
	/**
	 * Creates a new task 
	 * @return JSONObject with task information
	 */
	private JSONObject createRandomTask() {
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		
		String randomString = sb.toString(); 
		
		Task task = new Task();
		
		task.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
		task.setDescription("description of " + randomString);
		task.setPriority(1);
		task.setTitle(randomString);
		task.setStatus("status of " + randomString);
		
		JSONTokener tokener = new JSONTokener(task.toString());
		JSONObject jsonObject = null; 
		
		try {
            jsonObject = new JSONObject(tokener);                
        } catch (JSONException e) {
        	logger.debug("Exception while creating JSON object: " + e.getMessage());
        }
		return jsonObject;
	}

}	
