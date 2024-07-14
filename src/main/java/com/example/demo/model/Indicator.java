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
@Table("indicator")
public class Indicator implements Persistable<IndicatorId>{
    @Id
    private IndicatorId indicatorId;

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
        return getIndicatorId();
    }

    @PersistenceCreator
    public Indicator(IndicatorId indicatorId, Long value) {
        this.indicatorId = indicatorId;
        this.value = value;
    }
}