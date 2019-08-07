/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Gehad
 */
public class Admin {
    private String username;
    private String password;
    
    public Admin(){
        username = "admin";
        password = "admin";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return new String(md.digest(password.getBytes()));
    }

}
