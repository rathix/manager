package com.rathix.manager.service;

import com.rathix.manager.exception.AlreadyExistsException;
import com.rathix.manager.exception.ObjectNotFoundException;
import com.rathix.manager.model.Instance;
import com.rathix.manager.repository.InstanceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the InstanceService class
 */
@ExtendWith(MockitoExtension.class)
class InstanceServiceTest {
    @Mock
    private InstanceRepository instanceRepository;
    @Autowired
    @InjectMocks
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
     * Saving an instance should work
     */
    @Test
    void createInstance() {
        when(instanceRepository.save(any(Instance.class))).thenReturn(instance);
        Instance savedInstance = instanceRepository.save(instance);
        assertEquals(savedInstance.getName(), "testInstance");
    }

    /**
     * Saving a duplicate instance should throw an AlreadyExistsException
     */
    @Test
    void createDuplicateInstance() {
        when(instanceRepository.existsInstanceByName(any(String.class))).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> instanceService.createInstance(instance));
    }

    /**
     * Retrieving all instances should work
     */
    @Test
    void retrieveInstances() {
        when(instanceRepository.findAll()).thenReturn(instanceList);
        List<Instance> returnList = instanceService.retrieveInstances();
        assertEquals(returnList.get(0).getName(), "testInstance");
    }

    /**
     * Retrieving an instance should work
     */
    @Test
    void retrieveInstance() throws ObjectNotFoundException {
        when(instanceRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(instance));
        Instance retrievedInstance = instanceService.retrieveInstance(0L);
        assertEquals(retrievedInstance.getName(), "testInstance");
    }

    /**
     * Retrieving an instance which doesn't exist should throw a ObjectNotFoundException
     */
    @Test
    void retrieveNonExistentInstance() {
        when(instanceRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> instanceService.retrieveInstance(0L));
    }

    /**
     * Updating an instance should work
     */
    @Test
    void updateInstance() throws AlreadyExistsException, ObjectNotFoundException {
        when(instanceRepository.findById(any(Long.class))).thenReturn(Optional.of(instance));

        Instance newInstance = new Instance();
        newInstance.setUrl("newTestUrl");
        newInstance.setName("newTestName");

        when(instanceRepository.save(any(Instance.class))).thenReturn(newInstance);
        Instance savedNewInstance = instanceService.updateInstance(0L, newInstance);

        assertEquals(savedNewInstance.getName(), "newTestName");
    }

    /**
     * Updating an instance which doesn't exist should throw a ObjectNotFoundException
     */
    @Test
    void updateNonExistentInstance() {
        when(instanceRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> instanceService.updateInstance(0L, instance));
    }

    /**
     * Updating an instance with a name or url which already exists should throw an AlreadyExistsException
     */
    @Test
    void updateDuplicateInstance() {
        when(instanceRepository.existsInstanceByName(any(String.class))).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> instanceService.updateInstance(0L, instance));
    }

    /**
     * Deleting an instance should work
     */
    @Test
    void deleteInstance() throws ObjectNotFoundException {
        when(instanceRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(instance));
        assertTrue(instanceService.deleteInstance(0L));
    }

    /**
     * Deleting an instance which does not exist should throw ObjectNotFoundException
     */
    @Test
    void deleteNonExistentInstance() throws ObjectNotFoundException {
        when(instanceRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> instanceService.deleteInstance(0L));
    }
}