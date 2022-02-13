package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.enterprise.news.News;
import dev.finhacker.smarket.util.msg.MsgCodeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnterpriseService {

    Enterprise getEnterprise(Integer id);

    List<News> getAllNews(Enterprise enterprise);

    List<Enterprise> getAllEnterprises();

    Page<Enterprise> findAllEnterprise(Pageable pageable);

}
