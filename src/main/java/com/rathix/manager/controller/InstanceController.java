package com.rathix.manager.controller;

import com.rathix.manager.exception.AlreadyExistsException;
import com.rathix.manager.model.Instance;
import com.rathix.manager.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/instance")
public class InstanceController {
    @Autowired
    private InstanceService instanceService;

    @PostMapping
    public ResponseEntity<Object> createInstance(@RequestBody Instance instance) {
        try {
            return new ResponseEntity<>(instanceService.createInstance(instance), HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> retrieveInstances() {
        return new ResponseEntity<>(instanceService.retrieveInstances(), HttpStatus.FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> retrieveInstance(@PathVariable("id") Long id) {
        return new ResponseEntity<>(instanceService.retrieveInstance(id), HttpStatus.FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateInstance(@PathVariable("id") Long id, @RequestBody Instance instance) {
        try {
            return new ResponseEntity<>(instanceService.updateInstance(id, instance), HttpStatus.OK);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteInstance(@PathVariable("id") Long id) {
        instanceService.deleteInstance(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
