package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lupus on 04.01.17.
 */
@Repository
public interface NewsRepository extends CrudRepository<News, Long>, PagingAndSortingRepository<News, Long> {

}
