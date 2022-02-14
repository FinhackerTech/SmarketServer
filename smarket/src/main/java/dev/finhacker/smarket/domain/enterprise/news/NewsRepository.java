package dev.finhacker.smarket.domain.enterprise.news;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, String> {

    News findBySymbol(String symbol);

    List<News> findAllBySymbol(String symbol);

}
