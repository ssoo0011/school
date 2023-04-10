package management;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieManager {
	
	public static void makeCookie(HttpServletResponse response, String cName,
			String cValue, int cTime) {
		
		Cookie cookie = new Cookie(cName, cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);//아무것도 넣지 않으면 -1, 0이면 유지시간 0초
		response.addCookie(cookie);
	}
	
	public static String readCookie(HttpServletRequest request, String cName) {
		
		String cookieValue = "";
		
		Cookie[]cookies = request.getCookies();
		if (cookies !=null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				if (cookieName.equals(cName)) {
					cookieValue = c.getValue();
				}
			}
		}
		return cookieValue;
				
	}
	
	public static void deleteCookie(HttpServletResponse response, String cName) {
		
		makeCookie(response, cName, "", 0);
	}
}
