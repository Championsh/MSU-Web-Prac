package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import org.junit.jupiter.api.*;

import java.util.List;


public class AuthTests {

    @Test
    public void testAuth() {
        String mail = "first@mail.ru";
        String pwd = "firstpwd";
        AuthRole AuthRole = com.web.recruit.models.AuthRole.valueOf("соискатель");

        Auth auth = new Auth(mail, pwd, AuthRole);
        Assertions.assertEquals(auth.getMail(), mail);
        Assertions.assertEquals(auth.getPassword(), pwd);
        Assertions.assertEquals(auth.getAuthRole(), AuthRole);
    }

    @Test
    public void testFindById() {
        AuthService authService = new AuthService();
        Auth auth = authService.findById(1);
        Assertions.assertEquals(auth.getAuthId(), 1L);
    }

    @Test
    public void testFindAll() {
        AuthService AuthService = new AuthService();
        List<Auth> Auth = AuthService.findAll();
        Assertions.assertEquals(Auth.size(), 4);
        Assertions.assertEquals(Auth.get(0).getAuthId(), 1);
        Assertions.assertEquals(Auth.get(0).getMail(), "first@mail.ru");
        Assertions.assertEquals(Auth.get(0).getPassword(), "firstpwd");
        Assertions.assertEquals(Auth.get(0).getAuthRole(), AuthRole.соискатель);
        Assertions.assertEquals(Auth.get(1).getAuthId(), 2);
        Assertions.assertEquals(Auth.get(1).getMail(), "second@mail.ru");
        Assertions.assertEquals(Auth.get(1).getPassword(), "secondpwd");
        Assertions.assertEquals(Auth.get(1).getAuthRole(), AuthRole.студент);
        Assertions.assertEquals(Auth.get(2).getAuthId(), 3);
        Assertions.assertEquals(Auth.get(2).getMail(), "third@mail.ru");
        Assertions.assertEquals(Auth.get(2).getPassword(), "thirdpwd");
        Assertions.assertEquals(Auth.get(2).getAuthRole(), AuthRole.valueOf("работодатель"));
        Assertions.assertEquals(Auth.get(3).getAuthId(), 4);
        Assertions.assertEquals(Auth.get(3).getMail(), "fourth@mail.ru");
        Assertions.assertEquals(Auth.get(3).getPassword(), "fourthpwd");
        Assertions.assertEquals(Auth.get(3).getAuthRole(), AuthRole.valueOf("работодатель"));
    }

    @Test
    public void testUpdateDelete() {
        AuthService authService = new AuthService();
        Auth auth = authService.findById(1);
        auth.setAuthRole(AuthRole.valueOf("студент"));
        authService.update(auth);
        Auth new_auth = authService.findById(auth.getAuthId());
        Assertions.assertEquals(auth.getAuthRole(), new_auth.getAuthRole());
    }

    @Test
    public void testValidate() {
        AuthService authService = new AuthService();
        Auth auth = authService.validateAuth("third@mail.ru", "thirdpwd");
        Assertions.assertNotEquals(null, auth);
    }
}
