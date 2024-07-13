package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.IndicatorRepository;
import com.example.demo.view.IndicatorView;


@Service
public class IndicatorService {

    IndicatorRepository indicatorRepository;
    
    @Autowired
    public void setIndicatorRepository(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    public List<IndicatorView> getAllIndicatorsWithAverageValue(String order) {
        return indicatorRepository.findAllWithAvgValue(order);
    }
}