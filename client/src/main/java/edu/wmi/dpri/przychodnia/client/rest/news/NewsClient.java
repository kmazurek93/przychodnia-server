package edu.wmi.dpri.przychodnia.client.rest.news;

import edu.wmi.dpri.przychodnia.commons.news.webapi.NewsWebApi;
import edu.wmi.dpri.przychodnia.commons.news.webmodel.NewsCrudModel;
import edu.wmi.dpri.przychodnia.commons.rest.GenericRestClient;

public class NewsClient extends GenericRestClient<NewsWebApi> {
    public NewsClient(String url) {
        super(url, NewsWebApi.class);
    }

    public NewsCrudModel createNews(NewsCrudModel model) {
        return resource.createNews(model);
    }

    public NewsCrudModel updateNews(NewsCrudModel model) {
        return resource.updateNews(model);
    }

    public void deleteNews(Long id) {
        resource.deleteNews(id);
    }
}
