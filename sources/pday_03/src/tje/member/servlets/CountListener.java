package tje.member.servlets;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpSessionEvent;

import javax.servlet.http.HttpSessionListener;

public class CountListener implements HttpSessionListener {

	ServletContext ctx = null;

	static int current = 0;

	@Override

	public void sessionCreated(HttpSessionEvent e) {

		current++;

		ctx = e.getSession().getServletContext();

		ctx.setAttribute("currentUsers", current);

	}

	@Override

	public void sessionDestroyed(HttpSessionEvent e) {

		current--;

		ctx.setAttribute("currentUsers", current);

	}

}