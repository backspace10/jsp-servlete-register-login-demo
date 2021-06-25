package net.mypack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypack.ConnectionPro;
import net.mypack.User;
import net.mypack.UserDatabase;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// feth data from login form

		String logemail = request.getParameter("email");
		String logpass = request.getParameter("password");

		UserDatabase db = new UserDatabase(ConnectionPro.getConnection());
		User user = db.logUser(logemail, logpass);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("logUser", user);
			response.sendRedirect("welcome.jsp");
		} else {
			PrintWriter out = response.getWriter();
			out.println("user not found");
		}
	}

}
