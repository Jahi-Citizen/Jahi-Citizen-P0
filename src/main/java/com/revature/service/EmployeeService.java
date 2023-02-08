package com.revature.service;

import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.TicketRepository;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonNode;
import java.util.List;

import javax.management.relation.Role;

import java.io.InputStream;
import org.codehaus.jackson.type.TypeReference;

public class EmployeeService {
    
   

    public String EmployeeRegistration(String employee)
    {
        String value = "";
        EmployeeRepository repo = new EmployeeRepository();
        //Conversion from string to obj here?
        ObjectMapper mapper = new ObjectMapper();
       

        try {
            Employee newEmployee = mapper.readValue(employee, Employee.class);
            System.out.println(newEmployee.getPassword());

            if (!repo.getRegisteredEmployee().contains(newEmployee.getEmail()))
            {
                repo.Save(newEmployee);
                value = "Registered";
                return value;
            }
            else 
            {
                value = "Operation FAILED, email is not unique";
                return value;
            }
            

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "failure";


    }


public String EmployeeLogin(String employee)
    {
        String value = "";
        EmployeeRepository repo = new EmployeeRepository();
        //Conversion from string to obj here?
        ObjectMapper mapper = new ObjectMapper();
       

        try {
            Employee newEmployee = mapper.readValue(employee, Employee.class);
            System.out.println(newEmployee.getPassword());

            if (newEmployee.getEmail().equals(repo.getEmail(newEmployee)) && newEmployee.getPassword().equals(repo.getPassword(newEmployee)) )
            {
                
                value = "You have successfully logged in!";
                return value;
            }
            else 
            {
                value = "OPERATION FAILED, Wrong email or password provided";
                return value;
            }
          
            

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "failure";


    }


    public String verifyAndTicketHistory(String ticket)
    {
        String values = ""; 
        TicketRepository repo = new TicketRepository();
        //Conversion from string to obj here?
        ObjectMapper mapper = new ObjectMapper();
       

        try {
            Ticket newTicket = mapper.readValue(ticket, Ticket.class);
            
           
           

            if ((repo.getRole(newTicket)).toString().equals(Employee.Role.Standard.toString()))
            {
                return "You are a standard employee, loser";
            }
            else if ((repo.getRole(newTicket)).toString().equals(Employee.Role.Manager.toString()))
            {
                values = mapper.writeValueAsString(repo.getPendingTickets());
            }
            else 
            {
                System.out.println("Welcome lord OverLoser, aka administrator");
            }
            
            

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return values; 

    }

    public String ticketApprovalOrDenial(String ticket)
    {
        String values = ""; 
        TicketRepository repo = new TicketRepository();
        //Conversion from string to obj here?
        ObjectMapper mapper = new ObjectMapper();
       

        try {
            Ticket newTicket = mapper.readValue(ticket, Ticket.class);
           
           

            if ((repo.getRole(newTicket)).toString().equals(Employee.Role.Standard.toString()))
            {
                return "Failure, a standard employee cannot approve or deny a ticket";
            }
            else if ((repo.getRole(newTicket)).toString().equals(Employee.Role.Manager.toString()))
            {
                if(repo.changeTicketStatus(newTicket) == 1) 
                {
                    return "Success, you have changed the tickets status!";
                }
                else 
                {
                    return "Failure, changes were not made";
                }
            }
            else 
            {
                return "Something went wrong in permissions detection";
            }
            
            

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return values; 

    }


    public String employeeTicketHistory(String ticket)
    {
        String values = ""; 
        TicketRepository repo = new TicketRepository();
        //Conversion from string to obj here?
        ObjectMapper mapper = new ObjectMapper();
       

        try {
            Ticket newTicket = mapper.readValue(ticket, Ticket.class);
            JsonNode jsonNode = mapper.readTree(ticket);
           
           
            if (jsonNode.get("status") == null)
            {
                System.out.println("boo");
                values = mapper.writeValueAsString(repo.getAllEmployeeTickets(newTicket));
                return values;
            }
            else if (jsonNode.get("status").asText().equals("Approved"))
            {
                newTicket.setStatus("Denied");
                values = mapper.writeValueAsString(repo.getAllEmployeeTicketsFiltered(newTicket));
                return values;
            }
            else if (jsonNode.get("status").asText().equals("Denied"))
            {
                values = mapper.writeValueAsString(repo.getAllEmployeeTicketsFiltered(newTicket));
                return values;
            }
            else if (jsonNode.get("status").asText().equals("Pending"))
            {
                values = mapper.writeValueAsString(repo.getAllEmployeeTicketsFiltered(newTicket));
                return values;
            }


        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return values; 

    }

   

  

 


    
}
