package Kevin;

import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.PrintWriter;
//import java.io.IOException;

import java.io.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
//import javax.rmi.*;
import javax.naming.*;
import javax.sql.*;

import org.apache.naming.java.javaURLContextFactory;

import DAO.MsgBean;
import DAO.MsgDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/MsgServlet")

public class MsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public MsgServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		System.out.println("do post msgservlet");
		// To prevent caching
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server

		if (request.getParameter("submit") != null) {
			System.out.println("go to  subit process");
			gotoSubmitProcess(request, response);
			
			
		}else {
			System.out.println("where's submit?");
		} 
		
		/*else if (request.getParameter("confirm") != null) {
			gotoConfirmProcess(request, response);
		}*/
	}

	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//原則上送出不檢查直接加入資料庫
		//最好能自動取號,取得rs 日期與現在同一天的筆數
		//若rs=null,創建日期流水編第一筆
		//若rs!=null,編號為日期+(目前筆數+1)
		
		
		System.out.println("in subit process");
		//取得submit過來的資料
		String msg_title = request.getParameter("title").trim();
		String msg_desc = request.getParameter("desc").trim();
		String msg_type = request.getParameter("type").trim();
		// 以JAVADATE取得今天日期的long,再轉為SQLDATE
		java.sql.Date msg_date = new java.sql.Date(new Date().getTime());

		MsgBean reg_msg = new MsgBean("", msg_title, msg_desc, msg_type, msg_date);
		request.getSession(true).setAttribute("reg_msg", reg_msg);
		//request.getRequestDispatcher("/NewMsg.jsp").forward(request, response);
		
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		//insert into
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		String sqlCondition=f.format(reg_msg.getMsg_date());
		System.out.println(sqlCondition);
				
		try {

			ctxt = new InitialContext();

			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/AYCBDB");
			System.out.println("getDS");

			conn = ds.getConnection();
			System.out.println("getConn");
			
			Statement st=conn.createStatement();
			String sql=("select *from message where msg_date=")+ "'"+sqlCondition+"'";
			System.out.println(sql);
			ResultSet rs=st.executeQuery(sql);//where msg_date="+ sqlCondition
			
			int last=1;
			while(rs.next()) {
				last++;
				System.out.printf("last=%d",last);
			}
			
			String tempID=sqlCondition.replaceAll("-", "") +"-"+String.valueOf(last);
			reg_msg.setMsg_id(tempID);
				
			MsgDAO msgDAO = new MsgDAO(conn);
			MsgBean msgData = (MsgBean) request.getSession(true).getAttribute("reg_msg");
			if (msgDAO.insertMsg(msgData)) {
				System.out.println("Get some SQL commands done!");
				request.getSession(true).invalidate();
				
				//request.getRequestDispatcher("/NewMsg.jsp").forward(request, response);
				response.sendRedirect("./myProject/homepage.html");
			}else {System.out.println("insert 失敗");}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		
	}

}
