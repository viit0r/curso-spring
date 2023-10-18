package com.viit0r.cursospring.service;

import com.viit0r.cursospring.controller.BooksController;
import com.viit0r.cursospring.dto.v1.BooksDTO;
import com.viit0r.cursospring.exception.RequiredObjectIsNullException;
import com.viit0r.cursospring.exception.ResourceNotFoundException;
import com.viit0r.cursospring.mapper.Mapper;
import com.viit0r.cursospring.model.Books;
import com.viit0r.cursospring.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksService {
    private final Logger logger = Logger.getLogger(BooksService.class.getName());

    @Autowired
    BooksRepository booksRepository;

    public List<BooksDTO> findAll() {
        logger.info("Buscando todos os livros...");

        List<BooksDTO> livrosDTO = Mapper.parseListObjects(booksRepository.findAll(), BooksDTO.class);

        //Adicionando HATEOAS em cada item da lista
        livrosDTO.forEach(livro -> livro.add(linkTo(methodOn(BooksController.class).findById(livro.getIdBook())).withSelfRel()));
        return livrosDTO;
    }

    public BooksDTO findById(Long id) {
        logger.info("Buscando um livro...");

        Books livroRetornado = booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        BooksDTO BooksDTO = Mapper.parseObject(livroRetornado, BooksDTO.class);

        //Adicionando HATEOAS
        BooksDTO.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        return BooksDTO;
    }

    public BooksDTO create(BooksDTO livro) {
        if(livro == null)
            throw new RequiredObjectIsNullException();

        logger.info("Criando um livro...");
        Books BooksCriada = Mapper.parseObject(livro, Books.class);

        Books teste = booksRepository.save(BooksCriada);

        BooksDTO BooksDTO = Mapper.parseObject(teste, BooksDTO.class);

        //Adicionando HATEOAS
        BooksDTO.add(linkTo(methodOn(BooksController.class).findById(BooksDTO.getIdBook())).withSelfRel());
        return BooksDTO;
    }

    public BooksDTO update(BooksDTO livro) {
        if(livro == null)
            throw new RequiredObjectIsNullException();

        logger.info("Atualizando um livro...");

        Books livroRecuperado = booksRepository.findById(livro.getIdBook())
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        livroRecuperado.setAutor(livro.getAutor());
        livroRecuperado.setDataLancamento(livro.getDataLancamento());
        livroRecuperado.setPreco(livro.getPreco());
        livroRecuperado.setTitulo(livro.getTitulo());

        BooksDTO BooksDTO = Mapper.parseObject(booksRepository.save(livroRecuperado), BooksDTO.class);

        //Adicionando HATEOAS
        BooksDTO.add(linkTo(methodOn(BooksController.class).findById(BooksDTO.getIdBook())).withSelfRel());
        return BooksDTO;
    }

    public void delete(Long id) {
        logger.info("Deletando um livro...");

        Books BooksRecuperada = booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        booksRepository.delete(BooksRecuperada);
    }
}
