package dev.finhacker.smarket.service.impl;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.enterprise.EnterpriseRepository;
import dev.finhacker.smarket.domain.enterprise.news.News;
import dev.finhacker.smarket.service.EnterpriseService;
import dev.finhacker.smarket.util.msg.MsgCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

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
        return null;
    }

    @Override
    public List<Enterprise> getAllEnterprises() {
       List<Enterprise> enterpriseList = enterpriseRepository.findAll();
       return enterpriseList;
    }

    @Override
    public Page<Enterprise> findAllEnterprise(Pageable pageable) {
        return enterpriseRepository.findAll(pageable);
    }

}
