
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
	
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("UTF-8");
			
		boolean login = false;
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		String info = "";
			
		if ((user !="" && !user.isEmpty()) && (pwd !="" && !pwd.isEmpty()) ){
			try {
				ctxt = new InitialContext();
				ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/AYCBDB");
				conn = ds.getConnection();
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				Statement statement = conn.createStatement();
				System.out.println("連結資料庫");
				ResultSet rs = statement.executeQuery("select*from member");
				while (rs.next()) {
					String user_name = rs.getString("member_name");
					String user_pwd = rs.getString("member_password");
					if (user.equals(user_name) && pwd.equals(user_pwd)) {

						login = true;
						break;
					}

				}
				if (login) {
					
					response.sendRedirect("./myProject/homepage.html");

				} else {
					
					info+=("Login fail");
					
					String meta = "<meta http-equiv=\"refresh\" content=\"3;url=myProject/login.html\">";
					response.setContentType("text/html;charset=UTF-8");
					info += "login fail,wait 3 sec<br>";
					// request.getRequestDispatcher("/login.html").forward(request, response);
					pw.print("<!DOCTYPE html>\r\n"  + "<head>\r\n"//+ "<html lang=\"en\">\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n" + meta + "    <title>Document</title>\r\n"
							+ "</head>\r\n" + "<body>\r\n"

							+ info + "\r\n" + "    \r\n" + "    \r\n" + "</body>\r\n" + "</html>");

				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {//如果帳號或密碼為空
			System.out.println("null");
			if(user==null ||user.equals("")) {
				info+="帳號不可為空值<br>";
			}
			if(pwd==null ||pwd.equals("")) {
				info+="密碼不可為空值<br>";
			}
			
			
			
			
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		// 初始化後就不再調用

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);

	}

}
