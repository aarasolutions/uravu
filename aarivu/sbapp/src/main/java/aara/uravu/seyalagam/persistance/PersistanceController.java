package aara.uravu.seyalagam.persistance;

import aara.uravu.seyalagam.persistance.dao.CasteDAO;
import aara.uravu.seyalagam.persistance.dao.MarriageDAO;
import aara.uravu.seyalagam.persistance.dao.MasterData;
import aara.uravu.seyalagam.persistance.dao.PersonDAO;
import aara.uravu.seyalagam.service.pojo.Dependants;
import aara.uravu.seyalagam.service.pojo.Marriage;
import aara.uravu.seyalagam.service.pojo.Parents;
import aara.uravu.seyalagam.service.pojo.Person;
import aara.uravu.seyalagam.persistance.repository.CasteRepository;
import aara.uravu.seyalagam.persistance.repository.MarriageRepository;
import aara.uravu.seyalagam.persistance.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
@EnableNeo4jRepositories("aara.uravu.seyalagam.persistance.repository")
public class PersistanceController {

    private static Long personCounter= new Long(1);
    private static Long marriageCounter=new Long(1);

    private final PersonRepository personRepository;
    private final MarriageRepository marriageRepository;
    private final CasteRepository casteRepository;

    public PersistanceController(PersonRepository personRepository, MarriageRepository marriageRepository,CasteRepository casteRepository) {
        this.personRepository = personRepository;
        this.marriageRepository = marriageRepository;
        this.casteRepository = casteRepository;
    }

    public Person createPerson(Person newPerson) {
        PersonDAO daoPerson = personRepository.save(convertToDAO(newPerson, true));
        newPerson.setAppId(daoPerson.getAppId());
        return newPerson;
    }

    public Person getPersonAndFamily(String personId){
        Person person = null;
        if(null != personId) {
            PersonDAO personDAO = personRepository.findByAppId(personId);
            if(null != personDAO){
                person = convertToPersonPojo(personDAO);
                if(null != personDAO.getBornFor()) {
                    Person appa = convertToPersonPojo(personDAO.getBornFor().getMappilai());
                    Person amma = convertToPersonPojo(personDAO.getBornFor().getPonnu());
                    person.setParents(new Parents(appa,amma,personDAO.getBornFor().getEventDate()));
                }

                List<MarriageDAO> marriageDAOs = marriageRepository.findMarriages(personDAO.getAppId());
                System.out.println(marriageDAOs);
                for(MarriageDAO singleMarriage :marriageDAOs) {
                    //(pojoInstance.getGender() == Gender.MALE) ? singleMarriage.getPonnu() : singleMarriage.getMappilai()
                    MarriageDAO foundMrgDAO = marriageRepository.findByAppId(singleMarriage.getAppId());
                    System.out.println("Getting all details of Marriage :"+foundMrgDAO);
                    Person spouse = convertToPersonPojo(foundMrgDAO.getPonnu());
                    Dependants dependant = new Dependants(spouse, foundMrgDAO.getEventDate());

                    for(PersonDAO child:foundMrgDAO.getChildren()){
                        dependant.addChild(convertToPersonPojo(child));
                    }
                    person.getDependants().add(dependant);
                }
            }
        }
        return person;
    }

    private Person convertToPersonPojo(PersonDAO personDAO) {
        Person pojoInstance = new Person();
        pojoInstance.setName(personDAO.getName());
       // pojoInstance.setGender(Gender.valueOf(personDAO.getGender()));
        pojoInstance.setAlive(personDAO.isAlive());
        pojoInstance.setBirthDate(personDAO.getBirthDate());
        return pojoInstance;
    }

    private PersonDAO convertToDAO(Person newPerson, boolean isNew) {
        PersonDAO daoInstance = new PersonDAO();
        daoInstance.setAlive(newPerson.isAlive());
        daoInstance.setGender(newPerson.getGender().name());
        daoInstance.setName(newPerson.getName());
        if(isNew)
            daoInstance.setAppId(personCounter++);
        return daoInstance;
    }


    public String createMarriageEvent(Marriage marriage) {
        PersonDAO mappilaiDAO = personRepository.findByAppId(marriage.getManamagan().getAppId());
        PersonDAO ponnuDAO = personRepository.findByAppId(marriage.getManamagal().getAppId());
        MarriageDAO marriageDAO = null;
        System.out.println(" Inputs :"+marriage);
        if(null == mappilaiDAO){
            System.out.println("Creating Mappillai");
            mappilaiDAO = personRepository.save(convertToDAO(marriage.getManamagan(), true));
        }
        System.out.println("MappillaiDAO :"+mappilaiDAO);

        if (null == ponnuDAO) {
            System.out.println("Creating Ponnu");
            ponnuDAO = personRepository.save(convertToDAO(marriage.getManamagal(), true));
        }
        System.out.println("Ponnu :"+ponnuDAO);

        if(null !=mappilaiDAO && null != ponnuDAO) {
            marriageDAO = new MarriageDAO();
            marriageDAO.setMappilai(mappilaiDAO);
            marriageDAO.setPonnu(ponnuDAO);
            marriageDAO.setAppId(marriageCounter++);
            System.out.println("Creating Marriage");
            MarriageDAO createMarriageDAO = marriageRepository.save(marriageDAO);
            System.out.println("Marriage :" + createMarriageDAO);
            return createMarriageDAO.getAppId();
        }else
            return "=1";
    }

    public String deleteAllPersons() {
        String result = "Deleting Persons :"+ personRepository.count();
        personRepository.deleteAll();
        return result;
    }

    public String deleteAllMarriageNodes() {
        String result = "Deleting Marriages :"+marriageRepository.count();
        marriageRepository.deleteAll();
        return result;
    }

    public void relateParents(String you, String appa, String amma) {
        PersonDAO youDao = personRepository.findByAppId(you);
        if(null != youDao){
            PersonDAO appaDao = personRepository.findByAppId(appa);
            PersonDAO ammaDao = personRepository.findByAppId(amma);
            if(null != appaDao && null != ammaDao){

            }
        }
    }

    public void addBirthByParentsMarriage(String personId, String marriageId, Date birthDate) {
        System.out.println("Adding personId :"+personId);
        System.out.println("Adding marriageId :"+marriageId);
        System.out.println("Adding birthDate :"+birthDate);
        if(null != marriageId){
            MarriageDAO marriageDAO = marriageRepository.findByAppId(marriageId);
            if(null != marriageDAO){
                System.out.println("Adding Marriage :"+marriageDAO);
                PersonDAO personDAO = personRepository.findByAppId(personId);
                if(null != personDAO) {
                    personDAO.setBornFor(marriageDAO);
                    personDAO.setBirthDate(birthDate);
                    personRepository.save(personDAO);
                    System.out.println("Updated Person :" + personDAO);
                }else{
                    System.err.println("No Person found");
                }
            }else{
                System.err.println("No Marriage Found");
            }
        }else{
            System.err.println("Invalid marriageId");
        }
    }


    public void createMasterDataCaste(){
        try {

            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            ClassLoader classLoader = getClass().getClassLoader();

            System.out.println(" Classloadder :"+classLoader);
            File file = new File(classLoader.getResource("masterData.json").getFile());
            //convert json string to object
            MasterData masterData = objectMapper.readValue(file, MasterData.class);

            System.out.println("Employee Object\n" + masterData);
            casteRepository.saveAll(masterData.getCasteList());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
