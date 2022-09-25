package com.rathix.manager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Instance {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;
    private String iconPath;
    private String category = "default";
}
