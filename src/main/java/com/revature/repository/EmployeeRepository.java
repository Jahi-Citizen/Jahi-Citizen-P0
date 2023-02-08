package com.revature.repository;
import com.revature.util.*;

import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.revature.model.Employee;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class EmployeeRepository {
    
    public void Save(Employee employee)
    {
        // //Actual implementation here
        // ObjectMapper mapper = new ObjectMapper();
        // String jsonObject = "";

        // try {
            
        //     //Converted the pokemon obj into json
        //    jsonObject = mapper.writeValueAsString(employee);

        //    //Save the json into a file
        //    //File constructor needs a string that holds the path of where you want to save the file
        //    File employeeFile = new File("./src/main/java/com/revature/repository/employee.json");
        //    employeeFile.createNewFile();

        //    //Writing the file
        //     FileWriter writer = new FileWriter("./src/main/java/com/revature/repository/employee.json", true);
        //     writer.write(jsonObject + "\n"); //Writes the string into the file
        //     writer.close(); //Closes the necessary resources associated with a filewriter object

        // } catch (JsonGenerationException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (JsonMappingException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }


 //NEW WAY TO SAVE TO DATABASE INSTEAD
        String sql = "insert into employee ( employeeName, email, userName, pass) values ( ?, ?, ?, ?)";

        try (Connection con = ConnectionUtil.getConnection()) {

            PreparedStatement prstmt = con.prepareStatement(sql);

            //We are replacing the '?' into actual values from the pokemon we received
            //Sadly, it uses one-based indexing so 1 is the very first parameter
            
            prstmt.setString(1, employee.getName());
            prstmt.setString(2, employee.getEmail());
            prstmt.setString(3, employee.getUserName());
            prstmt.setString(4, employee.getPassword());



            //execute() method does not expect to return anything from the statement
            //executeQuery() method does expect something to result after executing the statement

            prstmt.execute();


        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

       
        
    }

    public HashSet<String> getRegisteredEmployee()
    {
        HashSet<String> EmployeesRegistered = new HashSet<String>();
        String sql = "SELECT * FROM employee";

        try (Connection con = ConnectionUtil.getConnection()) {
            Statement state = con.createStatement();
            ResultSet FS = state.executeQuery(sql);

        while(FS.next()){
            // EmployeesRegistered.add(FS.getString(1));
            EmployeesRegistered.add(FS.getString(3));
            EmployeesRegistered.add(FS.getString(5));
            
        }

    }catch (SQLException e){
        e.printStackTrace();
    }

    return EmployeesRegistered;

    }

    public String getPassword(Employee employee)
    {
        String password = "";
        String sql = " select pass from employee where email = ?";
        

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, employee.getEmail());
            ResultSet FS = prstmt.executeQuery();
            FS.next();
            password = FS.getString(1);
            
            
    
    }catch (SQLException e){
        e.printStackTrace();
    }

    return password;

    }

    public String getRole(Employee employee)
    {
        String role = "";
        String sql = " select employeeRole from employee where email = ?";
        

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, employee.getEmail());
            ResultSet FS = prstmt.executeQuery();
            FS.next();
            role = FS.getString(1);
           
            
    
    }catch (SQLException e){
        e.printStackTrace();
    }

    return role;

    }

    public String getEmail(Employee employee)
    {
        String email = "";
        String sql = " select email from employee where pass = ?";
        

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, employee.getPassword());
            ResultSet FS = prstmt.executeQuery();
            FS.next();
            email = FS.getString(1);
           
            
    
    }catch (SQLException e){
        e.printStackTrace();
    }

    return email;

    }

    
}

 
        
    // }


            

 
        
        

    

