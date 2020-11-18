package mvcBean;

import java.sql.Date;

public class Message {
	
	private int msg_ID;
	private String msg_Desc;
	private Date msg_Date;
	private String msg_Type;
	public int getMsg_ID() {
		return msg_ID;
	}
	public void setMsg_ID(int msg_ID) {
		this.msg_ID = msg_ID;
	}
	public String getMsg_Desc() {
		return msg_Desc;
	}
	public void setMsg_Desc(String msg_Desc) {
		this.msg_Desc = msg_Desc;
	}
	public Date getMsg_Date() {
		return msg_Date;
	}
	public void setMsg_Date(Date msg_Date) {
		this.msg_Date = msg_Date;
	}
	public String getMsg_Type() {
		return msg_Type;
	}
	public void setMsg_Type(String msg_Type) {
		this.msg_Type = msg_Type;
	}
	@Override
	public String toString() {
		return "Message [msg_ID=" + msg_ID + ", msg_Desc=" + msg_Desc + ", msg_Date=" + msg_Date + ", msg_Type="
				+ msg_Type + "]";
	}
	
}
