package com.dudzinski.portfolio.infrastructure.config.interceptors;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class PageSizeAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Page.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object objects,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (objects instanceof Page) {
            Page<?> obj = (Page<?>) objects;
            serverHttpResponse.getHeaders().add("X-Total-Count", String.valueOf(obj.getTotalElements()));
            List<String> accessControlExposeHeaders = serverHttpResponse.getHeaders().getAccessControlExposeHeaders();
            List<String> newHeaders = new ArrayList<>(accessControlExposeHeaders);
            newHeaders.add("X-Total-Count");
            serverHttpResponse.getHeaders().setAccessControlExposeHeaders(newHeaders);
            return obj.toList();
        }
        return objects;
    }

}
