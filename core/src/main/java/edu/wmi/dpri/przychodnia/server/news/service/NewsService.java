package edu.wmi.dpri.przychodnia.server.news.service;

import edu.wmi.dpri.przychodnia.commons.news.webmodel.NewsCrudModel;
import edu.wmi.dpri.przychodnia.server.entity.News;
import edu.wmi.dpri.przychodnia.server.entity.enums.NewsType;
import edu.wmi.dpri.przychodnia.server.repository.NewsRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause.MODIFICATION;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;

/**
 * Created by lupus on 11.01.17.
 */
@Component
public class NewsService {

    @Inject
    private NewsRepository newsRepository;

    @Transactional
    public News createNews(NewsCrudModel model) {
        News news = new News();
        news.setAnonymous(true);
        news.setAuthor(null);
        news.setContent(model.getContent());
        news.setType(NewsType.INFO);
        news.setTitle(model.getTitle());
        news.setNewsDate(new DateTime());
        return newsRepository.save(news);
    }

    @Transactional
    public News updateNews(NewsCrudModel model) {
        News news = newsRepository.findOne(model.getId());
        throwExceptionIfNull(model.getId(), news, MODIFICATION, News.class);
        news.setTitle(model.getTitle());
        news.setContent(model.getContent());
        return newsRepository.save(news);
    }

    @Transactional
    public void deleteNews(Long id) {
        News news = newsRepository.findOne(id);
        throwExceptionIfNull(id, news, MODIFICATION, News.class);
        newsRepository.delete(id);
    }
}
