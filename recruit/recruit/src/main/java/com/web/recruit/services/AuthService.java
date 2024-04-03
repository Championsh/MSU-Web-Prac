package com.web.recruit.services;

import com.web.recruit.dao.AuthDao;
import com.web.recruit.models.*;


public class AuthService extends CommonService<Auth, AuthDao> {
    public AuthService(){
        super(new AuthDao());
    }
}