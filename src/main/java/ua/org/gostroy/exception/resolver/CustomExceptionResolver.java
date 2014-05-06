package ua.org.gostroy.exception.resolver;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by panser on 5/6/14.
 */
public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView getModelAndView(String viewName, Exception ex) {
        return getCustomModelAndView(viewName, ex, null);
    }

    @Override
    protected ModelAndView getModelAndView(String viewName, Exception ex,
                                           HttpServletRequest request) {
        return getCustomModelAndView(viewName, ex, request);
    }

    private ModelAndView getCustomModelAndView(String viewName, Exception ex,
                                               HttpServletRequest request){
        ModelAndView mv = super.getModelAndView(viewName, ex);

        mv.addObject("funnyMessage", "view: " + viewName
                + " exceptionMessage: " + ex.getMessage()
                + " queryString: " + request.getQueryString() );

        return mv;
    }
}
