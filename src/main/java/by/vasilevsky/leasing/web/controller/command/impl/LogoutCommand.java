package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.UrlMapping;
import by.vasilevsky.leasing.web.filter.security.ProfileAccessFilter;

public class LogoutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setAttribute(ProfileAccessFilter.USER_ROLE_ALIAS, UserRole.ANONYMOUS.toString());
		
		response.sendRedirect(UrlMapping.HOME);
	}
}
