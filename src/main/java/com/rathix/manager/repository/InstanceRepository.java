package com.rathix.manager.repository;

import com.rathix.manager.model.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {
    boolean existsInstanceByName(String name);
}
