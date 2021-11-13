package com.example.usd.dao;

import com.example.usd.entity.Rate;
import com.example.usd.entity.Usd;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import java.time.Period;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class UsdDao {

    private final String urlCommon = "https://api.nbp.pl/api/exchangerates/rates/c/usd/%Start_Date%/%End_Date%/?format=json";

    Usd usd = new Usd();
    List<Rate> rates = new LinkedList<>();

    public Usd parse(String startDate) {

        ObjectMapper mapper = new ObjectMapper();
        LocalDate dateEnd = LocalDate.now();
        try {
            String url = urlCommon.replace("%Start_Date%", startDate).replace("%End_Date%", dateEnd.toString());
            // JSON file to Java object
            usd = mapper.readValue(new URL(url), Usd.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usd;
    }

    public List<String> getDates() {
        rates = usd.getRates();
        List<String> dates = new LinkedList<>();
        for (Rate element : rates) {
            dates.add(element.getEffectiveDate());
        }
        return dates;
    }

    public List<Float> getBids() {
        rates = usd.getRates();
        List<Float> bids = new LinkedList<>();
        try {
            for (Rate element : rates) {
                float f = Float.parseFloat(element.getBid());
                bids.add(f);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return bids;
    }

    public List<Float> getAsks() {
        rates = usd.getRates();
        List<Float> asks = new LinkedList<>();
        try {
            for (Rate element : rates) {
                float f = Float.parseFloat(element.getAsk());
                asks.add(f);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return asks;
    }

    public Float getAsksDifference() {
        rates = usd.getRates();
        List<Float> asks = getAsks();
        return asks.get(asks.size() - 1) - asks.get(0);
    }

    public Float getBidsDifference() {
        rates = usd.getRates();
        List<Float> bids = getBids();
        return bids.get(bids.size() - 1) - bids.get(0);
    }
}
