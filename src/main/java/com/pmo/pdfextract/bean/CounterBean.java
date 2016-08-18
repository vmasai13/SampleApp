package com.pmo.pdfextract.bean;

public class CounterBean {
	
	public int processedPOs;
	public int nonProcessedPOs;
	public int processedMileStones;
	public int duplicateMileStones;
	public int newMileStones;
	
	public int getNewMileStones() {
		return newMileStones;
	}
	public void setNewMileStones(int newMileStones) {
		this.newMileStones += newMileStones;
	}
	public int getDuplicateMileStones() {
		return duplicateMileStones;
	}
	public void setDuplicateMileStones(int duplicateMileStones) {
		this.duplicateMileStones += duplicateMileStones;
	}
	public int getProcessedPOs() {
		return processedPOs;
	}
	public void setProcessedPOs(int processedPOs) {
		this.processedPOs += processedPOs;
	}
	public int getNonProcessedPOs() {
		return nonProcessedPOs;
	}
	public void setNonProcessedPOs(int nonProcessedPOs) {
		this.nonProcessedPOs += nonProcessedPOs;
	}
	public int getProcessedMileStones() {
		return processedMileStones;
	}
	public void setProcessedMileStones(int processedMileStones) {
		this.processedMileStones += processedMileStones;
	}
	
	

}
