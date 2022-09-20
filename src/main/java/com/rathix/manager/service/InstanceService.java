package com.rathix.manager.service;

import com.rathix.manager.exception.AlreadyExistsException;
import com.rathix.manager.exception.ObjectNotFoundException;
import com.rathix.manager.model.Instance;
import com.rathix.manager.repository.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InstanceService {
    @Autowired
    private InstanceRepository instanceRepository;

    public Instance createInstance(Instance instance) throws AlreadyExistsException {
        if (instanceRepository.existsInstanceByName(instance.getName())) {
            throw new AlreadyExistsException("An instance with this name already exists.");
        }
        return instanceRepository.save(instance);
    }

    public List<Instance> retrieveInstances() {
        return instanceRepository.findAll();
    }

    public Instance retrieveInstance(Long id) throws ObjectNotFoundException {
        return instanceRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }

    public Instance updateInstance(Long id, Instance instance) throws AlreadyExistsException, ObjectNotFoundException {
        if (instanceRepository.existsInstanceByName(instance.getName())) {
            throw new AlreadyExistsException("An instance with this name already exists.");
        } else {
            Instance oldInstance = instanceRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
            oldInstance.setName(instance.getName());
            oldInstance.setUrl(instance.getUrl());
            return instanceRepository.save(oldInstance);
        }
    }

    public boolean deleteInstance(Long id) {
        instanceRepository.deleteById(id);
        return true;
    }
}
