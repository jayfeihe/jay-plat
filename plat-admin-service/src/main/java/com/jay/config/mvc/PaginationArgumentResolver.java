package com.jay.config.mvc;

import com.jay.vo.base.page.Pagination;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by hetiewei on 2017/3/2.
 */
public class PaginationArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Pagination.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Pagination pagination = new Pagination();
        String start = webRequest.getParameter("start");
        String limit = webRequest.getParameter("limit");
        if (start != null && limit != null) {
            pagination.setPageSize(Integer.valueOf(limit));
            pagination.setCurrentPage(Integer.valueOf(start) / Integer.valueOf(limit) + 1);
        }
        String count = webRequest.getParameter("count");
        if (count != null) {
            pagination.setCount(Integer.valueOf(count));
        }
        return pagination;
    }
}
