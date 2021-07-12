package com.rptp.rptpSpringBoot.service;

import com.rptp.rptpSpringBoot.model.BookDTO;
import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.BookRepository;
import com.rptp.rptpSpringBoot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //책 등록
    public Long register(BookDTO bookDTO) {
        // 같은 이름의 책 x
        validateDuplicateMember(bookDTO);

        bookRepository.save(bookDTO);
        return bookDTO.getId();
    }

    private void validateDuplicateMember(BookDTO bookDTO) {
        bookRepository.findByName(bookDTO.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 책입니다");
                });
    }

    //전체 책 조회
    public List<BookDTO> findBooks() {
        return bookRepository.findAll();
    }

    // id에 맞는 회원 조회
    public Optional<BookDTO> findOne(Long bookId) {
        return bookRepository.findById(bookId);
    }

    // 책 제거
    public Optional<BookDTO> removeBook(Long bookId) {
        return bookRepository.deleteById(bookId);
    }
}
