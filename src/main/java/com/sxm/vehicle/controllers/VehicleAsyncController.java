package com.sxm.vehicle.controllers;

import com.sxm.vehicle.models.Vehicle;
import com.sxm.vehicle.services.VehicleAsyncService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.ExecutionException;

/**
 * VehicleAsyncController is the controller containing methods to create vehicles
 *
 * @author Lakshay Mukhi
 *
 */
@RestController
public class VehicleAsyncController {


    @Autowired
    VehicleAsyncService vehicleService;

    Logger logger = LoggerFactory.getLogger(VehicleAsyncController.class);


    /**
     * <p> This method will take details of vehicle and save it to the database.
     * </p>
     * @param vehicle the details of the vehicle coming in from the request
     * @return the json object only containing the vehicle id with 200 status code
     */
    @RequestMapping(value = "/vehicle-api/v1/vehicles/vehicle", consumes = "application/json",method = RequestMethod.POST)
    public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle) throws InterruptedException, ExecutionException {

        logger.info("Request Body, {}", vehicle.toString());

        if (!(vehicle.getTransmissionType().equalsIgnoreCase("MANUAL")) && !(vehicle.getTransmissionType().
                equalsIgnoreCase("AUTO"))) {

            logger.error("BAD REQUEST, {}", new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Vehicle transmission type must accept only \"MANUAL\" or \"AUTO\" in the request body."));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Vehicle transmission type must accept only \"MANUAL\" or \"AUTO\" in the request body.");

        }else if( vehicle.getVin() == null ||
                    vehicle.getMake() == null ||
                    vehicle.getModel() == null ||
                    vehicle.getYear() == 0 ||
                    vehicle.getTransmissionType() == null) {


            logger.error("Internal Server Error, {}", new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went Wrong! Please check. Request fields cannot be null."));


            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went Wrong! Please check. Request fields cannot be null.");

        } else{
            JSONObject response = new JSONObject();
            response.put("vin",vehicleService.createVehicle(vehicle).get());

            logger.info("createVehicle() response body in controller, {}",
                    new ResponseEntity<>(response.toString(), HttpStatus.OK));

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
    }
}
