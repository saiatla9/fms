package com.cg.fms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.fms.dao.ContractDao;
import com.cg.fms.dao.CustomerDao;
import com.cg.fms.dao.OrderDao;
import com.cg.fms.dao.SchedulerDao;
import com.cg.fms.entity.Admin;
import com.cg.fms.entity.Contract;
import com.cg.fms.entity.Customer;
import com.cg.fms.entity.Land;
import com.cg.fms.entity.Order;
import com.cg.fms.entity.Product;
import com.cg.fms.entity.Scheduler;
import com.cg.fms.model.AdminModel;
import com.cg.fms.model.ContractModel;
import com.cg.fms.model.CustomerModel;
import com.cg.fms.model.LandModel;
import com.cg.fms.model.OrderModel;
import com.cg.fms.model.ProductModel;
import com.cg.fms.model.SchedulerModel;

@Service
public class EMParser {
	
	
	@Autowired
	private CustomerDao customerRepo;
	
	@Autowired
	private ContractDao contractRepo;
	
	@Autowired
	private SchedulerDao schedulerRepo;
	
	@Autowired
	private OrderDao orderRepo;
	
	
	public AdminModel parse(Admin source) {
		return source==null?null:
			new AdminModel(source.getAdminId(),
						source.getAdminName(),
						source.getAdminPassword());
			
	}
	
	public Admin parse(AdminModel source) {
		return source==null?null:
			new Admin(source.getAdminId(),
						source.getAdminName(),
						source.getAdminPassword());

	}
	
	public ContractModel parse(Contract source) {
		return source==null?null : 
			new ContractModel (source.getContractNumber(),
					source.getDeliveryDate(),source.getDeliveryPlace(),source.getQuantity(),
					source.getScheduler().getSchedulerId(),
					source.getCustomer().getCustomerId());
		
	}
	
	public Contract parse(ContractModel source) {
		return source==null?null : 
			new Contract (source.getContractNumber(),
					source.getDeliveryDate(),source.getDeliveryPlace(),source.getQuantity(),
					schedulerRepo.findById(source.getScheduler()).orElse(null), 
					customerRepo.findById(source.getCustomer()).orElse(null));
		
	}
	
	public CustomerModel parse(Customer source) {
		return source==null?null : 
			new CustomerModel (source.getCustomerId(),
					source.getCustomerName(),source.getCustomerPassword(),source.getCustomerEmail(),
					source.getCustomerAddress(),
					source.getCustomerTown(),
					source.getCustomerPostalCode(),
//					source.getContract().toString(),
//					source.getOrder().toString(),
					source.getCustomerContact());
	}
	
	public Customer parse(CustomerModel source) {
		return source==null?null : 
			new Customer (source.getCustomerId(),source.getCustomerName(),source.getCustomerPassword(),source.getCustomerEmail(),
					source.getCustomerAddress(),
					source.getCustomerTown(),
					source.getCustomerPostalCode(),
					source.getCustomerContact());
	}
	
	public OrderModel parse(Order source) {
		return source == null?null:
			new OrderModel(source.getOrderNumber(),source.getDeliveryDate(),source.getDeliveryPlace(),
					source.getQuantity(),source.getCustomer().getCustomerId(),source.getScheduler().getSchedulerId());
	}
	
	public Order parse (OrderModel source) {
		return source == null?null:
			new Order(source.getOrderNumber(),source.getDeliveryDate(),source.getDeliveryPlace(),
					source.getQuantity(),
					schedulerRepo.findById(source.getScheduler()).orElse(null),
					customerRepo.findById(source.getCustomer()).orElse(null));
	}
	
	public ProductModel parse(Product source) {
		return source == null?null:
			new ProductModel(source.getProductId(),source.getProductName(),source.getProductDescription(),
					source.getProductQuantity(),
					source.getContract().getContractNumber(),
					source.getOrder().getOrderNumber());
	}
	
	public Product parse(ProductModel source) {
		return source == null?null:
			new Product(source.getProductId(),source.getProductName(),source.getProductDescription(),
					source.getProductQuantity(),
					contractRepo.findById(source.getContract()).orElse(null),
					orderRepo.findById(source.getOrder()).orElse(null));
	}
	
	public SchedulerModel parse(Scheduler source) {
		return source == null?null:
			new SchedulerModel(source.getSchedulerId(),source.getSchedulerName(),source.getSchedulerContact(),
					source.getTruckNumber());
//					source.getContract().toString(),
//					source.getOrder().toString());
	}
	public Scheduler parse(SchedulerModel source) {
		return source == null?null:
			new Scheduler(source.getSchedulerId(),source.getSchedulerName(),
					source.getSchedulerContact(),source.getTruckNumber());
	}
	
	public LandModel parse(Land source) {
		return source == null ? null:
			new LandModel(source.getLandId(),source.getLandArea(),source.getOwnerName(),
					source.getSurveyNumber());
	}
	
	public Land parse(LandModel source) {
		return source == null ? null:
			new Land(source.getLandId(),source.getLandArea(),source.getOwnerName(),
					source.getSurveyNumber());
	}
}
