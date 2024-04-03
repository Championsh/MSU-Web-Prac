package com.web.recruit.services;

import com.web.recruit.models.*;
import com.web.recruit.dao.EdlevelDao;

import com.web.recruit.models.Edlevel;
import java.util.List;
import java.util.Set;


public class EdlevelService extends CommonService<Edlevel, EdlevelDao> {
    public EdlevelService(){
        super(new EdlevelDao());
    }

}
