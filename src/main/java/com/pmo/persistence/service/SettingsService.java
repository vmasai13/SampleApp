package com.pmo.persistence.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.pmo.bean.Settings;
import com.pmo.persistence.MongoDBPersistenceService;
import com.pmo.utilities.CommonUtility;

@Component
@Repository
public class SettingsService extends MongoDBPersistenceService{
	
	/**
	 * Service method to get the latest invoice number for a particular month.,
	 * @param clientInvoiceDate
	 * @return {@link String} - Latest invoice number for a particular month
	 */
	public String findGetLatestInvoiceNumber(String clientInvoiceDate) {
		String invoiceFormat = CommonUtility.getInvoiceNumberFormat(clientInvoiceDate);
		
		String latestInvoiceNumber = "";
		Settings setting = new Settings();
		Query query = new Query(Criteria.where("settingKey").is(invoiceFormat));
		List<Settings> settingsList = Collections.unmodifiableList(mongoTemplate.find(query, Settings.class, "settings"));
		if (null != settingsList && settingsList.size() > 0) {
			setting = settingsList.get(0);
			latestInvoiceNumber = setting.getSettingValue().substring(0, 7) + CommonUtility.getIncrementalNumber(Integer.parseInt(setting.getSettingValue().substring(7)));
			setting.setSettingValue(latestInvoiceNumber);
		} else {
			setting.setSettingKey(invoiceFormat);
			setting.setSettingValue("KLM" + invoiceFormat + "00001");
		}
		
		// Update the latest invoice number in "settings" table
		if (setting.getSettingValue().contains("00001")) {
			insert(setting);
		} else {
			update(setting);
		}
		
		return setting.getSettingValue();
	}
}
