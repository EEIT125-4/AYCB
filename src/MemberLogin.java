

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Login
 */
@WebServlet("/MemberLogin")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String user=request.getParameter("user");
		 String pwd=request.getParameter("pwd");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		
		pw.println(user);
		pw.println(pwd);
		boolean login=false;
	    DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
		
		try  
		{ ctxt = new InitialContext();
			ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/AYCBDB");
			 conn = ds.getConnection();
			 request.setCharacterEncoding("UTF-8");
			 response.setCharacterEncoding("UTF-8");
			 Statement statement=conn.createStatement();
			System.out.println("連結資料庫");
			ResultSet rs=statement.executeQuery("select*from member");
			while(rs.next()) {
				String user_name=rs.getString("member_name");
				String user_pwd=rs.getString("member_password");
				if(user.equals(user_name) && pwd.equals(user_pwd)) {
					
					login=true;
					break;
				}
				
		}
			if(login) {
				pw.printf("user %s  Login success 成功登入<br>",user);
				
			}else {
				pw.println("Login fail 登入失敗");
				
			}
			
			
			out.println("user: " + user+"<br>");
			out.println("password: " + password+"<br>");
			String info = "";
			boolean access = false;
			if (user != null && password != null && password.equals((String) users.get(user))) {
				access = true;
				info = "Hello " + user;
			} else {
				//request.getRequestDispatcher("/login.html").forward(request, response);
				
				info += "login fail,wait 3 sec<br>";
				if (!user.equals(getInitParameter("account"))) {
					info += "invaild user<br>";
				}
				if (!password.equals(getInitParameter("password"))) {
					info += "invaild password<br>";
				}

			}
			String meta = (access) ? "" : "<meta http-equiv=\"refresh\" content=\"3;url=submit.html\">";

			response.setContentType("text/html;charset=UTF-8");

			PrintWriter pw = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");

			pw.print("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
					+ meta + "    <title>Document</title>\r\n" + "</head>\r\n" + "<body>\r\n"

					+ info + "\r\n" + "    \r\n" + "    \r\n" + "</body>\r\n" + "</html>");
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("destroy");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		//初始化後就不再調用
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		
		
	}
	
	
	

}
