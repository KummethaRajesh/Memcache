package codes.rajesh;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("memcacheJCache")
public class MemcacheJCache extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("html/text");
		
		Cache cache = null;
		
		try {
	          CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
	          cache = cacheFactory.createCache(Collections.emptyMap());
	      } catch (CacheException e) {
	          out.println(e.toString());
	      }
		
		Map<String,String> map = new HashMap<>();
		map.put("name", "Rajesh");
		map.put("age", "21");
		
		String name = (String) cache.get("name");
		String age = (String) cache.get("age");
		
		out.println(name+age);
	}
}
