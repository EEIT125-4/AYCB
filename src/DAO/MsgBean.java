package DAO;

import java.io.*;
import java.sql.Date;

public class MsgBean implements Serializable {
	String msg_id,msg_title,msg_desc,msg_type;
	Date msg_date;
	
	//javaBean規定要放的空建構式
	public MsgBean() {
	}
	
	public MsgBean(String msg_id, String msg_title, String msg_desc, String msg_type, Date msg_date) {
		super();
		this.msg_id = msg_id;
		this.msg_title = msg_title;
		this.msg_desc = msg_desc;
		this.msg_type = msg_type;
		this.msg_date = msg_date;
	}

	public String getMsg_id() {
		return msg_id;
	}


	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}


	public String getMsg_title() {
		return msg_title;
	}


	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}


	public String getMsg_desc() {
		return msg_desc;
	}


	public void setMsg_desc(String msg_desc) {
		this.msg_desc = msg_desc;
	}


	public String getMsg_type() {
		return msg_type;
	}


	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}


	public Date getMsg_date() {
		return msg_date;
	}


	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}
	
	public void setMsg_date() {
		//將javaDate透過long值轉為sqlDate,
		this.msg_date =new Date(new java.util.Date().getTime());
	}


	
	
	

}