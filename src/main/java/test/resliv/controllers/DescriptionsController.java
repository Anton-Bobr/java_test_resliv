package test.resliv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.resliv.entity.DescriptionsEntity;
import test.resliv.exeption.*;
import test.resliv.service.DescriptionsService;
import java.util.List;

@RestController
@RequestMapping("/description")
public class DescriptionsController {

    @Autowired
    private DescriptionsService descriptionsService;

    @PostMapping("/add")
    public ResponseEntity createDescription (@RequestBody DescriptionsEntity descriptionsEntity,
                                             @RequestParam String city) {
        try {
            return ResponseEntity.ok(descriptionsService.descriptionCityAdd(descriptionsEntity, city));
        } catch (CityNotFoundException | DescriptionAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }

    @GetMapping("/get")
    public ResponseEntity getCityDescriptions(@RequestParam String city) {
        try {
            return ResponseEntity.ok(descriptionsService.getCityDescriptions(city));
        } catch (CityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity descriptionDelete(@RequestBody DescriptionsEntity descriptionsEntity,
                                            @RequestParam String city) {
        try {
            descriptionsService.descriptionDelete(descriptionsEntity, city);
            return ResponseEntity.ok("Description delete");
        } catch (CityNotFoundException | DescriptionNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }

    @PutMapping("/update")
    public ResponseEntity descriptionUpdate(@RequestBody List<DescriptionsEntity> descriptionsEntityList,
                                            @RequestParam String city) {
        try {
            return ResponseEntity.ok(descriptionsService.descriptionUpdate(descriptionsEntityList, city));
        } catch (CityNotFoundException | DescriptoinNotRenameException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }


}
