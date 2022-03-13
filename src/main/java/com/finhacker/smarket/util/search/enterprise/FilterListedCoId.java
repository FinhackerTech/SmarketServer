package com.finhacker.smarket.util.search.enterprise;

import com.finhacker.smarket.domain.enterprise.Enterprise;
import com.finhacker.smarket.util.msg.MsgCode;
import com.finhacker.smarket.util.msg.MsgCodeException;
import com.finhacker.smarket.util.search.Filter;
import com.finhacker.smarket.util.search.FilterType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FilterListedCoId implements Filter<Enterprise> {

    private List<Integer> listedCoIdList;

    @Override
    public void getFromFilterType(FilterType filterType) throws MsgCodeException {
        List<Object> parameters = filterType.getParameters();
        if (parameters == null) throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
        listedCoIdList = new ArrayList<>();
        for (Object o : parameters) {
            if (o instanceof Integer) {
                listedCoIdList.add((Integer) o);
            } else throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
        }
    }

    @Override
    public Predicate getPredicate(Root<Enterprise> root, CriteriaBuilder criteriaBuilder) {
        CriteriaBuilder.In<Integer> predicate = criteriaBuilder.in(root.get("listedCoId").as(Integer.class));
        for (Integer id : listedCoIdList) {
            predicate.value(id);
        }
        return predicate;
    }

}
