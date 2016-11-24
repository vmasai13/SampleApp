package com.pmo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.bean.BillLogBean;
import com.pmo.handler.BillLogHandler;
import com.pmo.persistence.service.BillLogService;
import com.pmo.service.InvoiceCreationService;
import com.pmo.utilities.CommonUtility;

@Controller
@RequestMapping(value = "/billlogtableentry", produces = "application/json")
public class BillLogController {

	@Autowired
	BillLogService billLogService;

	@Autowired
	InvoiceCreationService invoiceCreationService;
	
	@Autowired
	BillLogHandler billLogHandler;

	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> update(@ModelAttribute
			BillLogBean setting) {
		Map<String, Object> billLogMap = new HashMap<String, Object>();
		billLogMap.put("Record", billLogService.save(setting));
		System.out.println("Saved one item - " + setting.getPoNumber() + "|" + setting.getItem() + "|" + setting.getQuantity() + " to database");
		billLogMap.put("Result", "OK");
		return billLogMap;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(int jtStartIndex, int jtPageSize, String jtSorting) {
		Map<String, Object> billLogMap = new HashMap<String, Object>();
		List<BillLogBean> billLogList = new ArrayList<BillLogBean>();
		billLogList.addAll(billLogService.list(jtStartIndex, jtPageSize, jtSorting));
		System.out.println("Received - " + billLogList.size() + " items from database");
		billLogMap.put("Records", billLogList);
		billLogMap.put("Result", "OK");
		return billLogMap;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam
			String id) {
		Map<String, Object> billLogMap = new HashMap<String, Object>();
		billLogService.delete(id);
		System.out.println("Deleted  the item with - " + id + " from database");
		billLogMap.put("Result", "OK");
		return billLogMap;
	}

	@RequestMapping(value = "/new")
	@ResponseBody
	public Map<String, Object> create(BillLogBean setting) {
		Map<String, Object> billLogMap = new HashMap<String, Object>();
		billLogMap.put("Record", billLogService.save(setting));
		System.out.println("New -> "+ setting.getPoNumber() + "|" + setting.getItem() + "|" + setting.getQuantity() + "1 New item saved in database");
		billLogMap.put("Result", "OK");
		return billLogMap;
	}

	@RequestMapping(value = "/createInvoice", method= RequestMethod.GET ) 
	@ResponseBody
	public Map<String, Object> createInvoice(@RequestParam String selectedIDs) {
		Map<String, Object> billLogMap = new HashMap<String, Object>();
		String selectedId[] = selectedIDs.split(",");
		String clientInvoiceNumber = "";
		String resultStatus = "";
		for (String id : selectedId) {
			List<BillLogBean> billLogList = billLogService.checkId(id);
			for (BillLogBean billLogBean : billLogList) {
				if (CommonUtility.checkPreConditionForCreateInvoice(billLogBean)) {
					if (invoiceCreationService.createInvoice(billLogBean)) {
						billLogBean.setInvoiceStatus("Submitted");
						billLogService.save(billLogBean);
					}
					clientInvoiceNumber = clientInvoiceNumber + billLogBean.getCustomerInvoiceNumber();
				}
			}
		}
		billLogMap.put("ClientInvoiceNumber", clientInvoiceNumber);
		billLogMap.put("Result", "OK");
		return billLogMap;
	}
	
	@RequestMapping(value = "/copyMilestone", method= RequestMethod.GET ) 
	@ResponseBody
	public Map<String, Object> copyMilestone(@RequestParam String selectedIDs) {
		Map<String, Object> billLogMap = new HashMap<String, Object>();
		String selectedId[] = selectedIDs.split(",");
		String resultStatus = "";
		for (String id : selectedId) {
			List<BillLogBean> billLogList = billLogService.checkId(id);
			for (BillLogBean billLogBean : billLogList) {
				BillLogBean newBillLogBean = billLogHandler.checkAndCreateNewMilestone(billLogBean);
				billLogMap.put("Record", billLogService.save(newBillLogBean));
				resultStatus = resultStatus + newBillLogBean.getPoNumber() + ", ";
			}
		}
		billLogMap.put("Result", "Added new row for PO's : " + resultStatus);
		return billLogMap;
	}
	
	@RequestMapping(value = "/deleteMilestone", method= RequestMethod.GET ) 
	@ResponseBody
	public Map<String, Object> deleteMilestone(@RequestParam String selectedIDs) {
		Map<String, Object> billLogMap = new HashMap<String, Object>();
		String selectedId[] = selectedIDs.split(",");
		String resultStatus = "";
		for (String id : selectedId) {
			billLogService.delete(id);
		}
		System.out.println(selectedId.length + " - items are deleted from database");
		billLogMap.put("Result", selectedId.length + " - item(s) deleted from database");
		return billLogMap;
	}

	@RequestMapping(value = "/get")
	@ResponseBody
	public Map<String, Object> listGet() {
		Map<String, Object> billLogMap = new HashMap<String, Object>();
		List<BillLogBean> billLogList = new ArrayList<BillLogBean>();
		billLogList.addAll(billLogService.listGet());
		billLogMap.put("Records", billLogList);
		billLogMap.put("Result", "OK");
		return billLogMap;
	}

}
