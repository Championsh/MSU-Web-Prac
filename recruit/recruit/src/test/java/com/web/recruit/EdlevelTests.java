package com.web.recruit;

import org.junit.jupiter.api.Test;
import com.web.recruit.models.*;
import com.web.recruit.services.*;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.*;


public class EdlevelTests {

    @Test
    public void testEdlevel() {
        Edlevel edlevel = new Edlevel("Специалитет");

        Assertions.assertEquals("Специалитет", edlevel.getEdlevelName());
    }

    @Test
    public void testFindById() {
        EdlevelService edlevelService = new EdlevelService();
        Edlevel edlevel = edlevelService.findById(1L);
        Assertions.assertEquals(edlevel.getEdlevelId(), 1L);
    }

    @Test
    public void testFindAll() {
        EdlevelService edlevelService = new EdlevelService();
        List<Edlevel> edlevel = edlevelService.findAll();
        Assertions.assertEquals(edlevel.size(), 5);
        Assertions.assertEquals(edlevel.get(0).getEdlevelId(), 1);
        Assertions.assertEquals(edlevel.get(1).getEdlevelId(), 2);
        Assertions.assertEquals(edlevel.get(2).getEdlevelId(), 3);
        Assertions.assertEquals(edlevel.get(3).getEdlevelId(), 4);
        Assertions.assertEquals(edlevel.get(4).getEdlevelId(), 5);
    }

    @Test
    public void testDeleteById() {
        EdlevelService edlevelService = new EdlevelService();
        Edlevel tmp_edlevel = new Edlevel("Колледж");
        edlevelService.save(tmp_edlevel);
        Edlevel checK_edlevel = edlevelService.findById(tmp_edlevel.getEdlevelId());
        Assertions.assertEquals(tmp_edlevel, checK_edlevel);

        edlevelService.deleteById(tmp_edlevel.getEdlevelId());
        tmp_edlevel = edlevelService.findById(tmp_edlevel.getEdlevelId());
        Assertions.assertNull(tmp_edlevel);
    }
}
