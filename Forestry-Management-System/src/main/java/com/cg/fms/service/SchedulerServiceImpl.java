package com.cg.fms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.SchedulerDao;
import com.cg.fms.exception.SchedulerException;
import com.cg.fms.model.SchedulerModel;

@Service
public class SchedulerServiceImpl implements ISchedulerService {


	@Autowired
	private SchedulerDao schedulerRepo;
	
	@Autowired
	private EMParser parser;
	
	public SchedulerServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	

	public SchedulerServiceImpl(SchedulerDao schedulerRepo) {
		this.schedulerRepo = schedulerRepo;
		this.parser =new EMParser();
	}
	
	


	public SchedulerDao getSchedulerRepo() {
		return schedulerRepo;
	}



	public void setSchedulerRepo(SchedulerDao schedulerRepo) {
		this.schedulerRepo = schedulerRepo;
	}



	public EMParser getParser() {
		return parser;
	}



	public void setParser(EMParser parser) {
		this.parser = parser;
	}



	@Override
	public SchedulerModel getScheduler(String schedulerId) throws SchedulerException {
		if (!schedulerRepo.existsById(schedulerId))
			throw new SchedulerException("No Scheduler found for the given Id");
		return parser.parse(schedulerRepo.findById(schedulerId).get());
	}

	@Override
	public SchedulerModel addScheduler(SchedulerModel scheduler) throws SchedulerException {
		if (scheduler != null) {
			if (schedulerRepo.existsById(scheduler.getSchedulerId())) {
				throw new SchedulerException("Scheduler with this id already exists");
			}

			scheduler = parser.parse(schedulerRepo.save(parser.parse(scheduler)));
		}

		return scheduler;
	}


	@Override
	public void deleteScheduler(String schedulerId) {
		schedulerRepo.deleteById(schedulerId);
	}

	@Override
	public List<SchedulerModel> getAllSchedulers() {
		return schedulerRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	
	@Override
	public SchedulerModel updateScheduler(String schedulerId, SchedulerModel schedulerModel) throws SchedulerException {
		if (schedulerModel != null) {
			if (schedulerRepo.existsById(schedulerModel.getSchedulerId())) {
				schedulerModel = parser.parse(schedulerRepo.save(parser.parse(schedulerModel)));
			}
			
		}
		return schedulerModel;
	}



	

	



	

}
