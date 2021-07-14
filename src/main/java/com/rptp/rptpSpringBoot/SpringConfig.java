package com.rptp.rptpSpringBoot;

import com.rptp.rptpSpringBoot.aop.TimeTraceAop;
import com.rptp.rptpSpringBoot.controller.BookController;
import com.rptp.rptpSpringBoot.repository.BookRepository;
import com.rptp.rptpSpringBoot.repository.impliment.MemoryBookRepository;
import com.rptp.rptpSpringBoot.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public BookController bookController() {
        return new BookController(bookService());
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository());
    }

    @Bean
    public BookRepository bookRepository() {
        return new MemoryBookRepository();
    }

}
