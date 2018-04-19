package aara.uravu.seyalagam.service.resource;

import aara.uravu.seyalagam.persistance.PersistanceController;
import aara.uravu.seyalagam.service.pojo.Marriage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarriageResource {

    private final PersistanceController controller;

    public MarriageResource(PersistanceController controller) {
        this.controller = controller;
    }

    @RequestMapping(value = "/marriage", method = RequestMethod.POST)
    @ApiOperation(value = "Marking a Marriage event", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Creates a Marriage Event"),
    })
    public String createMarriage(@RequestBody Marriage marriage) {
        if (marriage.validate()) {
            return controller.createMarriageEvent(marriage);
        } else
            return "-1";
    }
}
