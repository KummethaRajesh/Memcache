package codes.rajesh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  
	  PrintWriter out=response.getWriter();
	  out.println("Memcache Application");

	  response.setContentType("text/plain");
	  response.setCharacterEncoding("UTF-8");

	  //response.getWriter().print("Hello App Engine!\r\n");
	  
	  
  }
}