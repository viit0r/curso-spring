package br.com.vitor.cursospring.service;

import br.com.vitor.cursospring.data.vo.v1.PessoaVO;
import br.com.vitor.cursospring.exception.ResourceNotFoundException;
import br.com.vitor.cursospring.mapper.Mapper;
import br.com.vitor.cursospring.model.Pessoa;
import br.com.vitor.cursospring.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<PessoaVO> findAll() {
        return Mapper.parseListsObjects(repository.findAll(), PessoaVO.class);
    }
    public PessoaVO findById (Long id) {
        Pessoa pessoaRecuperada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros para esse ID!"));

        return Mapper.parseObject(pessoaRecuperada, PessoaVO.class);
    }

    public PessoaVO create(PessoaVO pessoa) {
        Pessoa pessoaCriada = Mapper.parseObject(pessoa, Pessoa.class);

        return Mapper.parseObject(repository.save(pessoaCriada), PessoaVO.class);
    }

    public PessoaVO update(PessoaVO pessoa) {
        Pessoa pessoaRecuperada = repository.findById(pessoa.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros para esse ID!"));

        pessoaRecuperada.setNome(pessoa.getNome());
        pessoaRecuperada.setSobrenome(pessoa.getSobrenome());
        pessoaRecuperada.setEndereco(pessoa.getEndereco());
        pessoaRecuperada.setGenero(pessoa.getGenero());

        return Mapper.parseObject(repository.save(pessoaRecuperada), PessoaVO.class);
    }

    public void delete(Long id) {
        Pessoa pessoaRecuperada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros para esse ID!"));

        repository.delete(pessoaRecuperada);
    }
}
