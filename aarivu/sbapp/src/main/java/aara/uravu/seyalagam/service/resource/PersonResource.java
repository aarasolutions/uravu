package aara.uravu.seyalagam.service.resource;

import aara.uravu.seyalagam.persistance.PersistanceController;
import aara.uravu.seyalagam.service.pojo.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class PersonResource {

    private final PersistanceController controller;

    public PersonResource(PersistanceController controller) {
        this.controller = controller;
    }


    @RequestMapping(value = "/person/{prsAppId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get All details with PersonId", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Creates a Person"),
    })
    public Person getPersonDetails(@PathVariable("prsAppId") String prsAppId){
        return controller.getPersonAndFamily(prsAppId);
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    @ApiOperation(value = "Create A Person", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Creates a Person"),
    })
    public String createPerson(@RequestBody Person personDetails) {
        if (personDetails.validate()) {
            System.out.println(personDetails);
            controller.createPerson(personDetails);
            System.out.println(personDetails);
            return personDetails.getAppId();
        } else
            return "-1";
    }

    @RequestMapping(value="/person", method = RequestMethod.DELETE)
    @ApiOperation(value = "Cleaning up all data stored", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully cleaned all data"),
    })
    public String deletaAll(){
        StringBuffer result = new StringBuffer();
        result.append(controller.deleteAllPersons());
        result.append(controller.deleteAllMarriageNodes());
        return result.toString();
    }

    @RequestMapping(value = "/person/{personId}/birth/{marriageId}/{dateOfBirth}", method = RequestMethod.PATCH)
    @ApiOperation(value = "Relating You with your Appa & Amma", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully cleaned all data"),
    })
    public String addBirth(@PathVariable("personId") String you, @PathVariable("marriageId") String marriageId, @PathVariable("dateOfBirth")Date birthDate){
        controller.addBirthByParentsMarriage(you,marriageId,birthDate);
        return "Success";
    }

    @RequestMapping(value = "/person/{personId}/parents/{fatherId}/{motherId}", method = RequestMethod.PATCH)
    @ApiOperation(value = "Relating You with your Appa & Amma", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully cleaned all data"),
    })
    public String addParents(@PathVariable("fatherId") String you,@PathVariable("fatherId") String appa,@PathVariable("motherId") String amma){
        //controller.relateParents(you,appa,amma);
        return "Success";
    }
}
