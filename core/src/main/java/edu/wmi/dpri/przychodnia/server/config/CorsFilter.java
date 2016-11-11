package edu.wmi.dpri.przychodnia.server.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        setHeaders(request, response);
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

    public static Authentication doCorsAndReturnNull(HttpServletRequest req, HttpServletResponse res) {
        setHeaders(req, res);
        return null;
    }

    private static void setHeaders(HttpServletRequest req, HttpServletResponse res) {
        String corsRequestHeaders = req.getHeader("Access-Control-Request-Headers");
        if (corsRequestHeaders != null) {
            res.setHeader("Access-Control-Allow-Headers", corsRequestHeaders);
        } else {

            res.setHeader("Access-Control-Allow-Headers", "Content-Type");
        }
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
    }
}
