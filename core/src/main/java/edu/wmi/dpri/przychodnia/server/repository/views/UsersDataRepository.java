package edu.wmi.dpri.przychodnia.server.repository.views;

import edu.wmi.dpri.przychodnia.server.entity.views.UsersData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersDataRepository
        extends CrudRepository<UsersData, Long>,
        PagingAndSortingRepository<UsersData, Long> {

    @Query("SELECT ud FROM UsersData ud WHERE ud.login like :likeLogin " +
            "and ud.email like :likeMail and ud.pesel like :likePesel " +
            "and ud.telephone like :likeTelephone and " +
            "(ud.address like :likeAddress or ud.mailingAddress like :likeAddress) " +
            "and ud.name like :likeName")
    List<UsersData> queryOnAll(@Param("likeLogin") String likeLogin, @Param("likeMail") String likeMail,
                               @Param("likeTelephone") String likeTelephone, @Param("likeAddress") String likeAddress,
                               @Param("likePesel") String likePesel, @Param("likeName") String likeName);

    @Query("SELECT ud FROM UsersData ud WHERE ud.login like :likeLogin " +
            "and ud.email like :likeMail and ud.pesel like :likePesel " +
            "and ud.telephone like :likeTelephone and " +
            "(ud.address like :likeAddress or ud.mailingAddress like :likeAddress)" +
            "and ud.name like :likeName")
    Page<UsersData> queryOnAllPageable(@Param("likeLogin") String likeLogin, @Param("likeMail") String likeMail,
                                       @Param("likeTelephone") String likeTelephone, @Param("likeAddress") String likeAddress,
                                       @Param("likePesel") String likePesel, @Param("likeName") String likeName,
                                       Pageable pageRequest);
}
