package com.example.demo.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndicatorView {
    
    @Setter private String name;
    @Setter private Long bossId;
    private Long value;
    @Setter private Long employeeCount;
    private Long averageKpi;

    public void setAverageKpi(Long averageKpi) {
        if (averageKpi < 0) {
            this.averageKpi = 0L;
        } else if (averageKpi > 100) {
            this.averageKpi = 100L;
        } else {
            this.averageKpi = averageKpi;
        }
    }

    public void setValue(Long value) {
        if (value < 0) {
            this.value = 0L;
        } else if (value > 100) {
            this.value = 100L;
        } else {
            this.value = value;
        }
    }
}