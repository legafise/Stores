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

@WebFilter(filterName = "GuestPageRedirectFilter", initParams = {
        @WebInitParam(name = "GUEST-INFO-PAGE-PATH", value = "/controller?command=guest_info")
})
public class GuestPageRedirectFilter implements Filter {
    private static final String ROLE_PARAMETER_IN_REQUEST = "role";
    private static final String COMMAND_PARAMETER_IN_REQUEST = "command";
    private static final String GUEST_ROLE = "guest";
    private String guestInfoPagePath;
    private ArrayList<CommandType> forbiddenGuestCommands;

    @Override
    public void init(FilterConfig filterConfig) {
        guestInfoPagePath = filterConfig.getInitParameter("GUEST-INFO-PAGE-PATH");
        forbiddenGuestCommands = new ArrayList<>(
                Arrays.asList(CommandType.LOG_OUT, CommandType.PROFILE, CommandType.BASKET_PAGE, CommandType.ADD_GOOD,
                        CommandType.REMOVE_GOOD, CommandType.CLEAR_BASKET));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String role = (String) request.getSession().getAttribute(ROLE_PARAMETER_IN_REQUEST);
        String command = request.getParameter(COMMAND_PARAMETER_IN_REQUEST);

        if (command != null && role.equals(GUEST_ROLE) && forbiddenGuestCommands.stream()
                .anyMatch(currentCommand -> currentCommand.getCommandName().toUpperCase().equals(command.toUpperCase()))) {
            response.sendRedirect(request.getContextPath() + guestInfoPagePath);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
