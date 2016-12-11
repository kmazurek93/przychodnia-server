package edu.wmi.dpri.przychodnia.server.repository.views;

import edu.wmi.dpri.przychodnia.server.entity.views.EmployeesData;
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
public interface EmployeesDataRepository
        extends CrudRepository<EmployeesData, Long>,
        PagingAndSortingRepository<EmployeesData, Long> {

    @Query("SELECT ed FROM EmployeesData ed WHERE ed.login like :likeLogin " +
            "and ed.email like :likeMail and ed.pesel like :likePesel " +
            "and ed.telephone like :likeTelephone and " +
            "(ed.address like :likeAddress or ed.mailingAddress like :likeAddress) " +
            "and ed.name like :likeName")
    public List<EmployeesData> queryOnAll(@Param("likeLogin") String likeLogin, @Param("likeMail") String likeMail,
                                          @Param("likeTelephone") String likeTelephone, @Param("likeAddress") String likeAddress,
                                          @Param("likePesel") String likePesel, @Param("likeName") String likeName);

    @Query("SELECT ed FROM EmployeesData ed WHERE ed.login like :likeLogin " +
            "and ed.email like :likeMail and ed.pesel like :likePesel " +
            "and ed.telephone like :likeTelephone and " +
            "(ed.address like :likeAddress or ed.mailingAddress like :likeAddress)" +
            "and ed.name like :likeName")
    public Page<EmployeesData> queryOnAllPageable(@Param("likeLogin") String likeLogin, @Param("likeMail") String likeMail,
                                                  @Param("likeTelephone") String likeTelephone, @Param("likeAddress") String likeAddress,
                                                  @Param("likePesel") String likePesel, @Param("likeName") String likeName,
                                                  Pageable pageRequest);

}
