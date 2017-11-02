package controlador;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ServletControl")
public class ServletControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletControl() {
        super();
    }

    private HashMap<String, String> enlaces;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext contexto = config.getServletContext();

		
		enlaces.put("google", "http://www.google.es");
		enlaces.put("youtube", "http://www.youtube.es");
		enlaces.put("github", "http://www.github.es");
		

		contexto.setAttribute("Enlaces", enlaces);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
