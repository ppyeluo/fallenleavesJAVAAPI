package com.sspu.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 配置编码过滤器，将请求和响应的内容都以UTF-8进行编码，以防止出现中文乱码
@WebFilter(filterName = "encodingFilter",urlPatterns = "/*") //程序启动，自动将EncodingFilter.java过滤器加载到程序中
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {}
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {}
}