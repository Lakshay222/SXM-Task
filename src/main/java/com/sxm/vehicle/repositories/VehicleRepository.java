package com.sxm.vehicle.repositories;


import com.sxm.vehicle.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * VehicleRepository is the repository interface containing methods to interact with DB
 *
 * Please see the {@link org.springframework.data.jpa.repository.JpaRepository} class for true identity
 * @author Lakshay Mukhi
 *
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
