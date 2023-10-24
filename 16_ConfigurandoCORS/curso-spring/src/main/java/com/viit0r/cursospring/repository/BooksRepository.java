package com.viit0r.cursospring.repository;

import com.viit0r.cursospring.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
