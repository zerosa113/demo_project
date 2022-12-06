package com.example.demo_project.constants;

public enum RtnInfo {
	
	SUCCESSFUL("200","���\�C"),
	PARAMETER_REQUIRED("400","���n�ѼƯʥ��A�ЦA���աC"),
	FAILED("500","�o�Ϳ��~�A�ЦA���աC"),
	DATA_REQUIRED("417","�ж�g���㪺��ơC");
	
	
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
