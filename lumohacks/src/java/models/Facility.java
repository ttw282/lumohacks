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
public class Facility {
    
    public String Id;
    public String Name;
    public Integer NumPatients;
    public String City;
    
    public Facility(String id, String name, Integer numpatients, String city){
        this.Id = id;
        this.Name = name;
        this.NumPatients = numpatients;
        this.City = city;
    }
}
