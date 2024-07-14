package com.example.demo.service;

import java.util.Comparator;
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
        List<IndicatorView> indicators = indicatorRepository.findAllWithAvgValue();

        if (order == null) {
            order = "";
        }

        switch(order) {
            case "asc": {
                indicators.sort(new Comparator<IndicatorView>() {

                    @Override
                    public int compare(IndicatorView o1, IndicatorView o2) {
                        return Double.compare(o1.getAverageKpi(), o2.getAverageKpi());
                    }
                });
                break;
            }
            case "desc": {
                indicators.sort(new Comparator<IndicatorView>() {
                    
                    @Override
                    public int compare(IndicatorView o1, IndicatorView o2) {
                        return 0 - Double.compare(o1.getAverageKpi(), o2.getAverageKpi());
                    }
                });
                break;
            }
            default: {
                indicators.sort(new Comparator<IndicatorView>() {

                    @Override
                    public int compare(IndicatorView o1, IndicatorView o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                break;
            }
        }
        return indicators;
    }
}