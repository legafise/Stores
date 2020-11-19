package com.lashkevich.stores.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {}, filterName = "PageRedirectSecurityFilter", initParams = {
        @WebInitParam(name = "CONTROLLER-PATH", value = "/controller") })
public class PageRedirectSecurityFilter implements Filter {
    private String indexPath;
    @Override
    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter("CONTROLLER-PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
