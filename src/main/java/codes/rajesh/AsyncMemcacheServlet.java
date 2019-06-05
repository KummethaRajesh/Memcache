package codes.rajesh;

import java.io.IOException;
import java.io.PrintWriter;
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
	      return; 
	    }
		
		AsyncMemcacheService asyncCache = MemcacheServiceFactory.getAsyncMemcacheService();
		asyncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		
//		int[] jamArray = sortOf(new int[] {5,45,34,64,51,23,87});
//		out.println("Printing sorted array");
//		for (int a:jamArray)
//			out.println(a+"<br/>");
		
		
		try {
			
//			asyncCache.put("aname", "Rajesh1");
//			Future<Object> s1 = asyncCache.get("aname");
//			out.println(s1.get()+"<br/>");
//			
//			asyncCache.put("aname", "Rajesh2");
//			Future<Object> s2 = asyncCache.get("aname");
//			out.println(s2.get()+"<br/>");
//			
//			asyncCache.put("aname", "Rajesh3");
//			Future<Object> s3 = asyncCache.get("aname");
//			out.println(s3.get()+"<br/>");
//			
//			asyncCache.put("aname", "Rajesh4");
//			Future<Object> s4 = asyncCache.get("aname");
//			out.println(s4.get()+"<br/>");
//			
//			asyncCache.put("aname", "Rajesh5");
//			Future<Object> s5 = asyncCache.get("aname");
//			out.println(s5.get()+"<br/>");
//			
//			asyncCache.put("aname", "Rajesh6");
//			Future<Object> s6 = asyncCache.get("aname");
//			out.println(s6.get()+"<br/>");
//			
//			asyncCache.put("aname", "Rajesh7");
//			Future<Object> s7 = asyncCache.get("aname");
//			out.println(s7.get()+"<br/>");
//			
//			asyncCache.put("aname", "Rajesh8");
//			Future<Object> s8 = asyncCache.get("aname");
//			out.println(s8.get()+"<br/>");
//			
//			asyncCache.put("aname", "Rajesh9");
//			Future<Object> s9 = asyncCache.get("aname");
//			out.println(s9.get()+"<br/>");
			
			asyncCache.put("aCount", 0);
			
			for(int i=0; i<100; i++) {
				asyncCache.increment("aCount", 20);
			}
			
			for(int i=0; i<50; i++) {
				asyncCache.increment("aCount", 40);
			}
			
			for(int i=0; i<50; i++) {
				asyncCache.increment("aCount", 60);
			}
			
			Future<Object> futureValue = asyncCache.get("aCount");
			int c = (int) futureValue.get();
			out.println(c);
			
			
		}catch(InterruptedException | ExecutionException e) {
			out.println("exception :"+e.toString());
		}
		
		//out.println("<br/><br/><strong><a style='cursor:pointer' onclick=reload1()>Refresh</a><script>function reload1(){window.location.reload()}</script></strong>");
		
	}
}
