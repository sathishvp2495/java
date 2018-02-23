import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jbase.jremote.io.Response;
import com.nys.common.objects.NSCCorbanInitialise;


@RestController
@CrossOrigin
@RequestMapping("/")
public class IPRestriction {
	
	@RequestMapping(value = "ipblock")
	public static JSONArray ipres(){
		System.out.println("called this program");
		JSONObject jo = new JSONObject();
		jo.put("error_code", "403");
		jo.put("error_message", "Forbidden: You don't have permission to access [directory] on this server");

		JSONArray result = new JSONArray();
		result.add(jo);

		JSONObject mainObj = new JSONObject();
		mainObj.put("JSON", result);
		System.out.println("JSON_RESPONSE = "+result);
		return result;
		
	}
	 
	 static String remoteAddr = "";
	 static String v;
	 @RequestMapping(value = "Homes")
		private static String getClientIp(HttpServletRequest request) throws UnknownHostException {

		 String ip=NSCCorbanInitialise.getIpAddress();  //get the ip address from the property file
		 String allIP = ip.toString();
		 String[] iplist = allIP.split(",");
		 int j = iplist.length-1;
		 
		 for(int i=0;i<=allIP.length();i++){
			 String iplst = iplist[i];
			 
			//get the client's ip 
			 
		        if (request != null) {
		            remoteAddr = request.getHeader("X-FORWARDED-FOR"); 
		            if (remoteAddr == null || "".equals(remoteAddr)) {
		                remoteAddr = request.getRemoteAddr();
		            }
		        }
		        
		        // checking process
		        System.out.println("remoteAddr:::"+remoteAddr);
		        if(remoteAddr.equals(iplst)) {
		        	v = "valid";
		        }else {
		        	v = "invalid";
		        }
		       
			 
			 if(remoteAddr.equals(iplst) || i==j){
				 System.out.println("break caled");
				 break;
			 }
		 }
		 return "your ip "+ remoteAddr +" "+ " is "+ v;
	    }
	 
	
	 public static void main(String args[]) throws UnknownHostException{
		 IPRestriction ad = new IPRestriction();
		 ad.ipres();
		 
		 //testIP();
		 //ad.isValid();
		 
		 
	 }
	
	
	
	
	
	

}
