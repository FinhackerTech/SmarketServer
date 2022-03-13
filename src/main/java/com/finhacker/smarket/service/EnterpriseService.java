package com.finhacker.smarket.service;

import com.finhacker.smarket.domain.enterprise.Enterprise;
import com.finhacker.smarket.domain.enterprise.news.News;
import com.finhacker.smarket.util.msg.MsgCodeException;
import com.finhacker.smarket.util.search.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnterpriseService {

    /**
     * Get the enterprise by its ID.
     * @param id The ID of the enterprise.
     * @return The enterprise.
     */
    Enterprise getEnterprise(Integer id);

    /**
     * Get all news about the enterprise.
     * @param enterprise The enterprise.
     * @return All news about the enterprise.
     */
    List<News> getAllNews(Enterprise enterprise);

    List<Enterprise> getEnterpriseBySearch(String search, List<FilterType> filterTypes) throws MsgCodeException;

    Page<Enterprise.Brief> getEnterpriseBriefPage(List<Enterprise> enterprises, Pageable pageable) throws MsgCodeException;

    Enterprise.Analyse getAnalyse(Enterprise enterprise);

}
