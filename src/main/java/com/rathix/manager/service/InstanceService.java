package com.rathix.manager.service;

import com.rathix.manager.exception.AlreadyExistsException;
import com.rathix.manager.exception.ObjectNotFoundException;
import com.rathix.manager.model.Instance;
import com.rathix.manager.repository.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Instance> instanceList = instanceRepository.findAll();
        for (Instance instance : instanceList) {
            instance.testReachable();
        }
        return instanceList;
    }

    public Instance retrieveInstance(Long id) throws ObjectNotFoundException {
        return instanceRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }

    public Instance updateInstance(Long id, Instance instance) throws AlreadyExistsException, ObjectNotFoundException {
        Instance oldInstance = instanceRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        if (!(oldInstance.getName().equals(instance.getName()))) {
            if (instanceRepository.existsInstanceByName(instance.getName())) {
                throw new AlreadyExistsException("An instance with this name already exists.");
            }
        }
        oldInstance.setName(instance.getName());
        oldInstance.setUrl(instance.getUrl());
        oldInstance.setIconPath(instance.getIconPath());
        oldInstance.setCategory(instance.getCategory());
        return instanceRepository.save(oldInstance);
    }

    public boolean deleteInstance(Long id) throws ObjectNotFoundException {
        if (instanceRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException();
        }
        instanceRepository.deleteById(id);
        return true;
    }
}
