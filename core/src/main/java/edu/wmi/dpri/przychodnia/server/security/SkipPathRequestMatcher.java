package edu.wmi.dpri.przychodnia.server.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 22.10.16.
 */
public class SkipPathRequestMatcher implements RequestMatcher {
    private OrRequestMatcher matchers;
    private RequestMatcher processingMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        Assert.notNull(pathsToSkip);
        List<RequestMatcher> m = pathsToSkip.stream()
                .map(AntPathRequestMatcher::new)
                .collect(Collectors.toList());
        matchers = new OrRequestMatcher(m);
        processingMatcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        } else if (matchers.matches(request)) {
            return false;
        }
        return processingMatcher.matches(request);
    }
}
