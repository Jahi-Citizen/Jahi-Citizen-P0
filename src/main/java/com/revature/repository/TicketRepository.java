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
import com.revature.model.Ticket;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TicketRepository {
    
        public void Save(Ticket ticket)
        {
            String sql = "insert into ticket ( employeename, description, amounts, status) values ( ?, ?, ?, ?)";

        try (Connection con = ConnectionUtil.getConnection()) {

            PreparedStatement prstmt = con.prepareStatement(sql);

            //We are replacing the '?' into actual values from the pokemon we received
            //Sadly, it uses one-based indexing so 1 is the very first parameter
            
            prstmt.setString(1, ticket.getName());
            prstmt.setString(2, ticket.getDescription());
            prstmt.setInt(3, ticket.getAmount());
            prstmt.setString(4, ticket.getStatus());



            //execute() method does not expect to return anything from the statement
            //executeQuery() method does expect something to result after executing the statement

            prstmt.execute();


        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
     
    }

    public String getPassword(Ticket ticket)
    {
        String password = "";
        String sql = " select pass from employee where email = ?";
        

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, ticket.getEmail());
            ResultSet FS = prstmt.executeQuery();
            FS.next();
            password = FS.getString(1);
            
            
    
    }catch (SQLException e){
        e.printStackTrace();
    }

    return password;

    }

    public String getEmail(Ticket ticket)
    {
        String email = "";
        String sql = " select email from employee where pass = ?";
        

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, ticket.getPassword());
            ResultSet FS = prstmt.executeQuery();
            FS.next();
            email = FS.getString(1);
           
            
    
    }catch (SQLException e){
        e.printStackTrace();
    }

    return email;

    }

    public String getRole(Ticket ticket)
    {
        String role = "";
        String sql = " select employeeRole from employee where email = ?";
        

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, ticket.getEmail());
            ResultSet FS = prstmt.executeQuery();
            FS.next();
            role = FS.getString(1);
           
            
    
    }catch (SQLException e){
        e.printStackTrace();
    }

    return role;

    }

    public ArrayList<Ticket> getPendingTickets()
    {
        ArrayList<Ticket> pendingTickets = new ArrayList<Ticket>();
        String sql = "select * from ticket where status = 'Pending'";

        try (Connection con = ConnectionUtil.getConnection())
        {
            Statement state = con.createStatement();
            ResultSet FS = state.executeQuery(sql);

            while(FS.next()){
                Ticket newticket = new Ticket();
                newticket.setAmount(FS.getInt("amounts"));
                newticket.setDescription(FS.getString("description"));
                newticket.setName(FS.getString("employeename"));
                newticket.setTicketID(FS.getInt("ticketid"));
                newticket.setStatus(FS.getString("status"));

                pendingTickets.add(newticket);

            }}catch (SQLException e){
                e.printStackTrace();
            }

        return pendingTickets;
    }

    public ArrayList<Ticket> getAllEmployeeTickets(Ticket ticket)
    {
        ArrayList<Ticket> employeeTickets = new ArrayList<Ticket>();
        String sql = "select * from ticket where employeename = ?";

        try (Connection con = ConnectionUtil.getConnection())
        {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, ticket.getName());
            ResultSet FS = prstmt.executeQuery();

            while(FS.next()){
                Ticket newticket = new Ticket();
                newticket.setAmount(FS.getInt("amounts"));
                newticket.setDescription(FS.getString("description"));
                newticket.setName(FS.getString("employeename"));
                newticket.setTicketID(FS.getInt("ticketid"));
                newticket.setStatus(FS.getString("status"));

                employeeTickets.add(newticket);

            }}catch (SQLException e){
                e.printStackTrace();
            }

        return employeeTickets;
    }

    public ArrayList<Ticket> getAllEmployeeTicketsFiltered(Ticket ticket)
    {
        ArrayList<Ticket> employeeTickets = new ArrayList<Ticket>();
        String sql = "select * from ticket where employeename = ? and status = ?";

        try (Connection con = ConnectionUtil.getConnection())
        {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, ticket.getName());
            prstmt.setString(2, ticket.getStatus());
            ResultSet FS = prstmt.executeQuery();

            while(FS.next()){
                Ticket newticket = new Ticket();
                newticket.setAmount(FS.getInt("amounts"));
                newticket.setDescription(FS.getString("description"));
                newticket.setName(FS.getString("employeename"));
                newticket.setTicketID(FS.getInt("ticketid"));
                newticket.setStatus(FS.getString("status"));

                employeeTickets.add(newticket);

            }}catch (SQLException e){
                e.printStackTrace();
            }

        return employeeTickets;
    }



    public int changeTicketStatus(Ticket ticket) 
    {
        String status; 
        String sql = "update ticket set status = ? where ticketid = ? and status = 'Pending'";

        try (Connection con = ConnectionUtil.getConnection())
        {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setInt(2, ticket.getTicketID());
            prstmt.setString(1,ticket.getStatus());
            prstmt.execute();

            
            return prstmt.getUpdateCount();
            

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
        }

        
    }




    

    // public HashSet<String> getRegisteredEmployee()
    // {
    //     HashSet<String> EmployeesRegistered = new HashSet<String>();
    //     String sql = "SELECT email FROM employee";

    //     try (Connection con = ConnectionUtil.getConnection()) {
    //         Statement state = con.createStatement();
    //         ResultSet FS = state.executeQuery(sql);

    //     while(FS.next()){
    //         EmployeesRegistered.add(FS.getString(1));
    //     }

    // }catch (SQLException e){
    //     e.printStackTrace();
    // }

    // return EmployeesRegistered;

    // }
        
    



    

