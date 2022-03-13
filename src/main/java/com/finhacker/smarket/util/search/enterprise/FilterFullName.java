package com.finhacker.smarket.util.search.enterprise;

import com.finhacker.smarket.domain.enterprise.Enterprise;
import com.finhacker.smarket.util.msg.MsgCode;
import com.finhacker.smarket.util.msg.MsgCodeException;
import com.finhacker.smarket.util.search.Filter;
import com.finhacker.smarket.util.search.FilterType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class FilterFullName implements Filter<Enterprise> {

    private String fullName;

    @Override
    public void getFromFilterType(FilterType filterType) throws MsgCodeException {
        List<Object> parameters = filterType.getParameters();
        if (parameters == null) throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
        if (parameters.size() != 1) throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
        Object o = parameters.get(0);
        if (o instanceof String) fullName = (String) o;
        else throw  new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
    }

    @Override
    public Predicate getPredicate(Root<Enterprise> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("fullName"), '%'+fullName+'%');
    }

}
