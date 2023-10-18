package com.viit0r.cursospring.controller;

import com.viit0r.cursospring.dto.v1.BooksDTO;
import com.viit0r.cursospring.dto.v1.PersonDTO;
import com.viit0r.cursospring.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints para gerenciamento dos livros do sistema")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Retorna todos os livros", description = "Retorna todos os livros registrados no sistema",
    tags = {"Books"},
    responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = BooksDTO.class
                                    )
                            )
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public List<BooksDTO> findAll() {
        return booksService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Retorna um livro", description = "Retorna um dos livros registrados no sistema",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BooksDTO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public BooksDTO findById(@PathVariable(value = "id") Long id) {
        return booksService.findById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Adiciona um livro", description = "Registra um novo livro no sistema",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BooksDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public BooksDTO create(@RequestBody BooksDTO person) {
        return booksService.create(person);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Atualiza um livro", description = "Atualiza um livro existente no sistema",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Atualizado", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BooksDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public BooksDTO update(@RequestBody BooksDTO person) {
        return booksService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Deleta um livro", description = "Deleta um livro existente no sistema",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        booksService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
