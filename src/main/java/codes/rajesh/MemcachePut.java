package codes.rajesh;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheService.IdentifiableValue;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@WebServlet("idMemcache")
@SuppressWarnings("serial")
public class MemcachePut extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
	  
	  resp.setContentType("text/plain");
	  PrintWriter out = resp.getWriter();
	  
	  String path = req.getRequestURI();
	  if (path.startsWith("/favicon.ico")) {
		  return; 
	  }
    
	  String key = "id-value";
    
	  MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
	  syncCache.put(key, "Check",Expiration.byDeltaSeconds(60));
	  IdentifiableValue value = syncCache.getIdentifiable(key);
	  out.println(value.toString());
    
    

    //String key = "count-concurrent";
    // Using the synchronous cache.
    
    

    // Write this value to cache using getIdentifiable and putIfUntouched.
//    for (long delayMs = 1; delayMs < 1000; delayMs *= 2) {
//      IdentifiableValue oldValue = syncCache.getIdentifiable(key);
//      byte[] newValue = oldValue == null
//          ? BigInteger.valueOf(0).toByteArray()
//              : increment((byte[]) oldValue.getValue()); // newValue depends on old value
//      resp.setContentType("text/plain");
//      resp.getWriter().print("Value is " + new BigInteger(newValue).intValue() + "\n");
//      if (oldValue == null) {
//        // Key doesn't exist. We can safely put it in cache.
//        syncCache.put(key, newValue);
//        break;
//      } else if (syncCache.putIfUntouched(key, oldValue, newValue)) {
//        // newValue has been successfully put into cache.
//        break;
//      } else {
//        // Some other client changed the value since oldValue was retrieved.
//        // Wait a while before trying again, waiting longer on successive loops.
//        try {
//          Thread.sleep(delayMs);
//        } catch (InterruptedException e) {
//          throw new ServletException("Error when sleeping", e);
//        }
//      }
//    }
  }
}
