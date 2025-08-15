
package com.cart.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String product = request.getParameter("product");
		
		HttpSession session = request.getSession(true);
		
		ArrayList<String> cart = (ArrayList<String>)session.getAttribute("cart");
		
		if(cart == null) {
			cart = new ArrayList<>();
			session.setAttribute("cart", cart);
		}
		
		// Product array list are setting in a session object
		cart.add(product);
		session.setAttribute("cart", cart);
		
		// forward the request and response
		RequestDispatcher rd = request.getRequestDispatcher("ViewCartServlet");
		rd.forward(request, response);
		
	}

}
