package net.mypack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// make user object
		User userModel = new User(name, email, password);

		// create a database model
		UserDatabase regUser = new UserDatabase(ConnectionPro.getConnection());
		if (regUser.saveUser(userModel)) {
			response.sendRedirect("index.jsp");
		} else {
			String errorMessage = "User Available";
			HttpSession regSession = request.getSession();
			regSession.setAttribute("RegError", errorMessage);
			response.sendRedirect("registration.jsp");
		}

	}

}
