package dev.finhacker.smarket.service.impl;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.enterprise.EnterpriseRepository;
import dev.finhacker.smarket.domain.enterprise.news.News;
import dev.finhacker.smarket.domain.enterprise.news.NewsRepository;
import dev.finhacker.smarket.service.EnterpriseService;
import dev.finhacker.smarket.util.msg.MsgCode;
import dev.finhacker.smarket.util.msg.MsgCodeException;
import dev.finhacker.smarket.util.search.FilterType;
import dev.finhacker.smarket.util.search.SearchSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Enterprise getEnterprise(Integer id) {
        Optional<Enterprise> result = enterpriseRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public List<News> getAllNews(Enterprise enterprise) {
        return newsRepository.findAllBySymbol(enterprise.getSymbol());
    }

    @Override
    public List<Enterprise> getEnterpriseBySearch(String search, List<FilterType> filterTypes) throws MsgCodeException {
        List<FilterType> searchTypes = Arrays.asList(
                new FilterType("FullName", search),
                new FilterType("IndustryName", search),
                new FilterType("Description", search)
        );
        return enterpriseRepository.findAll(new SearchSpecification<Enterprise>(searchTypes, filterTypes, "enterprise"));
    }

    @Override
    public Page<Enterprise.Brief> getEnterpriseBriefPage(List<Enterprise> enterprises, Pageable pageable) throws MsgCodeException {
        List<Enterprise.Brief> briefList = new ArrayList<>();
        for (Enterprise enterprise : enterprises) {
            briefList.add(enterprise.getBrief());
        }
        int start = (int) pageable.getOffset();
        int end = (start+pageable.getPageSize()) > briefList.size() ? briefList.size() : (start+pageable.getPageSize());
        if (start > end) throw new MsgCodeException(MsgCode.ENTERPRISE_PAGE_ERROR);
        Page<Enterprise.Brief> briefPage = new PageImpl<>(briefList.subList(start, end), pageable, briefList.size());
        return briefPage;
    }

}
