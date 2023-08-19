package unittest.mockito.service;

import com.viit0r.cursospring.dto.v1.PersonDTO;
import com.viit0r.cursospring.exception.RequiredObjectIsNullException;
import com.viit0r.cursospring.model.Person;
import com.viit0r.cursospring.repository.PersonRepository;
import com.viit0r.cursospring.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import unittest.mapper.mock.MockPerson;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUp() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this); // Abre os mocks para utilização
    }

    @Test
    void testFindById() {
        Person person = input.mockEntity(1);
        when(personRepository.findById(1L))         // Quando buscar person com ID 1
                .thenReturn(Optional.of(person));   // Deve retornar um optional de person

        PersonDTO resultado = service.findById(1L);

        assertNotNull(resultado);
        assertNotNull(resultado.getIdPerson());
        assertTrue(resultado.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", resultado.getPrimeiroNome());
        assertEquals("Last Name Test1", resultado.getUltimoNome());
        assertEquals("Addres Test1", resultado.getEndereco());
        assertEquals("Female", resultado.getGenero());
    }

    @Test
    void testCreate() {
        Person personRecebida = input.mockEntity(1);

        Person personCriada = personRecebida;
        personCriada.setId(1L);

        PersonDTO personDTORecebido = input.mockDTO(1);
        personDTORecebido.setIdPerson(1L);

        when(personRepository.save(personRecebida)) // Quando salvar a personRecebida
                .thenReturn(personCriada);          // Deve retornar a personCriada

        PersonDTO resultado = service.create(personDTORecebido);
        assertNotNull(resultado);
        assertNotNull(resultado.getIdPerson());
        assertNotNull(resultado.getLinks());
        assertTrue(resultado.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", resultado.getPrimeiroNome());
        assertEquals("Last Name Test1", resultado.getUltimoNome());
        assertEquals("Addres Test1", resultado.getEndereco());
        assertEquals("Female", resultado.getGenero());
    }

    @Test
    void testUpdate() {
        Person personRecebida = input.mockEntity(1);
        personRecebida.setId(1L);

        Person personCriada = personRecebida;
        personCriada.setId(1L);

        PersonDTO personDTORecebido = input.mockDTO(1);
        personDTORecebido.setIdPerson(1L);

        when(personRepository.findById(1L))                 // Quando buscar person com ID 1
                .thenReturn(Optional.of(personRecebida));   // Deve retornar um optional de person

        when(personRepository.save(personRecebida)) // Quando salvar a personRecebida
                .thenReturn(personCriada);          // Deve retornar a personCriada

        PersonDTO resultado = service.update(personDTORecebido);

        assertNotNull(resultado);
        assertNotNull(resultado.getIdPerson());
        assertNotNull(resultado.getLinks());
        assertTrue(resultado.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", resultado.getPrimeiroNome());
        assertEquals("Last Name Test1", resultado.getUltimoNome());
        assertEquals("Addres Test1", resultado.getEndereco());
        assertEquals("Female", resultado.getGenero());
    }

    @Test
    void testDelete() {
        Person person = input.mockEntity(1);
        when(personRepository.findById(1L))         // Quando buscar person com ID 1
                .thenReturn(Optional.of(person));   // Deve retornar um optional de person

        service.delete(1L);
    }

    @Test
    void testCreateComPersonNulo() {
        String sMensagemEsperada = "Não é permitido persistir um objeto nulo!";

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String sMensagemRetornada = exception.getMessage();

        assertTrue(sMensagemRetornada.contains(sMensagemEsperada));
    }

    @Test
    void testUpdateComPersonNulo() {
        String sMensagemEsperada = "Não é permitido persistir um objeto nulo!";

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String sMensagemRetornada = exception.getMessage();

        assertTrue(sMensagemRetornada.contains(sMensagemEsperada));
    }
}
