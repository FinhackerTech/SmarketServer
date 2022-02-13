package dev.finhacker.smarket.domain.enterprise.news;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {

    News findBySymbol(Integer symbol);

}
