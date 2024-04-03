package com.web.recruit.services;

import com.web.recruit.models.*;
import com.web.recruit.dao.ExperienceDao;


public class ExperienceService extends CommonService<Experience, ExperienceDao> {
    public ExperienceService() {
        super(new ExperienceDao());
    }
}