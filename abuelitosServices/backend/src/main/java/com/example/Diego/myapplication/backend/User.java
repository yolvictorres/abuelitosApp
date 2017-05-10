package com.example.Diego.myapplication.backend;

/**
 * Created by Diego on 08/05/2017.
 */

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by jiacontrerasp on 8/18/15.
 */
@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    @Index
    private String typePeople;
    @Index
    private String typeUser;
    @Index
    private String typePosition;
    @Index
    private String department;
    @Index
    private String status;
    @Index
    private String state;
    private String message;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypePeople() {
        return typePeople;
    }

    public void setTypePeople(String typePeople) {
        this.typePeople = typePeople;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getTypePosition() {
        return typePosition;
    }

    public void setTypePosition(String typePosition) {
        this.typePosition = typePosition;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
