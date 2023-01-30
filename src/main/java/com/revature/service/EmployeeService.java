package com.revature.service;

public class EmployeeService {
    
   

    public void EmployeeRegistration(String employeeJson)
    {
        PokemonRepository repo = new PokemonRepository();
        //Conversion from string to pokemon obj here?
        ObjectMapper mapper = new ObjectMapper();

        try {
            Pokemon newPokemon = mapper.readValue(pokeJson, Pokemon.class);

            repo.Save(newPokemon);

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
