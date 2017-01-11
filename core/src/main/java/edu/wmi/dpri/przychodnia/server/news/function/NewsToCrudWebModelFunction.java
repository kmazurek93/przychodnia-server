package edu.wmi.dpri.przychodnia.server.news.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.news.webmodel.NewsCrudModel;
import edu.wmi.dpri.przychodnia.server.entity.News;
import org.springframework.stereotype.Component;

/**
 * Created by lupus on 11.01.17.
 */
@Component
public class NewsToCrudWebModelFunction implements Function<News, NewsCrudModel> {
    @Override
    public NewsCrudModel apply(News input) {
        NewsCrudModel output = new NewsCrudModel();
        output.setContent(input.getContent());
        output.setTitle(input.getTitle());
        output.setId(input.getId());
        return output;
    }
}
