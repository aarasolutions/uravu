package aara.uravu.seyalagam.service.resource;

import aara.uravu.seyalagam.persistance.PersistanceController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterdataResource {

    private final PersistanceController controller;

    public MasterdataResource(PersistanceController controller) {
        this.controller = controller;
    }



    @RequestMapping(value = "/masterdata/caste", method = RequestMethod.GET)
    @ApiOperation(value = "Upload MasterDAta to database", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Creates a Person"),
    })
    public void createMasterData(){
        controller.createMasterDataCaste();
    }
}
