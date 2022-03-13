package com.finhacker.smarket.domain.enterprise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer>, JpaSpecificationExecutor<Enterprise> {

}
