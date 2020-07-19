package com.personal.common.core.filter;

import com.personal.core.constant.CommonConstant;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author cgc6828
 * @className LogbackFilter
 * @description TODO
 * @date {DATE}{TIME}
 */
public class LogbackFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String traceId = httpServletRequest.getHeader("traceId");
        if (StringUtils.isBlank(traceId)) {
            traceId = RandomStringUtils.randomAlphanumeric(5);
        }
        MDC.put(CommonConstant.THREAD_ID, traceId);
        String str = ((HttpServletRequest) request).getMethod();
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CommonConstant.THREAD_ID);
        }
    }

    @Override
    public void destroy() {

    }

}


