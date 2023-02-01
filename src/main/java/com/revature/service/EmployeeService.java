package com.revature.service;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonNode;
import java.util.List;
import java.io.InputStream;
import org.codehaus.jackson.type.TypeReference;

public class EmployeeService {
    
   

    public void EmployeeRegistration(String employeeJson)
    {
        EmployeeRepository repo = new EmployeeRepository();
        //Conversion from string to obj here?
        ObjectMapper mapper = new ObjectMapper();

        try {
            Employee newEmployee = mapper.readValue(employeeJson, Employee.class);

            if (!repo.getRegisteredEmployee().contains(newEmployee.getEmail()))
            {
                repo.Save(newEmployee);
                System.out.println("Employee Registered!");
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


   
    }


    
}
