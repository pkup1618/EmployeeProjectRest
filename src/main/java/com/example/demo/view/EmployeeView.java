package com.example.demo.view;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeView {
    private Long id;
    private String name;
    private Long bossId;
    private List<EmployeeView> subordinates;
    private Long averageKpi;
}