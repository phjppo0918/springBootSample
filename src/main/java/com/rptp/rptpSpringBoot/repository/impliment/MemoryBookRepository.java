package com.rptp.rptpSpringBoot.repository.impliment;

import com.rptp.rptpSpringBoot.model.BookDTO;
import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.BookRepository;

import java.util.*;

public class MemoryBookRepository implements BookRepository {
    private static Map<Long, BookDTO> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public BookDTO save(BookDTO bookDTO) {
        bookDTO.setId(++sequence);
        store.put(bookDTO.getId(),bookDTO);
        return bookDTO;
    }

    @Override
    public Optional<BookDTO> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<BookDTO> findByName(String name) {
        return store.values().stream()
                .filter(bookDTO -> bookDTO.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<BookDTO> deleteById(Long id) {
        BookDTO bookDTO = store.get(id);

        return  Optional.ofNullable(store.remove(id));
    }

    @Override
    public List<BookDTO> findAll() {
        return new ArrayList<>(store.values());
    }
}
