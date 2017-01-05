package edu.wmi.dpri.przychodnia.server.publics.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.NewsWebModel;
import edu.wmi.dpri.przychodnia.server.entity.News;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import org.springframework.stereotype.Component;


/**
 * Created by lupus on 04.01.17.
 */
@Component
public class NewsToWebModelFunction implements Function<News, NewsWebModel> {

    public static final String ZESPOL_PRZYCHODNI = "Zespół przychodni";

    @Override
    public NewsWebModel apply(News input) {
        NewsWebModel outcome = new NewsWebModel();
        outcome.setContent(input.getContent());
        outcome.setId(input.getId());
        outcome.setTimestamp(input.getNewsDate().getMillis());
        outcome.setTitle(input.getTitle());
        outcome.setType(input.getType().toString());
        outcome.setAuthor(determineAuthor(input));
        return outcome;
    }

    private String determineAuthor(News input) {
        if (input.getAnonymous()) {
            return ZESPOL_PRZYCHODNI;
        } else {
            return concatenateNames(input.getAuthor().getPerson());
        }
    }

    private String concatenateNames(Person person) {
        return person.getFirstName() + " " + person.getLastName();
    }
}
