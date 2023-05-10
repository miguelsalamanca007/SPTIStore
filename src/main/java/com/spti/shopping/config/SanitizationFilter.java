package com.spti.shopping.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/*")
public class SanitizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Envuelve la solicitud en una solicitud personalizada que aplica la sanitización
        HttpServletRequest sanitizedRequest = new SanitizedRequestWrapper(req);

        chain.doFilter(sanitizedRequest, res);
    }

    @Override
    public void destroy() {
    }
}



