package dev.finhacker.smarket.util.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchInfo {

    private String searchText;
    private Integer pageNumber;
    private List<FilterType> filterTypes;

}
