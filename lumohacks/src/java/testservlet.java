/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Facility;
import models.Patient;

/**
 *
 * @author tony
 */
@WebServlet(urlPatterns = {"/lumohacks"})
public class testservlet extends HttpServlet {

    protected void processFetchAllPatients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String username = "adminP1f8xDA";
        String pw = "m9uw6PHVRkvU";
        String conn = "jdbc:mysql://127.12.25.130:3306/lumohacks?zeroDateTimeBehavior=convertToNull";
        Connection con = DriverManager.getConnection(conn, username, pw);
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement("select * from patients");
        ResultSet rs = ps.executeQuery();
        String result = "";
        ArrayList<Patient> arr = new ArrayList<>();
        while(rs.next()){
            Patient pat = new Patient(rs.getString("id"), rs.getString("name"), rs.getInt("age"), rs.getString("city"), rs.getString("disease"), rs.getString("email"), rs.getString("gender"), rs.getString("c_name"), rs.getString("c_year"), rs.getString("c_descript"));
            arr.add(pat);
        }
        result = new Gson().toJson(arr);
        
        response.getWriter().print(result);
        con.close();
        }
        catch(Exception e){
            response.getWriter().print(e);
        }
    }
    
    
    
    protected void processFetchAllFacilities(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        try{
            
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String username = "adminP1f8xDA";
        String pw = "m9uw6PHVRkvU";
        String conn = "jdbc:mysql://127.12.25.130:3306/lumohacks?zeroDateTimeBehavior=convertToNull";
        Connection con = DriverManager.getConnection(conn, username, pw);
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement("select * from facilities");
        ResultSet rs = ps.executeQuery();
        String result = "";
        ArrayList<Facility> arr = new ArrayList<>();
        while(rs.next()){
            Facility fac = new Facility(rs.getString("id"), rs.getString("name"), rs.getInt("numpatients"), rs.getString("city"), rs.getString("email"), rs.getString("institute"), rs.getString("r_name"), rs.getBoolean("r_trial"), rs.getString("r_descript"));
            arr.add(fac);
        }
        result = new Gson().toJson(arr);
        
        response.getWriter().print(result);
        con.close();
        }
        catch(Exception e){
            response.getWriter().print(e);
        }
    }
    
    protected void processAddPatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        try{
            
            String name = request.getParameter("name");
            Integer age = Integer.parseInt(request.getParameter("age"));
            String city = request.getParameter("city");
            String disease = request.getParameter("condition");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String c_name = request.getParameter("c_name");
            String c_year = request.getParameter("c_year");
            String c_descript = request.getParameter("c_descript");
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String username = "adminP1f8xDA";
        String pw = "m9uw6PHVRkvU";
        String conn = "jdbc:mysql://127.12.25.130:3306/lumohacks?zeroDateTimeBehavior=convertToNull";
        Connection con = DriverManager.getConnection(conn, username, pw);
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement("insert into patients(name,age,city,disease,email,gender,c_name,c_year,c_descript) values(?,?,?,?,?,?,?,?,?)");
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, city);
        ps.setString(4, disease);
        ps.setString(5, email);
        ps.setString(6, gender);
        ps.setString(7, c_name);
        ps.setString(8, c_year);
        ps.setString(9, c_descript);
        
        ps.executeUpdate();
        
        con.commit();
        
        
        PreparedStatement ps1 = con.prepareStatement("select * from facilities");
        ResultSet rs1 = ps1.executeQuery();
        String result = "";
        ArrayList<Facility> arr = new ArrayList<>();
        while(rs1.next()){
            Facility fac = new Facility(rs1.getString("id"), rs1.getString("name"), rs1.getInt("numpatients"), rs1.getString("city"), rs1.getString("email"), rs1.getString("institute"), rs1.getString("r_name"), rs1.getBoolean("r_trial"), rs1.getString("r_descript"));
            arr.add(fac);
        }
        //filter out only relevant facilities
        ArrayList<Facility> filtered = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            if(((Facility)arr.get(i)).City.toLowerCase().equals(city.toLowerCase()) || ((Facility)arr.get(i)).R_Name.contains(c_name) || c_name.contains(((Facility)arr.get(i)).R_Name) || ((Facility)arr.get(i)).R_Descript.contains(c_descript) || c_descript.contains(((Facility)arr.get(i)).R_Descript)){
                filtered.add(arr.get(i));
            }
        }
        result = new Gson().toJson(filtered);
        
        response.getWriter().print(result);
        
        con.close();
        }
        catch(Exception e){
            response.getWriter().print(e);
        }
    }
    
    protected void processAddFacility(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        try{
            
            String name = request.getParameter("name");
            Integer numpatients = 0;
            String city = request.getParameter("city");
            String email = request.getParameter("email");
            String institute = request.getParameter("institute");
            String r_name = request.getParameter("r_name");
            Boolean r_trial = Boolean.parseBoolean(request.getParameter("r_trial"));
            String r_descript = request.getParameter("r_descript");
            
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String username = "adminP1f8xDA";
        String pw = "m9uw6PHVRkvU";
        String conn = "jdbc:mysql://127.12.25.130:3306/lumohacks?zeroDateTimeBehavior=convertToNull";
        Connection con = DriverManager.getConnection(conn, username, pw);
        con.setAutoCommit(false);

        PreparedStatement ps = con.prepareStatement("insert into facilities(name,numpatients,city,email,institute,r_name,r_trial,r_descript) values(?,?,?,?,?,?,?,?)");
        ps.setString(1, name);
        ps.setInt(2, numpatients);
        ps.setString(3, city);
        ps.setString(4, email);
        ps.setString(5, institute);
        ps.setString(6, r_name);
        ps.setBoolean(7, r_trial);
        ps.setString(8, r_descript);
        
        ps.executeUpdate();
        
        con.commit();
        con.close();
        }
        catch(Exception e){
            response.getWriter().print(e);
        }
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String action = request.getParameter("action");
        switch(action){
            case "fetchallpatients":
                processFetchAllPatients(request, response);
                break;
            case "fetchallfacilities":
                processFetchAllFacilities(request, response);
                break;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(testservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch(action){
            case "addpatient":
        {
                try {
                    processAddPatient(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(testservlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
            case "addfacility":
        {
                try {
                    processAddFacility(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(testservlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
                
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
