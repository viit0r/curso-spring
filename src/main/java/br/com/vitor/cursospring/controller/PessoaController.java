package br.com.vitor.cursospring.controller;

import br.com.vitor.cursospring.data.vo.v1.PessoaVO;
import br.com.vitor.cursospring.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa/v1")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PessoaVO findById(@PathVariable(value = "id") Long id) {
        return pessoaService.findById(id);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PessoaVO> findAll() {
        return pessoaService.findAll();
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PessoaVO create(@RequestBody PessoaVO pessoa) {
        return pessoaService.create(pessoa);
    }

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PessoaVO update(@RequestBody PessoaVO pessoa) {
        return pessoaService.update(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        pessoaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
