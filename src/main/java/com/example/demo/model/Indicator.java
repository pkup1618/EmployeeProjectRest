package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Table("metric")
public class Indicator implements Persistable<IndicatorId>{
    @Id
    private IndicatorId metricId;

    private Long value; // constraints 0-100

    @Transient
    private boolean isNew;
    
    public void setValue(Long value) {
        if (value < 0) {
            this.value = 0L;
        } else if (value > 100) {
            this.value = 100L;
        } else {
            this.value = value;
        }
    }

    @Override
    public IndicatorId getId() {
        return getMetricId();
    }

    @PersistenceCreator
    public Indicator(IndicatorId metricId, Long value) {
        this.metricId = metricId;
        this.value = value;
    }
}