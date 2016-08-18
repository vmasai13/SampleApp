package com.pmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.pdfextract.bean.BillLogBean;
import com.pmo.persistence.service.BillLogPersistenceService;
import com.pmo.service.PdfExtractor;



@Controller
public class PMOController {

	@Autowired
	PdfExtractor pdfExtract;
	
	@Autowired
	BillLogPersistenceService billLogPersistenceService;
	
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
	
	/**
	 * To process the purchase order's and save it in mongo DB
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/selectPurchaseOrder", method= RequestMethod.GET)
	public String getData(ModelMap model) {
		List<BillLogBean> mileStoneList = pdfExtract.processPurchaseOrder();
		for(BillLogBean mileStone : mileStoneList) {
			//billLogPersistenceService.insert(mileStone);
		}
		return "getPurchaseOrder";
	}
	
	@RequestMapping(value="/getBillLogData", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<BillLogBean> getBillLogData() {
		BillLogBean billLogBean = new BillLogBean();
		List<BillLogBean> output = billLogPersistenceService.findAll();
		System.out.println("Fetched from Database...");
		return output;
	}
/*	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}*/

}