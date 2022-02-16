package dev.finhacker.smarket.util.search;

import dev.finhacker.smarket.util.msg.MsgCode;
import dev.finhacker.smarket.util.msg.MsgCodeException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SearchSpecification<T> implements Specification<T> {

    private List<Filter> searchList;
    private List<Filter> filterList;

    public SearchSpecification(List<FilterType> searchTypes, List<FilterType> filterTypes, String packageName) throws MsgCodeException {
        searchList = new ArrayList<>();
        filterList = new ArrayList<>();
        for (FilterType searchType : searchTypes) {
            addFilterToList(searchList, searchType, packageName);
        }
        for (FilterType filterType : filterTypes) {
            addFilterToList(filterList, filterType, packageName);
        }
    }

    private void addFilterToList(List<Filter> filters, FilterType filterType, String packageName) throws MsgCodeException {
        try {
            Class c = Class.forName("dev.finhacker.smarket.util.search."+packageName+".Filter"+filterType.getName());
            Object o = c.getDeclaredConstructor().newInstance();
            if (o instanceof Filter) {
                Filter filter = (Filter) o;
                filter.getFromFilterType(filterType);
                filters.add(filter);
            } else throw new MsgCodeException(MsgCode.UNKNOWN);
        } catch (Exception e) {
            throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_TYPE_NOT_FOUND);
        }
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.disjunction();
        for (Filter search : searchList) {
            predicate = criteriaBuilder.or(predicate, search.getPredicate(root, criteriaBuilder));
        }
        for (Filter filter : filterList) {
            predicate = criteriaBuilder.and(predicate, filter.getPredicate(root, criteriaBuilder));
        }
        return predicate;
    }

}
