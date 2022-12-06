package com.example.demo_project.constants;

public enum RtnInfo {
	
	SUCCESSFUL("200","成功。"),
	PARAMETER_REQUIRED("400","必要參數缺失，請再嘗試。"),
	FAILED("500","發生錯誤，請再嘗試。"),
	DATA_REQUIRED("417","請填寫完整的資料。");
	
	
	private String code;
	
	private String message;

	private RtnInfo(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	
}
