package com.revature.controllers;


import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.io.InputStream;

import com.revature.service.EmployeeService;
import com.revature.service.TicketService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class TicketCreationController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub

        String verb = exchange.getRequestMethod();

        //Verb switch statements 
        switch (verb) {

            case "GET":
                getRequest(exchange);

            case "POST":
                postRequest(exchange);
                break;
        
            default:
                break;
        }
        
    }

    private void getRequest(HttpExchange exchange) throws IOException {
        
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
    }


    private void postRequest(HttpExchange exchange) throws IOException {
        
        //InputStream has a bunch of bytes
        InputStream is = exchange.getRequestBody();

        //We need to convert the InputStream into String
        //StringBuilder is like a mutable version of a String
        String value = "";
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

        

        //Don't forget to call on the service layer and execute the method
        TicketService ticketService = new TicketService();
        value = ticketService.ticketCreation(textBuilder.toString());
        exchange.sendResponseHeaders(200, value.toString().getBytes().length);
      

        
        // TicketService.ticketHistory(textBuilder.toString());

        OutputStream os = exchange.getResponseBody();
        os.write(value.getBytes());
        os.close();
    }
    
}

