package com.task.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="task")
public class Task {
	
	/*@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;*/
	
	private static final String TIMESTAMP_FORMAT= "yyyy-MM-dd HH:mm:ss.SSS";
	
	@GenericGenerator(
            name = "taskSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "taskSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
	@Column(name = "id")
	@Id
    @GeneratedValue(generator = "taskSequenceGenerator")
	private long id;
	
	@JsonFormat(pattern=TIMESTAMP_FORMAT)
	@Column(name = "createdAt")
	private Timestamp createdAt;
	
	@JsonFormat(pattern=TIMESTAMP_FORMAT)
	@Column(name = "updatedAt")
	private Timestamp updatedAt;
	
	@JsonFormat(pattern=TIMESTAMP_FORMAT)
	@Column(name = "dueAt")
	private Timestamp dueAt;
	
	@JsonFormat(pattern=TIMESTAMP_FORMAT)
	@Column(name = "resolvedAt")
	private Timestamp resolvedAt;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "priority")
	private int priority;
	
	@Column(name = "status")
	private String status;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getDueAt() {
		return dueAt;
	}

	public void setDueAt(Timestamp dueAt) {
		this.dueAt = dueAt;
	}

	public Timestamp getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(Timestamp resolvedAt) {
		this.resolvedAt = resolvedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Task() {
		
	}
	
	public Task(long id, Timestamp createdAt, Timestamp updatedAt, Timestamp dueAt, Timestamp resolvedAt, 
			String title, String description, int priority, String status) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.dueAt = dueAt;
		this.resolvedAt = resolvedAt;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		 return "{id:" + id + ", createdAt:'" + createdAt + "', updatedAt:" + updatedAt +
				 ", dueAt:" + dueAt + ", resolvedAt:" + resolvedAt + ", title:'" + title +
				 "', description:'" + description + "', priority:" + priority + 
				 ", status:'" + status + "'}";
	}
	
	

}
