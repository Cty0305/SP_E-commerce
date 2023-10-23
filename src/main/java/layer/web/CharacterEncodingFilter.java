package layer.web;

import jakarta.servlet.*;

import java.io.IOException;

public class CharacterEncodingFilter implements Filter{
    private String encoding;
    @Override
    public void init(FilterConfig filterConfig){
        encoding = filterConfig.getInitParameter("encoding");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(encoding!=null){
            servletRequest.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
