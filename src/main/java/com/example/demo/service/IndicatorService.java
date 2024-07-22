package com.example.demo.service;


import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.IndicatorRepository;
import com.example.demo.view.IndicatorView;


@Service
public class IndicatorService {

    private final IndicatorRepository indicatorRepository;

    @Autowired
    public IndicatorService(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    public List<IndicatorView> getAllIndicatorsWithAverageValue(String order) {
        List<IndicatorView> indicators = indicatorRepository.findAllWithAvgValue();

        if (order == null) {
            order = "";
        }

        switch (order) {
            case "asc": {
                indicators.sort(Comparator.comparingDouble(IndicatorView::averageKpi));
                break;
            }
            case "desc": {
                indicators.sort((o1, o2) -> -Double.compare(o1.averageKpi(), o2.averageKpi()));
                break;
            }
            default: {
                indicators.sort(Comparator.comparing(IndicatorView::name));
                break;
            }
        }
        return indicators;
    }
}