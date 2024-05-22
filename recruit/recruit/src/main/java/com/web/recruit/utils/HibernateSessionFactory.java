package com.web.recruit.utils;

import com.web.recruit.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
            try {
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClasses(
                                Applicant.class,
                                ApplicantCompany.class,
                                Auth.class,
                                City.class,
                                Company.class,
                                Edlevel.class,
                                Education.class,
                                EducationResume.class,
                                Experience.class,
                                ExperienceResume.class,
                                Resume.class,
                                AuthRole.class,
                                Vacancy.class)
                        .buildMetadata()
                        .buildSessionFactory();
            } catch (Exception e) {
                System.out.println("getSessionFactory error");
                throw e;
            }
        }

        return sessionFactory;
    }
}

