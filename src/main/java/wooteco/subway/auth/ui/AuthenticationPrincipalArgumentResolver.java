package wooteco.subway.auth.ui;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import wooteco.subway.auth.application.AuthService;
import wooteco.subway.auth.domain.AuthenticationPrincipal;
import wooteco.subway.auth.domain.AuthorizationPayLoad;
import wooteco.subway.auth.infrastructure.AuthorizationExtractor;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthService authService;

    public AuthenticationPrincipalArgumentResolver(final AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public AuthorizationPayLoad resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                                final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        final String token = AuthorizationExtractor.extract(httpServletRequest);
        return authService.getPayLoad(token);
    }
}