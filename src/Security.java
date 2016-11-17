import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "Security", urlPatterns = "/open2")
public class Security implements Filter {

    private static String allowIP = "10.92.177.166";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        String ipAddr = req.getRemoteAddr();

        System.out.println("IP-Addr: " + ipAddr);

        if (ipAddr.equals(allowIP)) {
            chain.doFilter(request, response);
        } else {

        	PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<body>");
	        out.println("ACCESS DENIED NEGUIN!");
	        out.println("IP-Addr: " + ipAddr);
	        out.println("</body>");
	        out.println("</html>");
	        
	       

        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}