package controller;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.BookService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    private MockMvc mockMvc;
    private BookService bookService;

    @BeforeEach
    void setup() {
        bookService = mock(BookService.class);
        BookController bookController = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testAddBook_success() throws Exception {
        doNothing().when(bookService).addBook(any(Book.class));

        String json = """
                {
                    "bookId": 1,
                    "title": "Atomic Habits",
                    "author": "James Clear",
                    "isbn": 1234,
                    "copies": 5,
                    "available": true
                }
                """;

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Book added successfully!"));

        verify(bookService).addBook(any(Book.class));
    }

    @Test
    void testGetAllBooks_success() throws Exception {
        List<Book> books = List.of(new Book(1, "Atomic Habits", "James Clear", 1234, 5, true));
        when(bookService.getAllBook()).thenReturn(books);

        mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Atomic Habits"));
    }

    @Test
    void testGetBookById_success() throws Exception {
        Book book = new Book(1, "Atomic Habits", "James Clear", 1234, 5, true);
        when(bookService.getBookById(1)).thenReturn(book);

        mockMvc.perform(get("/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Atomic Habits"));
    }

    @Test
    void testUpdateBook_success() throws Exception {
        doNothing().when(bookService).updateBook(any(Book.class));

        String json = """
                {
                    "title": "Atomic Habits",
                    "author": "James Clear",
                    "isbn": 1234,
                    "copies": 10,
                    "available": true
                }
                """;

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Book updated successfully!"));

        verify(bookService).updateBook(any(Book.class));
    }

    @Test
    void testDeleteBook_success() throws Exception {
        doNothing().when(bookService).deleteBook(1);

        mockMvc.perform(delete("/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted successfully!"));

        verify(bookService).deleteBook(1);
    }
}
