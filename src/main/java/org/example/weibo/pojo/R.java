package org.example.weibo.pojo;

import java.util.HashMap;
import java.util.Map;

public class R {
	private Boolean success;

	//631548  正常  430438  失败  100009  无数据
	private Integer code;

	private String message;

	private Map<String, Object> data = new HashMap<String, Object>();

	private R(){}

	public static R ok(){
		R r=new R();
		r.setSuccess(true);
		r.setCode(631548);
		r.setMessage("");
		return r;
	}
	public static R error(){
		R r=new R();
		r.setSuccess(false);
		r.setCode(430438);
		r.setMessage("未知异常");
		return r;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void addData(String key, Object value){
		this.data.put(key,value);
	}
}
