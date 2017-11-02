package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletControl")
public class ServletControl1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletControl1() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/index.jsp");
			dispatcher.forward(request, response);
		} else {
			switch (request.getParameter("action")) {
			case "Eje1":

				HashMap<String, String> enlaces = new HashMap<String, String>();
				enlaces.put("google", "http://www.google.es");
				enlaces.put("youtube", "http://www.youtube.es");
				enlaces.put("github", "http://www.github.es");
				request.setAttribute("Enlaces", enlaces);

				RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Ejercicio1.jsp");
				dispatcher.forward(request, response);
				break;
			case "Eje2":

				dispatcher = request.getRequestDispatcher("JSP/Ejercicio2.jsp");
				dispatcher.forward(request, response);
				break;

			default:
				dispatcher = request.getRequestDispatcher("JSP/index.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("algo");

		ArrayList<String> err = new ArrayList<String>();
		if (request.getParameter("usu") == null) {
			err.add("Usuario no mandado");
		} else if (request.getParameter("usu").equalsIgnoreCase("")) {
			err.add("Usuario vacio");
		}

		if (request.getParameter("pass") == null) {
			err.add("Contraseña no mandada");
		} else if (request.getParameter("pass").equalsIgnoreCase("")) {
			err.add("Contraseña vacia");
		}

		if (err.size() == 0) {
			boolean sino = ExistsUsu(request.getParameter("usu"), request.getParameter("pass"));
			if(!sino){
				err.add("Fallo de credenciales");
			}else{
				err.add("Que de noche salen los pardos");
			}
		}
	}

	private Connection getConexion() {

		String USUARIO = "root";
		String PASS = "1234";
		String URL_BD = "jdbc:mysql://localhost:3306/biblioteca";
		try {
			return (Connection) DriverManager.getConnection(URL_BD, USUARIO, PASS);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof ClassNotFoundException) {
				System.err.println("Fallo de driver de BD");
			} else {
				System.err.println("Fallo de arrauqe de MYSQL");
			}
			System.exit(0);
			return null;
		}
	}

	private boolean ExistsUsu(String usu, String pass) {
		String consulta = "SELECT * FROM usuarios WHERE usuario='" + usu + "' AND clave='" + pass + "'";
		try {
			Connection con = this.getConexion();
			PreparedStatement preparedStatement = con.prepareStatement(consulta);

			ResultSet rs = preparedStatement.executeQuery(consulta);
			return rs.next();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}
}
