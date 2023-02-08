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
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EmpTicketHistoryController implements HttpHandler {

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
        String ticketHistory = "";
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
        EmployeeService employeeService = new EmployeeService();
        ticketHistory = employeeService.employeeTicketHistory(textBuilder.toString());
        exchange.sendResponseHeaders(200, ticketHistory.getBytes().length);
        
       

        OutputStream os = exchange.getResponseBody();
        os.write(ticketHistory.getBytes());
        os.close();
    }
    
    
}