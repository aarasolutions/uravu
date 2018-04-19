package aara.uravu.seyalagam.persistance.repository;

import aara.uravu.seyalagam.persistance.dao.PersonDAO;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<PersonDAO, Long> {

    PersonDAO findByName(String name);

    PersonDAO findByAppId(String appId);

}