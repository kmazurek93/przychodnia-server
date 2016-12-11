package edu.wmi.dpri.przychodnia.server.repository.views;

import edu.wmi.dpri.przychodnia.server.entity.views.UsersData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lupus on 11.12.16.
 */
public interface UsersDataRepository
        extends CrudRepository<UsersData, Long>,
        PagingAndSortingRepository<UsersData, Long> {

    @Query("SELECT ud FROM UsersData ud WHERE ud.login like :likeLogin " +
            "and ud.email like :likeMail and ud.pesel like :likePesel " +
            "and ud.telephone like :likeTelephone and " +
            "(ud.address like :likeAddress or ud.mailingAddress like :likeAddress)")
    public List<UsersData> queryOnAll(@Param("likeLogin") String likeLogin, @Param("likeMail") String likeMail,
                                      @Param("likeTelephone") String likeTelephone, @Param("likeAddress") String likeAddress,
                                      @Param("likePesel") String likePesel);

    @Query("SELECT ud FROM UsersData ud WHERE ud.login like :likeLogin " +
            "and ud.email like :likeMail and ud.pesel like :likePesel " +
            "and ud.telephone like :likeTelephone and " +
            "(ud.address like :likeAddress or ud.mailingAddress like :likeAddress)")
    public Page<UsersData> queryOnAllPageable(@Param("likeLogin") String likeLogin, @Param("likeMail") String likeMail,
                                              @Param("likeTelephone") String likeTelephone, @Param("likeAddress") String likeAddress,
                                              @Param("likePesel") String likePesel, Pageable pageRequest);
}
