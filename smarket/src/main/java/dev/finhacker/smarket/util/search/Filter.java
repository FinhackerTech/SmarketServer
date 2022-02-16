package dev.finhacker.smarket.util.search;

import dev.finhacker.smarket.util.msg.MsgCodeException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Filter<T> {

    /**
     * Get the parameters from the filter type.
     * @param filterType The filter tpe.
     * @throws MsgCodeException
     */
    void getFromFilterType(FilterType filterType) throws MsgCodeException;

    Predicate getPredicate(Root<T> root, CriteriaBuilder criteriaBuilder);

}
