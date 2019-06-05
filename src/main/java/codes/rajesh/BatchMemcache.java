package codes.rajesh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@WebServlet("batchMemcache")
@SuppressWarnings("serial")
public class BatchMemcache extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String requestValue = request.getParameter("data");
		
		List<String> productList=new ArrayList<>();
		productList.add("productName");
		productList.add("productCost");
		productList.add("productDesc");
		
		Map<String,Object> productMap = new HashMap<>();
		productMap.put("productName","Google Home");
		productMap.put("productCost", 10000);
		productMap.put("productDesc", "It is automated system that makes things easy.");
		
		MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();
		
		switch(requestValue) {
		
			case "save":
				memcacheService.putAll(productMap);
				out.println("Product has been written to memcache.");
				break;
			
			case "delete":
				memcacheService.deleteAll(productList);
				out.println("The product has been deleted");
				break;
				
			case "view":
				Map<String,Object> map = memcacheService.getAll(productList);
				for(Object o:map.values())
					out.println(o.toString()+"<br/>");
				break;
			
			default:
				out.print("Invalid request");
			
		}
		
	}
}
