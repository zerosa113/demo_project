package com.example.demo_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.constants.RegisterRtnCode;
import com.example.demo_project.entity.Register;
import com.example.demo_project.service.ifs.RegisterService;
import com.example.demo_project.vo.AddRoleListReq;
import com.example.demo_project.vo.AddRoleSetReq;
import com.example.demo_project.vo.RegisterReq;
import com.example.demo_project.vo.RegisterRes;

@RestController
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@PostMapping(value = "/api/register")
	public RegisterRes register(@RequestBody RegisterReq req) {
		RegisterRes checkResult = checkParam(req);
		if(req == null) {
			return checkResult;
		}
		Register reg = registerService.register(req.getAccount(), req.getPwd(), req.getName(), req.getAge(),
				req.getCity());
		if (reg == null) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_EXISTED.getMessage());
		}
		return new RegisterRes(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
	}
	// 確認參數是否為null
	private RegisterRes checkParam(RegisterReq req) {
		// account,pwd,name,name cannot be null or empty
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getPwd())) {
			return new RegisterRes(RegisterRtnCode.PWD_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getName())) {
			return new RegisterRes(RegisterRtnCode.NAME_REQUIRED.getMessage());
		}
		return null;
	}
//	//將條件合併，跳出統一回覆
//	private RegisterRes checkParam(Register req) {
//		if (!StringUtils.hasText(req.getAccount())||!StringUtils.hasText(req.getPwd())
//				||!StringUtils.hasText(req.getName())) {
//			return new RegisterRes("Param Required!");
//		}
//		return null;
//	}
	
	@PostMapping(value = "/api/active_account")
	public RegisterRes activeAccount(@RequestBody RegisterReq req) {
		if(!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		return registerService.activeAccount(req.getAccount());
//		boolean result = registerService.activeAccount(req.getAccount());
//		//回傳訊息給使用者
//		if(result) {	//判斷成功或失敗
//			return new RegisterRes(null, RegisterRtnCode.SUCCESSFUL.getMessage());
//		}
//		return new RegisterRes(null, RegisterRtnCode.FAILURE.getMessage());
	}
	
	@PostMapping(value = "/api/add_rolelist")
	public RegisterRes addRoleList(@RequestBody AddRoleListReq req) {
		//檢查AddRoleListReq account是否存在
		if(!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		if(CollectionUtils.isEmpty(req.getRoleList())) {
			return new RegisterRes(RegisterRtnCode.ROLD_LIST_IS_EMPTY.getMessage());
		}
		return registerService.addRole(req.getAccount(), req.getRoleList());
	}
	
	@PostMapping(value = "/api/add_roleset")
	public RegisterRes addRoleSet(@RequestBody AddRoleSetReq req) {
		if(!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		//判斷null會先放在前面/isEmpty()放左如果是null會繼續執行
//		if(req.getRoleSet() == null||req.getRoleSet().isEmpty()) {	//判斷null等同下方CollectionUtils
		if(CollectionUtils.isEmpty(req.getRoleSet())) {
			//要import util
			return new RegisterRes(RegisterRtnCode.ROLD_LIST_IS_EMPTY.getMessage());
		}
		return registerService.addRoleSet(req.getAccount(), req.getRoleSet());
	}
	
	
	@PostMapping(value = "/api/addRoleTest")
	public RegisterRes addRoleTest(@RequestBody AddRoleListReq req) {
		if(!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		if(CollectionUtils.isEmpty(req.getRoleList())) {
			return new RegisterRes(RegisterRtnCode.ROLD_LIST_IS_EMPTY.getMessage());
		}
		return registerService.addRole(req.getAccount(), req.getRoleList());
	}
}
