package com.study.readinglist.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.study.readinglist.domain.Reader;

/*
 * HandlerMethodArgumentResolver 인터페이스는 컨트롤러에서 파라미터를 바인딩해주는 역할을 한다.
 */
public class ReaderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) { // 바인딩할 클래스 지정.
		return Reader.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception { // 바인딩할 클래스 조작.
		
		Authentication auth = (Authentication) webRequest.getUserPrincipal();
		return auth != null && auth.getPrincipal() instanceof Reader ? auth.getPrincipal() : null;
	}

}
