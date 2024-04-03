package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.Arrays;


public class CityTests {

    @Test
    public void testCity() {
        String cityName = "Москва";

        City City = new City(cityName);
        Assertions.assertEquals(City.getCityName(), cityName);
    }

    @Test
    public void testFindById() {
        CityService CityService = new CityService();
        City City = CityService.findById(1L);
        Assertions.assertEquals(City.getCityId(), 1L);
    }

    @Test
    public void testFindAll() {
        CityService CityService = new CityService();
        List<City> City = CityService.findAll();
        Assertions.assertEquals(City.size(), 18);

        List<String> cities = Arrays.asList(
                "Хабаровск",
                "Оренбург",
                "Кишинев",
                "Рязань",
                "Вышний Волочек",
                "Новосибирск",
                "Курск",
                "Санкт-Петербург",
                "Ставрополь",
                "Челябинск",
                "Воронеж",
                "Барнаул",
                "Нижний Новгород",
                "Харьков",
                "Омск",
                "Тверь",
                "Москва",
                "Минск");
        for (int i = 0; i < City.size(); i++) {
            Assertions.assertEquals(City.get(i).getCityId(), i + 1);
            Assertions.assertEquals(City.get(i).getCityName(), cities.get(i));
        }
    }

    @Test
    public void testDeleteById() {
        City tmp_City = new City("Минск");
        CityService CityService = new CityService();
        CityService.save(tmp_City);
        City checK_City = CityService.findById(tmp_City.getCityId());
        Assertions.assertEquals(tmp_City, checK_City);

        CityService.deleteById(tmp_City.getCityId());
        checK_City = CityService.findById(tmp_City.getCityId());
        Assertions.assertNull(checK_City);
    }
}
