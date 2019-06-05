package codes.rajesh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@SuppressWarnings("serial")
@WebServlet("asyncMemcache")
public class AsyncMemcacheServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String path = request.getRequestURI();
	    if (path.startsWith("/favicon.ico")) {
	      return; // ignore the request for favicon.ico
	    }
		
		AsyncMemcacheService asyncCache = MemcacheServiceFactory.getAsyncMemcacheService();
		asyncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		
		Future<Object> futureValue = asyncCache.get("name");
		
		int[] jamArray = sortOf(new int[] {5,45,34,64,51,23,87});
		
		for (int a:jamArray)
			out.println(a);
		
		try {
			
			String value = (String) futureValue.get();
			if (value == null) {
				out.println("<br/>Value doesn't exist. Written new value.");
				asyncCache.put("name", "Rajesh");
			}
			else {
				out.println("<br/>Value exists. Reading from Memcache<br/>");
				out.println(value);
			}
			
		}catch(InterruptedException | ExecutionException e) {
			out.println("exception :"+e.toString());
		}
		
	}

	private int[] sortOf(int[] array) {
		Arrays.sort(array);
		return array;
	}
}
