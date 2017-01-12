package edu.wmi.dpri.przychodnia.server.news.web.service;

import edu.wmi.dpri.przychodnia.commons.news.webmodel.NewsCrudModel;
import edu.wmi.dpri.przychodnia.server.entity.News;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.news.function.NewsToCrudWebModelFunction;
import edu.wmi.dpri.przychodnia.server.news.service.NewsService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.ROLE_ADMIN;


@Component
public class NewsWebService {

    @Inject
    private UserVerificationService userVerificationService;
    @Inject
    private NewsToCrudWebModelFunction function;
    @Inject
    private NewsService newsService;

    public NewsCrudModel createNews(NewsCrudModel model) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if(isAdmin) {
            News news = newsService.createNews(model);
            return function.apply(news);
        }
        ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
        throw new ForbiddenException(errorMessage);
    }



    public NewsCrudModel updateNews(NewsCrudModel model) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if(isAdmin) {
            News news = newsService.updateNews(model);
            return function.apply(news);
        }
        ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
        throw new ForbiddenException(errorMessage);
    }

    public void deleteNews(Long id) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if(isAdmin) {
            newsService.deleteNews(id);
        } else {
            ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
            throw new ForbiddenException(errorMessage);
        }
    }
}
