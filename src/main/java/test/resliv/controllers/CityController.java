package test.resliv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.resliv.entity.CityEntity;
import test.resliv.exeption.CityNotFoundException;
import test.resliv.exeption.CityNotRenameException;
import test.resliv.exeption.NoAnyCityException;
import test.resliv.exeption.СityAlreadyExistException;
import test.resliv.service.CityService;

import java.util.ArrayList;


@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    public ResponseEntity cityAdd(@RequestBody CityEntity cityEntity) {
        try {
            return ResponseEntity.ok(cityService.cityAdd(cityEntity));
        } catch (СityAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }

    @GetMapping("/get")
    public ResponseEntity getAllCities() {
        try {
            return ResponseEntity.ok(cityService.getAllCities());
        } catch (NoAnyCityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity cityDelete(@RequestBody CityEntity cityEntity) {
        try {
            cityService.cityDelete(cityEntity);
            return ResponseEntity.ok("City delete");
        } catch (CityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }

    @PutMapping("/update")
    public ResponseEntity cityUpdate(@RequestBody ArrayList <CityEntity> cityEntityArrayList) {
        try {
            return ResponseEntity.ok(cityService.cityUpdate(cityEntityArrayList));
        } catch (CityNotRenameException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR");
        }
    }


    @GetMapping("/")
    public ResponseEntity getCities() {
        try {
            return ResponseEntity.ok("Server work");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server ERROR");
        }
    }

}