package com.example.diandi.app.model;

public class SayData {
	
	private int id;
	
	private String nowAddress;
	
	private String saySentence;
	
	private String sayEditTime;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public String getNowAddress(){
		return nowAddress;
	}
	
	public void setNowAddress(String nowAddress){
		this.nowAddress=nowAddress;
	}
	
	public String getSaySentence(){
		return saySentence;
	}

	public void setSaySentence(String saySentence){
		this.saySentence=saySentence;
	}
	
	public String getSayEditTime(){
		return sayEditTime;
	}
	
	public void setSayEditTime(String sayEditTime){
		this.sayEditTime=sayEditTime;
	}
}
