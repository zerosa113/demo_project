package com.example.demo_project.constants;
		//列舉
public enum RegisterRtnCode {

	SUCCESSFUL("200","Successful"),
	ACCOUNT_REQUIRED("400","Account is required!"),
	PWD_REQUIRED("400","Pwd is required!"),
	NAME_REQUIRED("400","Name is required!"),
	ACCOUNT_EXISTED("403","Account has existed!"),
	FAILURE("400","Account active failure!"),
	ADD_ROLE_FAILURE("400","Add role failure!"),
	ROLD_LIST_IS_EMPTY("400","Role list is empty");
	//500是伺服器錯誤
	private String code;
	
	private String message;
	
	//enum裡的建構方法只允許private
	private RegisterRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	//只需要get
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
