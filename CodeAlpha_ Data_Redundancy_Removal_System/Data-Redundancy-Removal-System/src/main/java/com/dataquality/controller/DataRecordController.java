package com.dataquality.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dataquality.entity.DataQualityReport;
import com.dataquality.entity.DataRecord;
import com.dataquality.service.IDataQualityService;
import com.dataquality.service.IDataRecordService;

@Controller	
@RequestMapping("/data")
public class DataRecordController {

	@Autowired
	private IDataRecordService service;
	@Autowired
	private IDataQualityService qualityService;
	
	//landing page ui endpoint
	@GetMapping("/")
	public String showHome() {
	    return "index";
	}
	
	//Dashbord mapping end point
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		 DataQualityReport report = qualityService.generateReport();

		    model.addAttribute("report", report);

		    return "dashboard";
	}
	
	//save Record
	@PostMapping("/save")
	public String saveDataRecord(@RequestBody DataRecord record) {
		Integer id = service.saveDataRecord(record);
		return "Data Record Saved with ID :" +id;
		
	}
	
	  // Get All Records
    @GetMapping("/all")
    public List<DataRecord> getAllDataRecords() {

        return service.getAllDataRecords();
    }

    // Get One Record
    @GetMapping("/get/{id}")
    public DataRecord getOneDataRecord(@PathVariable Integer id) {

        return service.getOneDataRecord(id);
    }

    // Delete Record
    @DeleteMapping("/delete/{id}")
    public String deleteDataRecord(@PathVariable Integer id) {

        service.deleteDataRecord(id);

        return "Record Deleted Successfully";
    }
    
    @GetMapping("/records")
    public String viewRecords(Model model) {

        List<DataRecord> list = service.getAllDataRecords();

        model.addAttribute("records", list);
        
        System.out.println(list);

        return "records";
    }

    // Check Duplicate Email
    @GetMapping("/checkEmail")
    public String checkEmail(@RequestParam String email) {

        boolean exists = service.isEmailExists(email);

        if (exists) {
            return "Email already exists";
        } else {
            return "Email is available";
        }
    }

    // Upload CSV File
    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file) {

        try {
            service.processCSV(file);
            
         // run validation after upload
            service.validateRecords();

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/data/dashboard";
    }
    
    //validated 
    @PostMapping("/validate")
    public String validateData() {

        service.validateRecords();

        return "Data validation completed";
    }
	
}
