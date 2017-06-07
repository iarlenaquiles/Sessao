
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/tarefas")
public class Tarefas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Tarefas() {
		super();
	}

	@SuppressWarnings("null")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<String> lista = ((List<String>) session.getAttribute("lista_tarefas"));

		if (lista == null) {
			lista = new ArrayList<>();
			session.setAttribute("lista_tarefas", lista);
		}

		String str = request.getParameter("tarefa");
		if (str != null || str.length() != 0) {
			lista.add(str);
		}

		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<p>");
		out.print(session.getId());
		out.print("</p>");
		out.print("<ol>");
			for(String item: lista){
				out.print("<li>");
				out.print(item);
				out.print("</li>");
			}
		out.print("</ol>");
		out.print("</body>");
		out.print("</html>");

	}

}
