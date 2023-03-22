package com.example.demo_project.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.constants.RegisterRtnCode;
import com.example.demo_project.entity.Register;
import com.example.demo_project.service.ifs.RegisterService;
import com.example.demo_project.vo.ActiveAccountReq;
import com.example.demo_project.vo.AddRoleListReq;
import com.example.demo_project.vo.AddRoleSetReq;
import com.example.demo_project.vo.LoginInfo;
import com.example.demo_project.vo.RegisterReq;
import com.example.demo_project.vo.RegisterRes;

@RestController
public class RegisterController {
	
	//import org.slf4j
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RegisterService registerService;

	@PostMapping(value = "/api/register")
	public RegisterRes register(@RequestBody RegisterReq req, HttpSession httpSession) {
		RegisterRes checkResult = checkParam(req);
		if (req == null) {
			return checkResult;
		}
		Register reg = registerService.register(req.getAccount(), req.getPwd(), req.getName(), req.getAge(),
				req.getCity());
		if (reg == null) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_EXISTED.getMessage());
		}
		//add verify code into httpSession �s�W�H���ýX
		//Math.random Math.round
		double random = Math.random()*10000;
		int verifyCode = (int) Math.round(random);	//�|�ˤ��J���૬��int
		httpSession.setAttribute("verify_code", verifyCode);
		httpSession.setAttribute("account", req.getAccount());
		return new RegisterRes(reg, RegisterRtnCode.SUCCESSFUL.getMessage(),verifyCode);
	}

	// �T�{�ѼƬO�_��null
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
//	//�N����X�֡A���X�Τ@�^��
//	private RegisterRes checkParam(Register req) {
//		if (!StringUtils.hasText(req.getAccount())||!StringUtils.hasText(req.getPwd())
//				||!StringUtils.hasText(req.getName())) {
//			return new RegisterRes("Param Required!");
//		}
//		return null;
//	}

	@PostMapping(value = "/api/active_account")
	public RegisterRes activeAccount(@RequestBody RegisterReq req) {
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		return registerService.activeAccount(req.getAccount());
//		boolean result = registerService.activeAccount(req.getAccount());
//		//�^�ǰT�����ϥΪ�
//		if(result) {	//�P�_���\�Υ���
//			return new RegisterRes(null, RegisterRtnCode.SUCCESSFUL.getMessage());
//		}
//		return new RegisterRes(null, RegisterRtnCode.FAILURE.getMessage());
	}

	@PostMapping(value = "/api/add_rolelist")
	public RegisterRes addRoleList(@RequestBody AddRoleListReq req) {
		// �ˬdAddRoleListReq account�O�_�s�b
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		if (CollectionUtils.isEmpty(req.getRoleList())) {
			return new RegisterRes(RegisterRtnCode.ROLD_LIST_IS_EMPTY.getMessage());
		}
		return registerService.addRole(req.getAccount(), req.getRoleList());
	}

	@PostMapping(value = "/api/add_roleset")
	public RegisterRes addRoleSet(@RequestBody AddRoleSetReq req) {
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		// �P�_null�|����b�e��/isEmpty()�񥪦p�G�Onull�|�~�����
//		if(req.getRoleSet() == null||req.getRoleSet().isEmpty()) {	//�P�_null���P�U��CollectionUtils
		if (CollectionUtils.isEmpty(req.getRoleSet())) {
			// �nimport util
			return new RegisterRes(RegisterRtnCode.ROLD_LIST_IS_EMPTY.getMessage());
		}
		return registerService.addRoleSet(req.getAccount(), req.getRoleSet());
	}

	@PostMapping(value = "/api/addRoleTest")
	public RegisterRes addRoleTest(@RequestBody AddRoleListReq req) {
		if (!StringUtils.hasText(req.getAccount())) {
			return new RegisterRes(RegisterRtnCode.ACCOUNT_REQUIRED.getMessage());
		}
		if (CollectionUtils.isEmpty(req.getRoleList())) {
			return new RegisterRes(RegisterRtnCode.ROLD_LIST_IS_EMPTY.getMessage());
		}
		return registerService.addRole(req.getAccount(), req.getRoleList());
	}

	@PostMapping(value = "/api/login")
	public RegisterRes login(@RequestBody LoginInfo loginInfo, HttpSession httpSession) {
		if (!StringUtils.hasText(loginInfo.getAccount()) || !StringUtils.hasText(loginInfo.getPwd())) {
			return new RegisterRes("Paramter is error!");
		}
		Register result = registerService.findById(loginInfo.getAccount());
		if (result == null) {
			return new RegisterRes("UserInfo not found!");
		}
		httpSession.setAttribute("account", result.getAccount());	//key�ȹ�����value
		httpSession.setMaxInactiveInterval(20);		//�Ʀr�^��ơA�]0�έt�Ƥ��|�Ȱ�
		return new RegisterRes(result, "Login Success!");
	}
	
	@PostMapping(value = "/api/logout")
	public RegisterRes logout(HttpSession httpSession) {
		httpSession.removeAttribute("account");
		return new RegisterRes("Logout Success!");
	}
	
	@PostMapping(value = "/api/get_user_info")
	public RegisterRes getUserInfo(HttpSession httpSession) {
		Object attValue = httpSession.getAttribute("account");
		if(attValue != null) {
			String account = attValue.toString();
			Register result = registerService.findById(account);
			return new RegisterRes(result, "Get User Info Success!");
		}
//		String account = httpSession.getAttribute("user_account").toString();
//		Register result = registerService.findById(account);
		return new RegisterRes("Get User Info Error!");
	}
	
	@PostMapping(value = "/api/active_account2")
	public RegisterRes activeAccount2(@RequestBody ActiveAccountReq req, HttpSession httpSession) {
		Object verifyCode = httpSession.getAttribute("verify_code");
		Object accountCode = httpSession.getAttribute("account");
		//�૬�e���P�_Object�O�_��null�A�_�h�|���P
		if(verifyCode != null && accountCode != null) {
			int attVer =  (int) httpSession.getAttribute("verify_code");
			String account = accountCode.toString();
			if(attVer == req.getVerifyCode()) {
				registerService.activeAccount(account);
				return new RegisterRes("success");
			}
			return new RegisterRes("verifyCode required");
		}
		return new RegisterRes("account active error");
	}
}
