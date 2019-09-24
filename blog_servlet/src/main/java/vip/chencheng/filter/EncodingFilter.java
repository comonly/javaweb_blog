package vip.chencheng.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
      private FilterConfig fc=null;//过滤器初始化配置对象
      
      public void init(FilterConfig fc) throws ServletException {
    	        this.fc=fc;
    	}
      
	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");

		//将请求转发给过滤器链中的下一个过滤器，如果过滤器链没有其他过滤器了，则请求到达目标资源
		chain.doFilter(req, resp);
	}
}
