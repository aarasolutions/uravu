package aara.uravu.seyalagam.persistance.repository;

import aara.uravu.seyalagam.persistance.dao.MarriageDAO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarriageRepository extends CrudRepository<MarriageDAO, String> {

    @Query("MATCH (m:MarriageDAO)<-[r:MAPPILLAI]-(a:Person) RETURN m,r,a LIMIT {limit}")
    public MarriageDAO findMarriage(@Param("appaId") String appaDaoId,@Param("ammaId") String ammaDAOId);

    MarriageDAO findByAppId(String marriageId);

//MATCH (per:PersonDAO {appId:"P-15"})<-[r:MAPPILAI]-(mrg:MarriageDAO)-[r2:PONNU]->(spouse:PersonDAO) return spouse
    @Query("MATCH (per:PersonDAO {appId:{personId}})<-[r:MAPPILAI]-(mrg:MarriageDAO) return mrg")
    List<MarriageDAO> findMarriages(@Param("personId") String personId);
}
