package com.cg.fms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.fms.exception.SchedulerException;
import com.cg.fms.model.SchedulerModel;
import com.cg.fms.service.ISchedulerService;

@RestController
@RequestMapping(path="/schedulers")
public class SchedulerAPI {
	
	@Autowired
	private ISchedulerService schedulerService;
	
	
	@PostMapping("/addscheduler")
	public ResponseEntity<SchedulerModel> createAdmin(@RequestBody SchedulerModel scheduler) throws  SchedulerException {
		scheduler = schedulerService.addScheduler(scheduler);
		return new ResponseEntity<>(scheduler, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatescheduler/{schedulerId}")
	public ResponseEntity<SchedulerModel> updateScheduler(@RequestBody SchedulerModel schedulerModel,@PathVariable("schedulerId")String schedulerId) throws SchedulerException{
		schedulerModel = schedulerService.updateScheduler(schedulerModel.getSchedulerId(),schedulerModel);
		return new ResponseEntity<>(schedulerModel, HttpStatus.OK);
	}
	
	@GetMapping("/getallschedulers")
	public ResponseEntity<List<SchedulerModel>> getAll() throws SchedulerException{
		return ResponseEntity.ok(schedulerService.getAllSchedulers());
	}
	
	@GetMapping("/getscheduler/{schedulerId}")
	public ResponseEntity<SchedulerModel> getByAdmin(@PathVariable("schedulerId") String schedulerId) throws SchedulerException{
		return ResponseEntity.ok(schedulerService.getScheduler(schedulerId));
	}
	
	@DeleteMapping("/deletescheduler/{schedulerId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("schedulerId") String schedulerId) throws  SchedulerException {
		ResponseEntity<String> response = null;
		SchedulerModel schedulerModel = schedulerService.getScheduler(schedulerId);
		if (schedulerModel == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			schedulerService.deleteScheduler(schedulerId);
			response = new ResponseEntity<>("Scheduler is deleted successsfully", HttpStatus.OK);
		}
		return response;
	}
	
}
