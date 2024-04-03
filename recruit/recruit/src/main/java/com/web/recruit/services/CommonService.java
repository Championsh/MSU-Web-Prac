package com.web.recruit.services;
import java.util.List;
import com.web.recruit.dao.CommonDao;

public abstract class CommonService<T, DAO extends CommonDao<T>> {
    protected DAO dao;

    public CommonService(DAO dao) {
        this.dao = dao;
    }

    public T findById(Long id) {
        return dao.findById(id);
    }

    public T findById(Integer id) {
        return dao.findById(id.longValue());
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public void save(T obj) {
        dao.save(obj);
    }

    public void update(T obj) {
        dao.update(obj);
    }

    public void delete(T obj) {
        dao.delete(obj);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}