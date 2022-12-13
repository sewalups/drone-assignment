package com.sewalusteven.droneproject.domains.drones.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sewalusteven.droneproject.DroneprojectApplication;
import com.sewalusteven.droneproject.domains.drones.payloads.DronePayloads;
import com.sewalusteven.droneproject.domains.drones.types.DroneModel;
import com.sewalusteven.droneproject.domains.drones.types.DroneState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DroneprojectApplication.class)
@AutoConfigureMockMvc
class DroneResourceTest {
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_can_register_drone() throws Exception {
        var droneRequest =  new DronePayloads.Request(
                "ranSerialNum",
                DroneModel.Cruiserweight,
                300,
                23,
                DroneState.IDLE
        );

        var request = MockMvcRequestBuilders
                .post("/api/v1/drone")
                .content(asJsonString(droneRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

         mvc.perform(request)
                 .andExpect(status().isOk())
                 .andExpect(content().string(containsString(droneRequest.serialNumber())))
                 .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void itCanLoadDrone() {
    }

    @Test
    void itCanCheckLoadForDrone() {
    }

    @Test
    void itCanGetFreeDrones() {
    }

    @Test
    void itCantGetDroneBattery() {
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}