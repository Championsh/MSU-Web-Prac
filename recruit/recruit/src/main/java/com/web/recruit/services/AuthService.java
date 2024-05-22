package com.web.recruit.services;

import com.web.recruit.dao.AuthDao;
import com.web.recruit.models.*;


public class AuthService extends CommonService<Auth, AuthDao> {
    public AuthService(){
        super(new AuthDao());
    }

    public Auth validateAuth(String mail, String password) {
        return dao.validateAuth(mail, password);
    }

    public Auth findByMail(String mail) {
        return dao.findByMail(mail);
    }

//    public Auth createAuth(String mail, String password) {
//        return dao.validateAuth(mail, password);
//    }
}
