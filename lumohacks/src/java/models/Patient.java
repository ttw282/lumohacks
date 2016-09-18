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
    public String Email;
    public String Gender;
    public String C_Name;
    public String C_Year;
    public String C_Descript;
    
    public Patient(String id, String name, Integer age, String city, String disease, String email, String gender, String c_name, String c_year, String c_descript){
        this.Id = id;
        this.Name = name;
        this.Age = age;
        this.City = city;
        this.Disease = disease;
        this.Email = email;
        this.Gender = gender;
        this.C_Name = c_name;
        this.C_Year = c_year;
        this.C_Descript = c_descript;
    }
}
