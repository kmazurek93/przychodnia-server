package edu.wmi.dpri.przychodnia.server.news.web.api;

import edu.wmi.dpri.przychodnia.commons.news.webapi.NewsWebApi;
import edu.wmi.dpri.przychodnia.commons.news.webmodel.NewsCrudModel;
import edu.wmi.dpri.przychodnia.server.news.web.service.NewsWebService;

import javax.inject.Inject;



public class NewsWebApiImpl implements NewsWebApi {

    @Inject
    private NewsWebService newsWebService;


    @Override
    public NewsCrudModel createNews(NewsCrudModel model) {
        return newsWebService.createNews(model);
    }

    @Override
    public NewsCrudModel updateNews(NewsCrudModel model) {
        return newsWebService.updateNews(model);
    }

    @Override
    public void deleteNews(Long id) {
        newsWebService.deleteNews(id);

    }
}
