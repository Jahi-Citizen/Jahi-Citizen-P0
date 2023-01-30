package com.revature.controllers;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


import org.omg.CORBA.portable.InputStream;

import com.revature.service.EmployeeService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EmployeeController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub
        String verb = exchange.getRequestMethod();

        //Verb switch statements 
        switch (verb) {
            case "POST":
                postRequest(exchange);
                break;
        
            default:
                break;
        }
    }

    private void postRequest(HttpExchange exchange) throws IOException {
        
        //InputStream has a bunch of bytes
        InputStream is = exchange.getRequestBody();

        //We need to convert the InputStream into String
        //StringBuilder is like a mutable version of a String
        StringBuilder textBuilder = new StringBuilder();

        //Converts our binary into letters
        //try_resource block will automatically close the resource within the parenthesis
        try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;

            //read() method from BufferReader will give a -1 once there is no more letters left
            //TLDR keep reading each letter until there is no more left
            while ((c = reader.read()) != -1) {
                //Adds the letter to your text
                textBuilder.append((char)c);
            }
        } 

        exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);

        //Don't forget to call on the service layer and execute the method
        EmployeeService pokeService = new EmployeeService();
        pokeService.saveToPokeBox(textBuilder.toString());

        OutputStream os = exchange.getResponseBody();
        os.write(textBuilder.toString().getBytes());
        os.close();
    }
    
    
}
