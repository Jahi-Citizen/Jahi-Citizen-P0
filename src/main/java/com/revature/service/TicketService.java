package com.revature.service;

import java.io.IOException;
import java.sql.SQLException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.postgresql.util.PSQLException;

import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.TicketRepository;

public class TicketService {
    
    public String ticketCreation(String ticket)
    {
        TicketRepository repo = new TicketRepository();
        // EmployeeRepository repo1 = new EmployeeRepository();
        //Conversion from string to obj here?
        ObjectMapper mapper = new ObjectMapper();

        try {
            Ticket newTicket = mapper.readValue(ticket, Ticket.class);
            // Employee newEmployee = mapper.readValue(employee, Employee.class);
            
         
            if (newTicket.getEmail().equals(repo.getEmail(newTicket)) && newTicket.getPassword().equals(repo.getPassword(newTicket)))
            {
                if (newTicket.getAmount() != -25 && newTicket.getDescription() != "null" && newTicket.getAmount() > 0)
                {
                    repo.Save(newTicket);
                    return "Ticket successfully created!";
                }
                else 
                {
                    return "Either an amount or description was not provided";
                }
            }
            else {
                return "Ticket denied, WRONG EMAIL OR PASSWORD";
            }

       


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "null";
   
    }

    public  void ticketApproval(String ticket)
    {
        TicketRepository repo = new TicketRepository();
        // EmployeeRepository repo1 = new EmployeeRepository();
        //Conversion from string to obj here?
        ObjectMapper mapper = new ObjectMapper();

        try {
            Ticket newTicket = mapper.readValue(ticket, Ticket.class);
            // Employee newEmployee = mapper.readValue(employee, Employee.class);
            
         
            if (newTicket.getEmail().equals(repo.getEmail(newTicket)) && newTicket.getPassword().equals(repo.getPassword(newTicket)))
            {
                if (newTicket.getAmount() != -25 && newTicket.getDescription() != "null")
                {
                    repo.Save(newTicket);
                    System.out.println("Ticket created!");
                }
                else 
                {
                    System.out.println("Either an amount or description was not provided");
                }
            }
            else {
                System.out.println("Ticket denied, WRONG EMAIL OR PASSWORD");
            }

       


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   
    }





    //TODO: Add the ability for employees to pull up a list of their current tickets (w status)
    //TODO: ADD MANAGER APPROVE OR DENY FUNCTION AND ABILITY TO PULL ALL TICKETS
    // public static void ticketHistory(String employee)
    // {
        
    //     EmployeeRepository repo1 = new EmployeeRepository();
    //     //Conversion from string to obj here?
    //     ObjectMapper mapper = new ObjectMapper();
        

    //     try {
    //         Employee newEmployee = mapper.readValue(employee, Employee.class);
    //         System.out.println(repo1.getEmail(newEmployee));
            
         
    //     //    if (newEmployee.getEmail().equals(repo1.getEmail(newEmployee)) && newEmployee.getPassword().equals(repo1.getPassword(newEmployee)))
    //     //    {
    //     //     repo.Save(newTicket);
    //     //     System.out.println("Ticket created!");  
    //     //    }
            
               
         

    //     } catch (JsonParseException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     } catch (JsonMappingException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }


   
    // }


}
