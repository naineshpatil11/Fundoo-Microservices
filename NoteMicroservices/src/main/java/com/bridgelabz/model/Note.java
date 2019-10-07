package com.bridgelabz.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "note")
public class Note {
	@Id
	private String id;
	private String title;
	private String description;
	private LocalDateTime updateTime;
	private LocalDateTime currentTime;
	private boolean isArchive;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public LocalDateTime getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}
	public boolean isArchive() {
		return isArchive;
	}
	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", description=" + description + ", updateTime=" + updateTime
				+ ", currentTime=" + currentTime + ", isArchive=" + isArchive + ", userId=" + userId + "]";
	}
}
