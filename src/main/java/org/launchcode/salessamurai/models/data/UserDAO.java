package org.launchcode.salessamurai.models.data;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserDAO extends CrudRepository<Users, Integer> {

    public List<Users> findByUsername(String username);
}

