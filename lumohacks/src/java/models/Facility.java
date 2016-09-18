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
    public String Email;
    public String Institute;
    public String R_Name;
    public Boolean R_Trial;
    public String R_Descript;
    
    public Facility(String id, String name, Integer numpatients, String city, String email, String institute, String r_name, Boolean r_trial, String r_descript){
        this.Id = id;
        this.Name = name;
        this.NumPatients = numpatients;
        this.City = city;
        this.Email = email;
        this.Institute = institute;
        this.R_Name = r_name;
        this.R_Trial = r_trial;
        this.R_Descript = r_descript;
    }
}
