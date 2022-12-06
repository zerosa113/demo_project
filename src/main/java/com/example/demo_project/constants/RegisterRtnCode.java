package com.example.demo_project.constants;
		//�C�|
public enum RegisterRtnCode {

	SUCCESSFUL("200","Successful"),
	ACCOUNT_REQUIRED("400","Account is required!"),
	PWD_REQUIRED("400","Pwd is required!"),
	NAME_REQUIRED("400","Name is required!"),
	ACCOUNT_EXISTED("403","Account has existed!"),
	FAILURE("400","Account active failure!"),
	ADD_ROLE_FAILURE("400","Add role failure!"),
	ROLD_LIST_IS_EMPTY("400","Role list is empty");
	//500�O���A�����~
	private String code;
	
	private String message;
	
	//enum�̪��غc��k�u���\private
	private RegisterRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	//�u�ݭnget
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
