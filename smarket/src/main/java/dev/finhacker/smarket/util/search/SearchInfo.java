package dev.finhacker.smarket.util.search;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class SearchInfo {

    @NotNull
    private String searchText;
    @NotNull
    private Integer pageNumber;
    @NotNull
    private List<FilterType> filterTypes;

}
