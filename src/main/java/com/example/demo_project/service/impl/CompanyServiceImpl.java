package com.example.demo_project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Company;
import com.example.demo_project.entity.OrgCompanyId;
import com.example.demo_project.repository.CompanyDao;
import com.example.demo_project.service.ifs.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyDao companyDao;
	@Override
	public List<Company> findAll() {
		return companyDao.findAll();
	}
	@Override
	public Company findById(String orgId,String companyId) {
		OrgCompanyId orgCompanyId = new OrgCompanyId(orgId,companyId);
		Optional<Company> companyOp = companyDao.findById(orgCompanyId);
//		if(companyOp.isPresent()) {		//isPresent判斷有無東西，值是否存在
////			Company com = companyOp.get();
////			return com;
//			return companyOp.get();		//27+28行
//		}
//		return new Company();
		return companyOp.orElse(new Company());		//26~31
	}
	@Override
	public Company updateById(String orgId, String companyId) {
		OrgCompanyId orgCompanyId = new OrgCompanyId(orgId,companyId);
		Optional<Company> companyOp = companyDao.findById(orgCompanyId);
		//update name
		if(companyOp.isPresent()) {
			Company com = companyOp.get();		//JPA不提供update語法,要先find找到資料get後才能update	
			com.setCompanyName("A02");			//set更新
			Company newCom = companyDao.save(com);		//最後才能save
			return newCom;
		}
		return new Company();
	}
	@Override
	public Company saveCompany() {
		Company com = new Company("DDD","D00","COMD");
		return companyDao.save(com);
	}

}
