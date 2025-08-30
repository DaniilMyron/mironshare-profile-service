package com.miron.profileservice.infrastructure.repo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subscribers")
public class Subscribers {
    @Id
    @GeneratedValue
    private long id;
}