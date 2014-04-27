package ua.org.gostroy.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by panser on 4/27/14.
 */
public class ExecutionTimeInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startRequestTime", new Long( System.currentTimeMillis() ) );
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info( " execution time of " + request.getRequestURI() + ": "
                + ( System.currentTimeMillis() - ((Long)request.getAttribute("startRequestTime")).longValue() )
                + " ms" );
        super.postHandle(request, response, handler, modelAndView);
    }
}
