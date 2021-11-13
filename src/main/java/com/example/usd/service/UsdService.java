package com.example.usd.service;

import com.example.usd.dao.UsdDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsdService {

    public List<String> getDates(String date){
        UsdDao usdDao = new UsdDao();
        usdDao.parse(date);
        return usdDao.getDates();
    }

    public List<Float> getBids(String date){
        UsdDao usdDao = new UsdDao();
        usdDao.parse(date);
        return usdDao.getBids();
    }

    public List<Float> getAsks(String date){
        UsdDao usdDao = new UsdDao();
        usdDao.parse(date);
        return usdDao.getAsks();
    }
    public Float getAsksDifference(String date){
        UsdDao usdDao = new UsdDao();
        usdDao.parse(date);
        return usdDao.getAsksDifference();
    }
    public Float getBidsDifference(String date){
        UsdDao usdDao = new UsdDao();
        usdDao.parse(date);
        return usdDao.getBidsDifference();
    }
}
