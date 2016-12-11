package edu.wmi.dpri.przychodnia.server.repository.views;

import edu.wmi.dpri.przychodnia.server.entity.views.PatientsData;
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
public interface PatientsDataRepository
        extends CrudRepository<PatientsData, Long>,
        PagingAndSortingRepository<PatientsData, Long> {

    @Query("SELECT pd FROM PatientsData pd WHERE pd.login like :likeLogin " +
            "and pd.email like :likeMail and pd.pesel like :likePesel " +
            "and pd.telephone like :likeTelephone and " +
            "(pd.address like :likeAddress or pd.mailingAddress like :likeAddress) " +
            "and pd.name like :likeName")
    List<PatientsData> queryOnAll(@Param("likeLogin") String likeLogin, @Param("likeMail") String likeMail,
                                         @Param("likeTelephone") String likeTelephone, @Param("likeAddress") String likeAddress,
                                         @Param("likePesel") String likePesel, @Param("likeName") String likeName);

    @Query("SELECT pd FROM PatientsData pd WHERE pd.login like :likeLogin " +
            "and pd.email like :likeMail and pd.pesel like :likePesel " +
            "and pd.telephone like :likeTelephone and " +
            "(pd.address like :likeAddress or pd.mailingAddress like :likeAddress)" +
            "and pd.name like :likeName")
    Page<PatientsData> queryOnAllPageable(@Param("likeLogin") String likeLogin, @Param("likeMail") String likeMail,
                                                 @Param("likeTelephone") String likeTelephone, @Param("likeAddress") String likeAddress,
                                                 @Param("likePesel") String likePesel, @Param("likeName") String likeName,
                                                 Pageable pageRequest);

}
