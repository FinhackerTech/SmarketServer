package com.finhacker.smarket.util.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterType {

    private String name;

    private List<Object> parameters;

    public FilterType(String name, Object parameter) {
        this.name = name;
        this.parameters = Arrays.asList(parameter);
    }

}
