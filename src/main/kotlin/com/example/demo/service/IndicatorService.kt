package com.example.demo.service

import com.example.demo.repository.IndicatorRepository
import com.example.demo.view.IndicatorView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class IndicatorService @Autowired constructor(
    private val indicatorRepository: IndicatorRepository) {

    fun getAllIndicatorsWithAverageValue(order: String?): List<IndicatorView> {

        val indicators: List<IndicatorView> = indicatorRepository.findAllWithAvgValue()

        when (order) {
            "asc" -> {
                indicators.sortedWith(Comparator.comparingDouble(IndicatorView::averageKpi))
            }
            "desc" -> {
                indicators.sortedWith { o1: IndicatorView, o2: IndicatorView ->
                    -o1.averageKpi.compareTo(o2.averageKpi)
                }
            }
            else -> {
                indicators.sortedWith(Comparator.comparing(IndicatorView::name))
            }
        }

        return indicators
    }
}