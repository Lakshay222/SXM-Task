package com.sxm.vehicle.services;

import com.sxm.vehicle.models.Vehicle;
import com.sxm.vehicle.repositories.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * VehicleAsyncService is the service responsible for performing db operations
 *
 * @author Lakshay Mukhi
 *
 */
@Service
public class VehicleAsyncService {

    @Autowired
    VehicleRepository vehicleRepository;

    Logger logger = LoggerFactory.getLogger(VehicleAsyncService.class);


    /**
     * <p> This method will take details of vehicle and save it to the database.
     * </p>
     * @param vehicle the details of the vehicle coming in from the request
     * @return the vehicle id
     */
    @Async("asyncExecutor")
    public CompletableFuture<String> createVehicle(Vehicle vehicle) throws InterruptedException, ExecutionException {

        logger.info("Request parameter in service method, {}", vehicle.toString());

        vehicleRepository.save(vehicle);
        Thread.sleep(1000L);

        logger.info("Vehicle ID as response, {}", CompletableFuture.completedFuture(vehicle.getVin()).get());

        return CompletableFuture.completedFuture(vehicle.getVin());
    }

}
