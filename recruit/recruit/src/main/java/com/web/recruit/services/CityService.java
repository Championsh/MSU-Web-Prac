package com.web.recruit.services;

import com.web.recruit.dao.CityDao;
import com.web.recruit.models.*;


public class CityService extends CommonService<City, CityDao> {
    public CityService(){
        super(new CityDao());
    }
}
