package edu.wmi.dpri.przychodnia.server.publics.web.service;

import edu.wmi.dpri.przychodnia.commons.common.webmodel.NewsWebModel;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.PageRequestModel;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.SimplePage;
import edu.wmi.dpri.przychodnia.server.entity.News;
import edu.wmi.dpri.przychodnia.server.publics.function.NewsToWebModelFunction;
import edu.wmi.dpri.przychodnia.server.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Created by lupus on 04.01.17.
 */
@Component
public class PublicNewsWebService {
    @Inject
    private NewsRepository newsRepository;
    @Inject
    private NewsToWebModelFunction function;

    @Transactional(readOnly = true)
    public SimplePage<NewsWebModel> getNewsPage(PageRequestModel model) {
        Sort sortingProperties = new Sort(DESC, "newsDate");
        PageRequest pageRequest = new PageRequest(model.getPage(), model.getSize(), sortingProperties);
        Page<News> newsPage = newsRepository.findAll(pageRequest);

        SimplePage<NewsWebModel> outcome = new SimplePage<>();
        outcome.setNumberOfPages(newsPage.getTotalPages());

        List<NewsWebModel> newsWebModels = newsPage.getContent()
                .stream().map(function::apply)
                .collect(Collectors.toList());

        outcome.setList(newsWebModels);
        return outcome;
    }
}
