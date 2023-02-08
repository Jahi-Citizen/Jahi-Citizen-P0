package com.revature;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.revature.controllers.EmployeeRegController;
import com.revature.controllers.EmpTicketHistoryController;
import com.revature.controllers.EmployeeLoginController;
import com.revature.controllers.ManagerActionController;
import com.revature.controllers.TicketCreationController;
import com.revature.controllers.ManagerTicketHistoryController;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/registration", new EmployeeRegController());
        server.createContext("/login", new EmployeeLoginController());
        server.createContext("/ticketCreation", new TicketCreationController());
        server.createContext("/ticketStatusChange", new ManagerActionController());
        server.createContext("/ticketViewHistory", new ManagerTicketHistoryController());
        server.createContext("/EmpticketViewHistory", new EmpTicketHistoryController());
        

        server.setExecutor(null);
        server.start();

        String pass = System.getenv("url");
        System.out.println(pass);
        

        
    }
}
