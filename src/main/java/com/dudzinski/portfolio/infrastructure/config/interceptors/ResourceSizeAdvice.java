package com.dudzinski.portfolio.infrastructure.config.interceptors;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ControllerAdvice
public class ResourceSizeAdvice implements ResponseBodyAdvice<Collection<?>> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Collection.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Collection<?> beforeBodyWrite(Collection<?> objects,
                                         MethodParameter methodParameter,
                                         MediaType mediaType,
                                         Class<? extends HttpMessageConverter<?>> aClass,
                                         ServerHttpRequest serverHttpRequest,
                                         ServerHttpResponse serverHttpResponse) {
        serverHttpResponse.getHeaders().add("X-Total-Count", String.valueOf(objects.size()));
        List<String> accessControlExposeHeaders = serverHttpResponse.getHeaders().getAccessControlExposeHeaders();
        List<String> newHeaders = new ArrayList<>(accessControlExposeHeaders);
        newHeaders.add("X-Total-Count");
        serverHttpResponse.getHeaders().setAccessControlExposeHeaders(newHeaders);
        return objects;
    }

}
