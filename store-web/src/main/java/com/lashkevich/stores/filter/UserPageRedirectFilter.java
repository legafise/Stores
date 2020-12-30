package com.lashkevich.stores.filter;

import com.lashkevich.stores.command.CommandType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebFilter(filterName = "UserPageRedirectFilter", initParams = {
        @WebInitParam(name = "CATALOG-PATH", value = "/controller?command=catalog")
})
public class UserPageRedirectFilter implements Filter {
    private static final String ROLE_PARAMETER_IN_REQUEST = "role";
    private static final String COMMAND_PARAMETER_IN_REQUEST = "command";
    private static final String GUEST_ROLE = "guest";
    private String catalogPath;
    private ArrayList<CommandType> forbiddenUserCommands;

    @Override
    public void init(FilterConfig filterConfig) {
        catalogPath = filterConfig.getInitParameter("CATALOG-PATH");
        forbiddenUserCommands = new ArrayList<>(
                Arrays.asList(CommandType.AUTHORIZATION, CommandType.AUTHORIZATION_PAGE, CommandType.REGISTRATION, CommandType.REGISTRATION_PAGE));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String role = (String) request.getSession().getAttribute(ROLE_PARAMETER_IN_REQUEST);
        String command = request.getParameter(COMMAND_PARAMETER_IN_REQUEST);

        if (command != null && !role.equals(GUEST_ROLE) && forbiddenUserCommands.stream()
                .anyMatch(currentCommand -> currentCommand.getCommandName().toUpperCase().equals(command.toUpperCase()))) {
            response.sendRedirect(request.getContextPath() + catalogPath);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
