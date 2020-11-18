package Kevin;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/shopping")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataSource ds;
  
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		try {
		      // Create a JNDI Initial context to be able to lookup the DataSource
		      InitialContext ctx = new InitialContext();
		      // Lookup the DataSource, which will be backed by a pool
		      //   that the application server provides.
		      ds = (DataSource)ctx.lookup("java:comp/env/jdbc/EmployeeDB");
		      if (ds == null)
		         throw new ServletException("Unknown DataSource 'jdbc/EmployeeDB'");
		  } catch (NamingException ex) {
		      ex.printStackTrace();
		  }
		
		  Connection conn = null;
	      Statement  stmt = null;
	      
	      List <String> titleList =  new  ArrayList <String> () ;
	      List <String> authorList = new  ArrayList <String> ();
	      List <Float> priceList = new  ArrayList <Float> ();
	      
	      try {
	         	 
	         // Get a connection from the pool
	         conn = ds.getConnection();
	 
	         // Normal JBDC programming hereafter. Close the Connection to return it to the pool
	         stmt = conn.createStatement();
	         ResultSet rset = stmt.executeQuery("SELECT title, author, price FROM books");
	        
	         while(rset.next()) {        	 
	        	titleList.add(rset.getString("title"));
	        	authorList.add(rset.getString("author"));
	        	priceList.add(rset.getFloat("price"));
	         }
	         
	         BookDB.setTitles((String[]) titleList.toArray(new String[0]));
	         BookDB.setAuthors((String[]) authorList.toArray(new String[0]));
	         BookDB.setPrices((Float[]) priceList.toArray(new Float[0]));

	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      } finally {
	   
	         try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();  // return to pool
	         } catch (SQLException ex) {
	             ex.printStackTrace();
	         }
	      }
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);  // Same as doGet()
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 // Retrieve the current session, or create a new session if no session exists.
	      HttpSession session = request.getSession(true);
	 
	      // Retrieve the shopping cart of the current session.
	      List<CartItem> theCart = (ArrayList<CartItem>) session.getAttribute("cart");
	 
	      // For dispatching the next Page
	      String nextPage = "";
	      String todo = request.getParameter("todo");
	 
	      if (todo == null) {
	         // First access - redirect to order.jsp
	         nextPage = "/order.jsp";
	      } else if (todo.equals("add")) {
	         // Submitted by order.jsp, with request parameters bookID and qty.
	         // Create a CartItem for the selected book, and add it into the cart
	         CartItem newCartItem = new CartItem(
	                 Integer.parseInt(request.getParameter("bookID")),
	                 Integer.parseInt(request.getParameter("qty")));
	         if (theCart == null) { // shopping cart is empty
	            theCart = new ArrayList<>();
	            theCart.add(newCartItem);
	            session.setAttribute("cart", theCart);  // binds cart to session
	         } else {
	            // Check if this book is already in the cart.
	            // If so, update the qty ordered. Otherwise, add it to the cart.
	            boolean found = false;
	            Iterator iter = theCart.iterator();
	            while (!found && iter.hasNext()) {
	               CartItem aCartItem = (CartItem)iter.next();
	               if (aCartItem.getBookID() == newCartItem.getBookID()) {
	                  aCartItem.setQtyOrdered(aCartItem.getQtyOrdered()
	                        + newCartItem.getQtyOrdered());
	                  found = true;
	               }
	            }
	            if (!found) { // Add it to the cart
	               theCart.add(newCartItem);
	            }
	         }
	         // Back to order.jsp for more order
	         nextPage = "/order.jsp";
	      } else if (todo.equals("remove")) {
	         // Submitted by order.jsp, with request parameter cartIndex.
	         // Remove the CartItem of cartIndex in the cart
	         int cartIndex = Integer.parseInt(request.getParameter("cartIndex"));
	         theCart.remove(cartIndex);
	         // Back to order.jsp for more order
	         nextPage = "/order.jsp";
	      } else if (todo.equals("checkout")) {
	         // Submitted by order.jsp.
	         // Compute the total price for all the items in the cart
	         float totalPrice = 0;
	         int totalQtyOrdered = 0;
	         for (CartItem item: theCart) {
	            float price = item.getPrice();
	            int qtyOrdered = item.getQtyOrdered();
	            totalPrice += price * qtyOrdered;
	            totalQtyOrdered += qtyOrdered;
	         }
	         // Format the price in float to 2 decimal places
	         StringBuilder sb = new StringBuilder();
	         Formatter formatter = new Formatter(sb);  // Send all output to sb
	         formatter.format("%.2f", totalPrice);     // 2 decimal places
	         // Put the total price and qty in request header
	         request.setAttribute("totalPrice", sb.toString());
	         request.setAttribute("totalQtyOrdered", totalQtyOrdered + "");
	         // Dispatch to checkout.jsp
	         nextPage = "/checkout.jsp";
	      }
	 
	      ServletContext servletContext = getServletContext();
	      RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
	      requestDispatcher.forward(request, response);
	      
	      //getServletContext().getRequestDispatcher(nextPage);
	   }


	
}
