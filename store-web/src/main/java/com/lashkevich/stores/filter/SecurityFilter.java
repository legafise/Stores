package com.lashkevich.stores.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    private static final String ROLE_PARAMETER_IN_REQUEST = "role";
    private static final String GUEST_ROLE = "guest";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(ROLE_PARAMETER_IN_REQUEST);

        if (role == null) {
            session.setAttribute("role", GUEST_ROLE);
            session.setAttribute("currencyId", "1");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
