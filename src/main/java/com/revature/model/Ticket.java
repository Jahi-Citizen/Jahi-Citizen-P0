package com.revature.model;

public class Ticket {
    
    private int ticketID;
    private String name; 
    private String description;
    private int amount; 
    private String status = "";
    protected String email;
    protected String password;

    

    public Ticket()
    {
        amount = -25; 
        description = "null";
        status = "pending";
    }
    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
   
    public String getDescription() {
        
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   




}
