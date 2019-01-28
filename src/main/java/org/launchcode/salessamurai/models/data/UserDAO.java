package org.launchcode.salessamurai.models.data;

import org.launchcode.salessamurai.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDAO extends CrudRepository<Users, Integer> {

    public List<Users> findByUsername(String username);
}

