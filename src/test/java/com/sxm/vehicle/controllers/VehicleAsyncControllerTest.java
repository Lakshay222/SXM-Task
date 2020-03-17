package com.sxm.vehicle.controllers;

import com.sxm.vehicle.VehicleApplicationTests;
import com.sxm.vehicle.models.Vehicle;
import com.sxm.vehicle.services.VehicleAsyncService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class VehicleAsyncControllerTest extends VehicleApplicationTests {

    @Autowired
    WebApplicationContext applicationContext;

    MockMvc mockMvc;

    @MockBean
    VehicleAsyncService vehicleService;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void vehicleTestStatusSuccess() throws Exception {
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999",2019,"FCA","RAM","AUTO");
        String mockData = "{\"vin\":\"1A4AABBC5KD501999\",\"year\": 2019,\"make\": \"FCA\",\"model\": \"RAM\", \"transmissionType\": \"MANUAL\"}";

        when(vehicleService.createVehicle(Mockito.any(Vehicle.class))).thenReturn(CompletableFuture.completedFuture(vehicle.getVin()));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicle-api/v1/vehicles/vehicle")
                .contentType("application/json")
                .content(mockData);


        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    public void vehicleTestStatusBadRequest() throws Exception {
        Vehicle vehicle = new Vehicle("1A4AABBC5KD501999",2019,"FCA","RAM","MAN");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicle-api/v1/vehicles/vehicle")
                .contentType("application/json")
                .content(vehicle.toString());


        this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
                .andDo(print());
    }


    @Test
    public void vehicleTestStatusInternalServerError() throws Exception {
        String mockData = "{\"vin\": null,\"year\": 2019,\"make\": \"FCA\",\"model\": \"RAM\", \"transmissionType\": \"MANUAL\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicle-api/v1/vehicles/vehicle")
                .contentType("application/json")
                .content(mockData);

        this.mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError())
                .andDo(print());

    }
}