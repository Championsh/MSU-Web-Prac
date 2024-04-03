package com.web.recruit.services;

import com.web.recruit.models.*;
import com.web.recruit.dao.EducationDao;


public class EducationService extends CommonService<Education, EducationDao> {
    public EducationService() {
        super(new EducationDao());
    }
}