package management;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	public static void alertLocation(String mag, String url, JspWriter out) {
		try {
			String script = ""
					+"<script>"
					+"	alert('"+mag+"');"
					+"	location.href='"+url+"';"
					+"</script>";
			out.print(script);
			
		} catch (Exception e) {}
	}
	
	public static void alertBack(String mag, JspWriter out) {
		try {
			String script = ""
					+"<script>"
					+"	alert('"+mag+"');"
					+"	history.back();"
					+"</script>";
			out.print(script);
			
		} catch (Exception e) {}
	}
}
