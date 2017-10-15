package com.task.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="task")
public class Task {
	
	/*@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;*/
	
	
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
	
	
	@Column(name = "createdAt")
	private Date createdAt;
	
	@Column(name = "updatedAt")
	private Date updatedAt;
	
	@Column(name = "dueAt")
	private Date dueAt;
	
	@Column(name = "resolvedAt")
	private Date resolvedAt;
	
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDueAt() {
		return dueAt;
	}

	public void setDueAt(Date dueAt) {
		this.dueAt = dueAt;
	}

	public Date getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(Date resolvedAt) {
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
	
	public Task(long id, Date createdAt, Date updatedAt, Date dueAt, Date resolvedAt, 
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
