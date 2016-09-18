/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author tony
 */
public class Patient {
    
    public String Id;
    public String Name;
    public Integer Age;
    public String City;
    public String Disease;
    
    public Patient(String id, String name, Integer age, String city, String disease){
        this.Id = id;
        this.Name = name;
        this.Age = age;
        this.City = city;
        this.Disease = disease;
    }
}
