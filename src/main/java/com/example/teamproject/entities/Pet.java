package com.example.teamproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Pet {
    /**
     * Уникальный ID для хранения класса в БД и использования экземпляра в программе
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Владелец питомца
     */
    @ManyToOne
    @JsonIgnore
    private AdoptiveParent adoptiveParent;

    /**
     * Тип питомца
     */
    private String type;

    /**
     * Имя питомца
     */
    private String name;

    /**
     * Возраст питомца
     */
    private int age;

    /**
     * Описание питомца
     */
    private String description;
    /**
     * Дата окончания испытательного срока
     */
    private LocalDate trialPeriod;

    public LocalDate getTrialPeriod() {
        return trialPeriod;
    }

    public void setTrialPeriod(LocalDate trialPeriod) {
        this.trialPeriod = trialPeriod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdoptiveParent getAdoptiveParent() {
        return adoptiveParent;
    }

    public void setAdoptiveParent(AdoptiveParent adoptiveParent) {
        this.adoptiveParent = adoptiveParent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && Objects.equals(id, pet.id) && Objects.equals(adoptiveParent, pet.adoptiveParent) && Objects.equals(type, pet.type) && Objects.equals(name, pet.name) && Objects.equals(description, pet.description) && Objects.equals(trialPeriod, pet.trialPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adoptiveParent, type, name, age, description, trialPeriod);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", adoptiveParent=" + adoptiveParent +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", trialPeriod=" + trialPeriod +
                '}';
    }
}
