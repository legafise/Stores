package com.lashkevich.stores.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PageRedirectSecurityFilter", initParams = {
        @WebInitParam(name = "ERROR-PAGE-PATH", value = "/controller?command=error")
})
public class PageRedirectSecurityFilter implements Filter {
    private String errorPath;
    @Override
    public void init(FilterConfig filterConfig) {
        errorPath = filterConfig.getInitParameter("ERROR-PAGE-PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        httpResponse.sendRedirect(httpRequest.getContextPath() + errorPath);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
