package br.com.vitor.cursospring.service;

import br.com.vitor.cursospring.controller.PessoaController;
import br.com.vitor.cursospring.data.vo.v1.PessoaVO;
import br.com.vitor.cursospring.exception.ResourceNotFoundException;
import br.com.vitor.cursospring.mapper.DozerMapper;
import br.com.vitor.cursospring.model.Pessoa;
import br.com.vitor.cursospring.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<PessoaVO> findAll() {
        List<PessoaVO> pessoasVO = DozerMapper.parseListsObjects(repository.findAll(), PessoaVO.class);

        pessoasVO.forEach(p -> p.add(linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel()));

        return pessoasVO;
    }
    public PessoaVO findById (Long id) {
        Pessoa pessoaRecuperada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros para esse ID!"));

        PessoaVO pessoaVO = DozerMapper.parseObject(pessoaRecuperada, PessoaVO.class);

        //HATEOAS
        pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(id)).withSelfRel());

        return pessoaVO;
    }

    public PessoaVO create(PessoaVO pessoa) {
        Pessoa pessoaCriada = DozerMapper.parseObject(pessoa, Pessoa.class);

        PessoaVO pessoaVO = DozerMapper.parseObject(repository.save(pessoaCriada), PessoaVO.class);

        //HATEOAS
        pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(pessoaVO.getKey())).withSelfRel());

        return pessoaVO;
    }

    public PessoaVO update(PessoaVO pessoa) {
        Pessoa pessoaRecuperada = repository.findById(pessoa.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros para esse ID!"));

        pessoaRecuperada.setNome(pessoa.getNome());
        pessoaRecuperada.setSobrenome(pessoa.getSobrenome());
        pessoaRecuperada.setEndereco(pessoa.getEndereco());
        pessoaRecuperada.setGenero(pessoa.getGenero());

        PessoaVO pessoaVO = DozerMapper.parseObject(repository.save(pessoaRecuperada), PessoaVO.class);

        pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(pessoaVO.getKey())).withSelfRel());

        return pessoaVO;
    }

    public void delete(Long id) {
        Pessoa pessoaRecuperada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros para esse ID!"));

        repository.delete(pessoaRecuperada);
    }
}
