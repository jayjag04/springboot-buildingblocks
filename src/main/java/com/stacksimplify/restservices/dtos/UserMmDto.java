package com.stacksimplify.restservices.dtos;

import com.stacksimplify.restservices.entities.Order;

import java.util.List;

public class UserMmDto {

    private Long userid;
    private String username;
    private String firstname;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    private List<Order> orders;

    public void setFirstname(String fn)
    {
        this.firstname = fn;
    }

    public String getFirstname() {
        return firstname;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    } 

}
