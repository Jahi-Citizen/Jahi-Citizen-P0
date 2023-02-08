package com.revature.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    //numeric employee identifier 
    protected int employeeID;

    //fields for the employee class 
    protected String name; 
    protected String email;
    protected String userName; 
    protected String password;
    

    public enum Role
    {
        Standard, 
        Manager, 
        Admin
    }

   Role employeeRole = Role.Standard; 
    
    public int getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getEmployeeRole() {
        return employeeRole;
    }
    public void setEmployeeRole(Role employeeRole) {
        this.employeeRole = employeeRole;
    }
    public Object getRole() {
        return null;
    }



    
}
