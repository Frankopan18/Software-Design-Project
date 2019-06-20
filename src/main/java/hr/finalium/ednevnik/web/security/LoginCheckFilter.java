package hr.finalium.ednevnik.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web filter koji preusmjerava sve korisnike koji nisu logirani na
 * početnu/login stranicu.
 * 
 * @author Zlatko
 *
 */
@WebFilter("/servleti/*")
public class LoginCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			if (req.getSession().getAttribute("current.user.mail") == null) {
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
