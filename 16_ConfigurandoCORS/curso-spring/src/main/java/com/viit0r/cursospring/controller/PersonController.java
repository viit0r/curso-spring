package com.viit0r.cursospring.controller;

import com.viit0r.cursospring.dto.v1.PersonDTO;
import com.viit0r.cursospring.service.PersonService;
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

//@CrossOrigin
/* Esta forma habilita CORS para o Controller inteiro.
Caso eu especifique um IP e/ou host, somente este IP/host conseguirá acessar a nossa API */
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints para gerenciamento das pessoas do sistema")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Retorna todas as pessoas", description = "Retorna todas as pessoas registradas no sistema",
    tags = {"People"},
    responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = PersonDTO.class
                                    )
                            )
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080") /* Esta forma habilita CORS somente para este endpoint. */
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Retorna uma pessoa", description = "Retorna uma das pessoas registradas no sistema",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:8080") /* Esta forma habilita CORS somente para este endpoint. */
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Adiciona uma pessoa", description = "Registra uma nova pessoa no sistema",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public PersonDTO create(@RequestBody PersonDTO person) {
        return personService.create(person);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Atualiza uma pessoa", description = "Atualiza uma pessoa existente no sistema",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Atualizado", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    //ANOTAÇÃO DO SWAGGER PARA DOC
    @Operation(summary = "Deleta uma pessoa", description = "Deleta uma pessoa existente no sistema",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
