package codes.rajesh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String memKey = "count";
		
		if(memcacheService.contains(memKey)) {
			memcacheService.increment(memKey, 1);
			out.println("Value already exists. Updating it.<br/>The final value is " + memcacheService.get(memKey));
		}else {
			out.println("Value doesn't exists. Starting from 0.");
			memcacheService.put(memKey, 0);
		}
		
		
		
	}
}
