package com.rptp.rptpSpringBoot.repository;

import com.rptp.rptpSpringBoot.model.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    BookDTO save(BookDTO bookDTO);
    Optional<BookDTO> findById(Long id);
    Optional<BookDTO> findByName(String name);
    Optional<BookDTO> deleteById(Long id);
    List<BookDTO> findAll();
}

