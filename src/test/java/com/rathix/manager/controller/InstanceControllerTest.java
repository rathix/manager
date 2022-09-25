package com.rathix.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rathix.manager.exception.AlreadyExistsException;
import com.rathix.manager.exception.ObjectNotFoundException;
import com.rathix.manager.model.Instance;
import com.rathix.manager.service.InstanceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for the controller
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(InstanceController.class)
class InstanceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private InstanceService instanceService;

    private Instance instance;
    private List<Instance> instanceList;

    @BeforeEach
    void setUp() {
        instance = new Instance();
        instance.setName("testInstance");
        instance.setUrl("testUrl");
        instance.setId(0L);

        instanceList = new ArrayList<>();
        instanceList.add(instance);
    }

    @AfterEach
    void tearDown() {
        instance = null;
        instanceList = null;
    }

    /**
     * Creating an instance should return 201
     */
    @Test
    void createInstance() throws Exception {
        when(instanceService.createInstance(any(Instance.class))).thenReturn(instance);

        mockMvc.perform(post("/api/v1/instance")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(instance)))
                .andExpect(status().isCreated());
    }

    /**
     * Creating a duplicate instance should return 400
     */
    @Test
    void createDuplicateInstance() throws Exception {
        when(instanceService.createInstance(any(Instance.class))).thenThrow(new AlreadyExistsException());

        mockMvc.perform(post("/api/v1/instance")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(instance)))
                .andExpect(status().isBadRequest());

    }

    /**
     * Retrieving all instances should return 302
     */
    @Test
    void retrieveInstances() throws Exception {
        when(instanceService.retrieveInstances()).thenReturn(instanceList);

        mockMvc.perform(get("/api/v1/instance"))
                .andExpect(status().isOk());
    }

    /**
     * Retrieving an instance should return 302
     */
    @Test
    void retrieveInstance() throws Exception {
        when(instanceService.retrieveInstance(any(Long.class))).thenReturn(instance);

        mockMvc.perform(get("/api/v1/instance/{id}", instance.getId()))
                .andExpect(status().isOk());
    }

    /**
     * Retrieving an instance which doesn't exist should return 404
     */
    @Test
    void retrieveInstanceNotFound() throws Exception {
        when(instanceService.retrieveInstance(any(Long.class))).thenThrow(new ObjectNotFoundException());

        mockMvc.perform(get("/api/v1/instance/{id}", instance.getId()))
                .andExpect(status().isNotFound());
    }

    /**
     * Updating an instance should return 200
     */
    @Test
    void updateInstance() throws Exception {
        when(instanceService.updateInstance(any(Long.class), any(Instance.class))).thenReturn(instance);

        mockMvc.perform(put("/api/v1/instance/{id}", instance.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(instance)))
                .andExpect(status().isOk());
    }

    /**
     * Updating an instance should return 304
     */
    @Test
    void updateDuplicateInstance() throws Exception {
        when(instanceService.updateInstance(any(Long.class), any(Instance.class))).thenThrow(new AlreadyExistsException());

        mockMvc.perform(put("/api/v1/instance/{id}", instance.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(instance)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Updating an instance which doesn't exist should return 404
     */
    @Test
    void updateInstanceNotFound() throws Exception {
        when(instanceService.updateInstance(any(Long.class), any(Instance.class))).thenThrow(new ObjectNotFoundException());

        mockMvc.perform(put("/api/v1/instance/{id}", instance.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(instance)))
                .andExpect(status().isNotFound());
    }

    /**
     * Deleting an instance should return 200
     */
    @Test
    void deleteInstance() throws Exception {
        mockMvc.perform(delete("/api/v1/instance/{id}", instance.getId()))
                .andExpect(status().isOk());
    }
}