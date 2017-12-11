package com.pers.crud.bean;

import java.util.HashMap;
import java.util.Map;

/*
 * �����෵�ص� Json ��������
 */
public class Message {
	
	//״̬��(1--�ɹ���2--ʧ��)
	private Integer code;
	//״̬��Ϣ
	private String msg;
	//���ظ��ͻ��˵�����
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
	
	
	//�ɹ�
	public static Message success() {
		Message result = new Message();
		result.setCode(1);
		result.setMsg("�ɹ�");
		return result;
	}
	
	//ʧ��
	public static Message fail() {
		Message result = new Message();
		result.setCode(2);
		result.setMsg("ʧ��");
		return result;
		}
	
	//������ݷ���
	public Message add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}
}
