package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kmazu on 03.07.2016.
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Query("select a from Address a inner join fetch a.persons " +
            "where a.country like :likeString " +
            "or a.province like :likeString or a.city like :likeString " +
            "or a.street like :likeString or a.house like :likeString " +
            "or a.house like :likeString or a.apartment like :likeString " +
            "or a.postCode like :likeString")
    List<Address> searchQueryOnAll(@Param("likeString") String likeString);
}
