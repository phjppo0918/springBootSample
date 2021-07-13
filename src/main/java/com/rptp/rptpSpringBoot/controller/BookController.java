package com.rptp.rptpSpringBoot.controller;

import com.rptp.rptpSpringBoot.service.BookService;
import com.rptp.rptpSpringBoot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
