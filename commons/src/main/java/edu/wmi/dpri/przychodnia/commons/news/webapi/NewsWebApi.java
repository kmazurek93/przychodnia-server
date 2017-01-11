package edu.wmi.dpri.przychodnia.commons.news.webapi;

import edu.wmi.dpri.przychodnia.commons.news.webmodel.NewsCrudModel;

import javax.ws.rs.*;

import static edu.wmi.dpri.przychodnia.commons.news.webapi.NewsWebApi.BASE_PATH;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by lupus on 11.01.17.
 */
@Path(BASE_PATH)
public interface NewsWebApi {
    String BASE_PATH = "/manageNews";
    String NEWS = "/news";

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path(NEWS)
    NewsCrudModel createNews(NewsCrudModel model);

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path(NEWS)
    NewsCrudModel updateNews(NewsCrudModel model);

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/news/{id}")
    void deleteNews(@PathParam("id") Long id);


}
