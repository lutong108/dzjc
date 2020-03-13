package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AccessFilter implements Filter{
    @Override
    public void destroy() {
      // TODO Auto-generated method stub
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization, Show-In-UI-View");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
