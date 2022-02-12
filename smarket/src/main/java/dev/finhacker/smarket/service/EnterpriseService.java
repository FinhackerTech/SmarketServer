package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnterpriseService {

    Enterprise getEnterprise(Integer id);

    List<Enterprise> getAllEnterprises();

    Page<Enterprise> findAllEnterprise(Pageable pageable);


}
