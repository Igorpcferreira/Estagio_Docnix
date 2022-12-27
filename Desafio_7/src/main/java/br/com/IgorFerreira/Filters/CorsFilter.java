package br.com.IgorFerreira.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter(filterName = "CorsFilter")
public class CorsFilter implements Filter {

    public void init(FilterConfig config){
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Headers", "*");
        resp.addHeader("Access-Control-Allow-Methods", "*");
        chain.doFilter(request, response);
    }

    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
