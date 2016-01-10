package org.sample.gcm.server.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Carlo on 10/01/2016.
 */
public interface AccountRepository extends CrudRepository<String, Account>{

    public List<Account> findById(String id);

}
