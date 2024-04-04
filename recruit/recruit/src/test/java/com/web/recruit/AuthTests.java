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
        RoleName rolename = RoleName.valueOf("соискатель");

        Auth auth = new Auth(mail, pwd, rolename);
        Assertions.assertEquals(auth.getMail(), mail);
        Assertions.assertEquals(auth.getPassword(), pwd);
        Assertions.assertEquals(auth.getAuthRole(), rolename);
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
        Assertions.assertEquals(Auth.get(0).getAuthRole(), RoleName.соискатель);
        Assertions.assertEquals(Auth.get(1).getAuthId(), 2);
        Assertions.assertEquals(Auth.get(1).getMail(), "second@mail.ru");
        Assertions.assertEquals(Auth.get(1).getPassword(), "secondpwd");
        Assertions.assertEquals(Auth.get(1).getAuthRole(), RoleName.студент);
        Assertions.assertEquals(Auth.get(2).getAuthId(), 3);
        Assertions.assertEquals(Auth.get(2).getMail(), "third@mail.ru");
        Assertions.assertEquals(Auth.get(2).getPassword(), "thirdpwd");
        Assertions.assertEquals(Auth.get(2).getAuthRole(), RoleName.valueOf("работодатель"));
        Assertions.assertEquals(Auth.get(3).getAuthId(), 4);
        Assertions.assertEquals(Auth.get(3).getMail(), "fourth@mail.ru");
        Assertions.assertEquals(Auth.get(3).getPassword(), "fourthpwd");
        Assertions.assertEquals(Auth.get(3).getAuthRole(), RoleName.valueOf("работодатель"));
    }

    @Test
    public void testUpdateDelete() {
        AuthService authService = new AuthService();
        Auth auth = authService.findById(1);
        auth.setAuthRole(RoleName.valueOf("студент"));
        authService.update(auth);
        Auth new_auth = authService.findById(auth.getAuthId());
        Assertions.assertEquals(auth.getAuthRole(), new_auth.getAuthRole());
    }
}
