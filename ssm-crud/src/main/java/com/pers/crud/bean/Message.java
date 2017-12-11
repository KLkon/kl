package com.pers.crud.bean;

import java.util.HashMap;
import java.util.Map;

/*
 * 处理类返回的 Json 数据类型
 */
public class Message {
	
	//状态码(1--成功，2--失败)
	private Integer code;
	//状态信息
	private String msg;
	//返回给客户端的数据
	private Map<String, Object> extend = new HashMap<>();
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
	//成功
	public static Message success() {
		Message result = new Message();
		result.setCode(1);
		result.setMsg("成功");
		return result;
	}
	
	//失败
	public static Message fail() {
		Message result = new Message();
		result.setCode(2);
		result.setMsg("失败");
		return result;
		}
	
	//添加数据方法
	public Message add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}
}
