package com.revature.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.revature.model.Employee;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class EmployeeRepository {
    
    public void Save(Employee employee)
    {
        //Actual implementation here
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = "";

        try {
            
            //Converted the pokemon obj into json
           jsonObject = mapper.writeValueAsString(employee);

           //Save the json into a file
           //File constructor needs a string that holds the path of where you want to save the file
           File employeeFile = new File("./src/main/java/com/revature/repository/pokemon.json");
           employeeFile.createNewFile();

           //Writing the file
            FileWriter writer = new FileWriter("./src/main/java/com/revature/repository/pokemon.json");
            writer.write(jsonObject); //Writes the string into the file
            writer.close(); //Closes the necessary resources associated with a filewriter object

        } catch (JsonGenerationException e) {
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
