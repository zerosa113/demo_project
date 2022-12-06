package com.example.demo_project.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.service.ifs.BankService;
import com.example.demo_project.vo.BankRes;
import com.example.demo_project.vo.DespositReq;
import com.example.demo_project.vo.DespositRes;
import com.example.demo_project.vo.WithdrawReq;
import com.example.demo_project.vo.WithdrawRes;
import com.example.demo_project.vo.BankReq;

@RestController		//=@Controller+@ResponsBody
public class BankController {
	//localhost:8080 本地端
	@Autowired
	private BankService bankService;
	
/*	@PostMapping(value="/api/getAmount")
	public BankRes getAmount(@RequestBody BankReq request) {
		BankRes res= new BankRes();
//		String account = request.getAccount();
//		if(account==null||account.isEmpty()) {
//			res.setMessage("Account is empty!");
//			return res;
//		}
//		System.out.println(account.isEmpty());
		if(!StringUtils.hasText(request.getAccount())) {	//hasText是否有文字，空白不算文字
			res.setMessage("Account is empty!")	;			//!是相反，否的意思
			return res;
		}
		Bank bank = bankService.getAmount(request.getAccount());
//		String Account = bank.getAccount();
//		int amount = bank.getAmount();		//19.20合併至21.22
//		res.setAccount(bank.getAccount());
//		res.setAmount(bank.getAmount());
		res.setBank(bank);
		res.setMessage("Success");
		return res;
	}
	@PostMapping(value="/api/desposit")	//API URL:API的定值
	public DespositRes deposit(@RequestBody DespositReq despositReq) {
		Bank bank = bankService.deposit(despositReq.getAccount(), despositReq.getAmount());
		DespositRes res = new DespositRes();
		res.setAccount(bank.getAccount());
		res.setAmount(bank.getAmount());
		res.setMessage("money");
		return res;
	}
	@PostMapping(value="/api/withdraw")
	public WithdrawRes withdrawAmount(@RequestBody WithdrawReq request) {
		WithdrawRes res = new WithdrawRes();
		if(!StringUtils.hasText(request.getAccount())) {
			res.setMessage("Account is empty!")	;
			return res;
		}
		BankRes result = bankService.withdraw(request.getAccount(),request.getWithdrawAmount());
		
		res.setAccount(result.getAccount());
		res.setAmount(result.getAmount());
		res.setMessage(result.getMessage());
		return res;
	}
	@GetMapping(value="/api/getAmount")
	public BankRes getAmount() {
		BankRes res = new BankRes();
		Bank bank = new Bank();
		bank.setAccount("A");
		bank.setAmount(1000);
		res.setBank(bank);
		res.setMessage("Succ");
		return res;
	}*/
//====================================
	@PostMapping(value="/api/createAccount")
	public Bank createAccount(@RequestBody BankReq req) {
		return bankService.createAccount(req.getAccount());
	}
	@PostMapping(value="/api/getamount")
	public Bank getAmount1(@RequestBody BankReq req){
		BankRes res= new BankRes();
		Bank getamount = new Bank();
		getamount=bankService.getAmount1(req.getAccount());
		if(getamount==null) {
			System.out.println("無效帳號");
			return getamount;
		}
//		res.setAccount(getamount.getAccount());
//		return res;
		return getamount;
	}
	@PostMapping(value="/api/deleAccount")
	public BankRes deleAccount(@RequestBody BankReq req) throws Exception{
		bankService.deleAccount(req.getAccount());
		return new BankRes();
	}
	@PostMapping(value="/api/deleteByName")
	public Bank deleteByName(@RequestBody BankReq req) throws Exception{
		bankService.deleteByName(req.getName());
		return new Bank();
	}
	
}
