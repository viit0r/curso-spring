package unittest.mockito.service;

import com.viit0r.cursospring.dto.v1.PersonDTO;
import com.viit0r.cursospring.model.Person;
import com.viit0r.cursospring.repository.PersonRepository;
import com.viit0r.cursospring.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import unittest.mapper.mock.MockPerson;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        when(personRepository.findById(1L))     // Quando buscar person com ID 1
           .thenReturn(Optional.of(person));    // Deve retornar um optional de person

        PersonDTO resultado = service.findById(1L);

        assertNotNull(resultado);
        assertNotNull(resultado.getIdPerson());
        assertTrue(resultado.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", resultado.getPrimeiroNome());
        assertEquals("Last Name Test1", resultado.getUltimoNome());
        assertEquals("Addres Test1", resultado.getEndereco());
        assertEquals("Female", resultado.getGenero());
    }
}
