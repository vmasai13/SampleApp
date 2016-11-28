package com.pmo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.bean.BillLogBean;
import com.pmo.persistence.service.BillLogPersistenceService;
import com.pmo.persistence.service.SettingsService;
import com.pmo.service.PoProcessingService;

@Controller
public class PMOController {

	@Autowired
	PoProcessingService poProcessingService;
	
	@Autowired
	BillLogPersistenceService billLogPersistenceService;
	
	@Autowired
	SettingsService settingsService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "index";

	}
	
	@RequestMapping(value="/billLog", method=RequestMethod.GET)
	public String getBillLog(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "billLog";
	}
	
	@RequestMapping(value="/billLogEdit", method=RequestMethod.GET)
	public String getBillLogEditable(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "billLogEdit";
	}
	
	
	@RequestMapping(value="/billLogView", method=RequestMethod.GET)
	public String billlogView(ModelMap model) {
		return "billLogView";
	}
	
	@RequestMapping(value="/pieChartForPayment", method=RequestMethod.GET)
	public String pieChartForPayment(ModelMap model) {
		List<BillLogBean> output = billLogPersistenceService.findAll();
		model.addAttribute("Record", output);
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "pieChartForPayment";
	}
	
	/**
	 * To process the purchase order's and save it in mongo DB
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/selectPurchaseOrder", method= RequestMethod.GET)
	public String getData(ModelMap model) {
		List<BillLogBean> mileStoneList = poProcessingService.processPurchaseOrder();
		for(BillLogBean mileStone : mileStoneList) {
			mileStone.setCustomerInvoiceNumber(settingsService.findGetLatestInvoiceNumber(mileStone.getClientInvoiceDate()));
			billLogPersistenceService.insert(mileStone);
		}
		return "getPurchaseOrder";
	}
	
	@RequestMapping(value="/getBillLogData", method = RequestMethod.GET, produces="application/json") // This method is changed to GET in-order to get access to front end calls
	public @ResponseBody Map<String, Object> getBillLogData() {
		Map<String, Object> settingsMap = new HashMap<String, Object>();
		List<BillLogBean> output = billLogPersistenceService.findAll();
		System.out.println("Fetched from Database...");
		settingsMap.put("aaData", output);
		return settingsMap;
	}
/*	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}*/

}