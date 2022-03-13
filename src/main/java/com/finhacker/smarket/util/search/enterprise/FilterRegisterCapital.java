package com.finhacker.smarket.util.search.enterprise;

import com.finhacker.smarket.domain.enterprise.Enterprise;
import com.finhacker.smarket.util.msg.MsgCode;
import com.finhacker.smarket.util.msg.MsgCodeException;
import com.finhacker.smarket.util.search.Filter;
import com.finhacker.smarket.util.search.FilterType;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Data
public class FilterRegisterCapital implements Filter<Enterprise> {

    private Long minimum;
    private Long maximum;

    @Override
    public void getFromFilterType(FilterType filterType) throws MsgCodeException {
        List<Object> parameters = filterType.getParameters();
        if (parameters == null) throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
        if (parameters.size() != 2) throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
        Object o1 = parameters.get(0), o2 = parameters.get(1);
        if ((o1 instanceof Long || o1 instanceof Integer) && (o2 instanceof Long || o2 instanceof Integer)) {
            if (o1 instanceof Integer) minimum = ((Integer) o1).longValue();
            else minimum = (Long) o1;
            if (o2 instanceof Integer) maximum = ((Integer) o2).longValue();
            else maximum = (Long) o2;
            if (minimum > maximum) throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
        } else throw new MsgCodeException(MsgCode.ENTERPRISE_FILTER_PARAM_ERROR);
    }

    @Override
    public Predicate getPredicate(Root<Enterprise> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.between(root.get("registerCapital"), minimum, maximum);
    }

}
