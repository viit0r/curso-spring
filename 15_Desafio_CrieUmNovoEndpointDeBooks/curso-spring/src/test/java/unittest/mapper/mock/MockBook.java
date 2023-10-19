package unittest.mapper.mock;

import com.viit0r.cursospring.dto.v1.BooksDTO;
import com.viit0r.cursospring.model.Books;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {


    public Books mockEntity() {
        return mockEntity(0);
    }
    
    public BooksDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<Books> mockEntityList() {
        List<Books> livros = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            livros.add(mockEntity(i));
        }
        return livros;
    }

    public List<BooksDTO> mockDTOList() {
        List<BooksDTO> livros = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            livros.add(mockDTO(i));
        }
        return livros;
    }
    
    public Books mockEntity(Integer number) {
        Books livro = new Books();
        livro.setId(number.longValue());
        livro.setAutor("Some Author" + number);
        livro.setDataLancamento(new Date());
        livro.setPreco(BigDecimal.valueOf(25D));
        livro.setTitulo("Some Title" + number);
        return livro;
    }

    public BooksDTO mockDTO(Integer number) {
        BooksDTO livro = new BooksDTO();
        livro.setIdBooks(number.longValue());
        livro.setAutor("Some Author" + number);
        livro.setDataLancamento(new Date());
        livro.setPreco(BigDecimal.valueOf(25D));
        livro.setTitulo("Some Title" + number);
        return livro;
    }

}
