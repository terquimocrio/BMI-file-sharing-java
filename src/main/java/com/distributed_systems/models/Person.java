/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.distributed_systems.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author diego
 */
@XmlRootElement
public class Person {
    private String name;
    private float height;
    private float weight;
    private float bmi;
    private String result;

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @XmlElement
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    @XmlElement
    public float getBmi() {
        return bmi;
    }

    @XmlElement
    public String getResult() {
        return result;
    }

    public void setBMI() {
        if (height != 0) {
            this.bmi = weight / (height * height);
            if (this.bmi < 18.5f) {
                this.result = "Thin";
            } else if (this.bmi < 24.9f) {
                this.result = "Healthy";
            } else if (this.bmi < 29.9f) {
                this.result = "Overweight";
            } else {
                this.result = "Obese";
            }
        } else {
            this.result = "Invalid height";
        }
    }

    public String getBMIMeaning() {
        return this.result;
    }
}