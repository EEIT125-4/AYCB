package DAO;

public class account {
	private String member_id;
	private String member_name;
	//private String member_email;
	private String member_pwd;
	private int member_statue;
	
	
	
	public account(String member_id, String member_name, String member_pwd, int member_statue) {
		super();
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_pwd = member_pwd;
		this.member_statue = member_statue;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
	public int getMember_statue() {
		return member_statue;
	}
	public void setMember_statue(int member_statue) {
		this.member_statue = member_statue;
	}
	
	

}
