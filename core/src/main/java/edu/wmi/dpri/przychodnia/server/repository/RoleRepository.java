package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

    List<Role> findByNameIn(List<String> names);

    @Query("select r from Role r inner join fetch r.users u inner join fetch u.person where r.name like :likeName")
    List<Role> findByNameLike(@Param("likeName") String likeName);
}
