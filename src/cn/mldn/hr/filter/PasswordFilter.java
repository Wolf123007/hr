package cn.mldn.hr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@WebFilter(urlPatterns={"/pages/admin_change_password.jsp"})
public class PasswordFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		if (session.getAttribute("fadmin") != null || session.getAttribute("badmin") != null) {
			chain.doFilter(req, resp);
		} else {
			request.setAttribute("msg", "您还未登录，请先登录！");
			request.setAttribute("url", "/login.jsp");
			request.getRequestDispatcher("/pages/forward.jsp").forward(req, resp);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
