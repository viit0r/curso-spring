package unittest.mockito.service;

import com.viit0r.cursospring.dto.v1.BooksDTO;
import com.viit0r.cursospring.exception.RequiredObjectIsNullException;
import com.viit0r.cursospring.model.Books;
import com.viit0r.cursospring.repository.BooksRepository;
import com.viit0r.cursospring.service.BooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import unittest.mapper.mock.MockBook;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BooksService service;

    @Mock
    BooksRepository bookRepository;

    @BeforeEach
    void setUp() throws Exception {
        input = new MockBook();
        MockitoAnnotations.openMocks(this); // Abre os mocks para utilização
    }

    @Test
    void testFindById() {
        Books book = input.mockEntity(1);
        when(bookRepository.findById(1L))         // Quando buscar book com ID 1
                .thenReturn(Optional.of(book));   // Deve retornar um optional de book

        BooksDTO resultado = service.findById(1L);

        assertNotNull(resultado);
        assertNotNull(resultado.getIdBooks());
        assertTrue(resultado.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author1", resultado.getAutor());
        assertEquals("Some Title1", resultado.getTitulo());
        assertEquals(BigDecimal.valueOf(25D), resultado.getPreco());
        assertNotNull(resultado.getDataLancamento());
    }

    @Test
    void testCreate() {
        Books bookRecebida = input.mockEntity(1);

        Books bookCriada = bookRecebida;
        bookCriada.setId(1L);

        BooksDTO bookDTORecebido = input.mockDTO(1);
        bookDTORecebido.setIdBooks(1L);

        when(bookRepository.save(bookRecebida)) // Quando salvar a bookRecebida
                .thenReturn(bookCriada);          // Deve retornar a bookCriada

        BooksDTO resultado = service.create(bookDTORecebido);
        assertNotNull(resultado);
        assertNotNull(resultado.getIdBooks());
        assertNotNull(resultado.getLinks());
        assertTrue(resultado.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author1", resultado.getAutor());
        assertEquals("Some Title1", resultado.getTitulo());
        assertEquals(BigDecimal.valueOf(25D), resultado.getPreco());
        assertNotNull(resultado.getDataLancamento());
    }

    @Test
    void testUpdate() {
        Books bookRecebida = input.mockEntity(1);
        bookRecebida.setId(1L);

        Books bookCriada = bookRecebida;
        bookCriada.setId(1L);

        BooksDTO bookDTORecebido = input.mockDTO(1);
        bookDTORecebido.setIdBooks(1L);

        when(bookRepository.findById(1L))                 // Quando buscar book com ID 1
                .thenReturn(Optional.of(bookRecebida));   // Deve retornar um optional de book

        when(bookRepository.save(bookRecebida)) // Quando salvar a bookRecebida
                .thenReturn(bookCriada);          // Deve retornar a bookCriada

        BooksDTO resultado = service.update(bookDTORecebido);

        assertNotNull(resultado);
        assertNotNull(resultado.getIdBooks());
        assertNotNull(resultado.getLinks());
        assertTrue(resultado.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author1", resultado.getAutor());
        assertEquals("Some Title1", resultado.getTitulo());
        assertEquals(BigDecimal.valueOf(25D), resultado.getPreco());
        assertNotNull(resultado.getDataLancamento());
    }

    @Test
    void testDelete() {
        Books book = input.mockEntity(1);
        when(bookRepository.findById(1L))         // Quando buscar book com ID 1
                .thenReturn(Optional.of(book));   // Deve retornar um optional de book

        service.delete(1L);
    }

    @Test
    void testCreateComLivroNulo() {
        String sMensagemEsperada = "Não é permitido persistir um objeto nulo!";

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String sMensagemRetornada = exception.getMessage();

        assertTrue(sMensagemRetornada.contains(sMensagemEsperada));
    }

    @Test
    void testUpdateComBooksNulo() {
        String sMensagemEsperada = "Não é permitido persistir um objeto nulo!";

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String sMensagemRetornada = exception.getMessage();

        assertTrue(sMensagemRetornada.contains(sMensagemEsperada));
    }

    @Test
    void testFindAll() {
        List<Books> listLivros = input.mockEntityList();

        when(bookRepository.findAll())         // Quando buscar todos os livros
                .thenReturn(listLivros);   // Deve retornar o mock com todos os livros

        List<BooksDTO> resultado = service.findAll();

        assertNotNull(resultado);
        assertEquals(14, resultado.size());

        BooksDTO primeiroLivro = resultado.get(1);
        assertNotNull(primeiroLivro);
        assertNotNull(primeiroLivro.getIdBooks());
        assertNotNull(primeiroLivro.getLinks());
        assertTrue(resultado.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author1", primeiroLivro.getAutor());
        assertEquals("Some Title1", primeiroLivro.getTitulo());
        assertEquals(BigDecimal.valueOf(25D), primeiroLivro.getPreco());
        assertNotNull(primeiroLivro.getDataLancamento());

        BooksDTO quartoLivro = resultado.get(4);
        assertNotNull(quartoLivro);
        assertNotNull(quartoLivro.getIdBooks());
        assertNotNull(quartoLivro.getLinks());
        assertTrue(resultado.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author4", quartoLivro.getAutor());
        assertEquals("Some Title4", quartoLivro.getTitulo());
        assertEquals(BigDecimal.valueOf(25D), quartoLivro.getPreco());
        assertNotNull(quartoLivro.getDataLancamento());

        BooksDTO setimoLivro = resultado.get(7);
        assertNotNull(setimoLivro);
        assertNotNull(setimoLivro.getIdBooks());
        assertNotNull(setimoLivro.getLinks());
        assertTrue(resultado.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author7", setimoLivro.getAutor());
        assertEquals("Some Title7", setimoLivro.getTitulo());
        assertEquals(BigDecimal.valueOf(25D), setimoLivro.getPreco());
        assertNotNull(setimoLivro.getDataLancamento());
    }
}
