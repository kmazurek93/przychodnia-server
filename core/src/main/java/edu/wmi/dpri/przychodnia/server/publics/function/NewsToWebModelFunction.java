package edu.wmi.dpri.przychodnia.server.publics.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.NewsWebModel;
import edu.wmi.dpri.przychodnia.server.entity.News;
import org.springframework.stereotype.Component;

/**
 * Created by lupus on 04.01.17.
 */
@Component
public class NewsToWebModelFunction implements Function<News, NewsWebModel> {

    @Override
    public NewsWebModel apply(News input) {
        NewsWebModel outcome = new NewsWebModel();
        outcome.setContent(input.getContent());
        outcome.setId(input.getId());
        outcome.setTimestamp(input.getNewsDate().getMillis());
        outcome.setTitle(input.getTitle());
        outcome.setType(input.getType().toString());
        return outcome;
    }
}
