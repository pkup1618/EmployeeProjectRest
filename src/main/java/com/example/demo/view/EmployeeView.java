package com.example.demo.view;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeView {
    @Setter private Long id;
    @Setter private String name;
    @Setter private Long bossId;
    @Setter private List<EmployeeView> subordinates;
    private Double averageKpi;

    public void setAverageKpi(Double averageKpi) {
        if (averageKpi < 0) {
            this.averageKpi = 0.0;
        } else if (averageKpi > 100) {
            this.averageKpi = 100.0;
        } else {
            this.averageKpi = averageKpi;
        }
    }
}