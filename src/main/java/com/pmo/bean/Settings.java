package com.pmo.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import com.pmo.persistence.AbstractDatedDBRecord;

@Document(collection = "settings")
public class Settings extends AbstractDatedDBRecord{

	private String settingKey;
	private String settingValue;

	public String getSettingKey() {
		return settingKey;
	}

	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}
}
