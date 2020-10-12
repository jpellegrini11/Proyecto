package com.filter;

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
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthorizationFilterEnt", urlPatterns = { "*.jsf" ,"*.xhtml"})
public class AuthorizationFilterEnt implements Filter {

	public AuthorizationFilterEnt() {
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			Integer index= reqURI.indexOf("/cargaPerfilEnt");

			System.out.println("imprime"+reqURI+""+index);
			
			if (reqURI.contains("/deportista/")&& ses.getAttribute("perfil").equals("ENTRENADOR"))
			{
				System.out.println(ses.getAttribute("perfil").equals("ENTRENADOR"));
				Long c= (Long) ses.getAttribute("compPerfil");
				if(c==0) {
					System.out.println("imprime PASO IF333");
					resp.sendRedirect(reqt.getContextPath() + "/entrenador/cargaPerfilEnt");
				}
				else {
				resp.sendRedirect(reqt.getContextPath() + "/entrenador/homeEnt.jsf");
				}
				
			}
			else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}