package codes.rajesh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@SuppressWarnings("serial")
@WebServlet("memcache")
public class MemcacheServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String path = request.getRequestURI();
	    if (path.startsWith("/favicon.ico")) {
	      return;
	    }
		
		MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();
		memcacheService.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		
//		memcacheService.put("name", "Rajesh1");
//		out.print(memcacheService.get("name")+"<br/>");
//		
//		memcacheService.put("name", "Rajesh2");
//		out.print(memcacheService.get("name")+"<br/>");
//		
//		memcacheService.put("name", "Rajesh3");
//		out.print(memcacheService.get("name")+"<br/>");
//		
//		memcacheService.put("name", "Rajesh4");
//		out.print(memcacheService.get("name")+"<br/>");
//		
//		memcacheService.put("name", "Rajesh5");
//		out.print(memcacheService.get("name")+"<br/>");
//		
//		memcacheService.put("name", "Rajesh6");
//		out.print(memcacheService.get("name")+"<br/>");
//		
//		memcacheService.put("name", "Rajesh7");
//		out.print(memcacheService.get("name")+"<br/>");
//		
//		memcacheService.put("name", "Rajesh8");
//		out.print(memcacheService.get("name")+"<br/>");
//		
//		memcacheService.put("name", "Rajesh9");
//		out.print(memcacheService.get("name")+"<br/>");
		
		memcacheService.put("sCount", 0);
		
		for(int i=0; i<100; i++) {
			memcacheService.increment("sCount", 20);
		}
		
		for(int i=0; i<50; i++) {
			memcacheService.increment("sCount", 40);
		}
		
		for(int i=0; i<50; i++) {
			memcacheService.increment("sCount", 60);
		}
		
		int c = (int) memcacheService.get("sCount");
		out.println(c);
		
	}
}
