package com.havi;

import com.havi.controller.BookController;
import com.havi.domain.Book;
import com.havi.service.BookService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.contains;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;
    //모든 의존성을 로드하는것이 아닌 BookController 관련 빈만 로드

    @MockBean
    //Mock객체는 실제 객체는 아니지만 특정 행위를 지정하여 실제 객체처럼 동작하게 만들수 있음
    private BookService bookService;

    @Test
    public void bookMvcTest() throws Exception {
        Book book = new Book("Spring Boot Book", LocalDateTime.now());

        //생성한 Book 객체를 포함하는 리스트를 반환하도록 설정
        given(bookService.getBookList()).willReturn(Collections.singletonList(book));

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("book"));
//                .andExpect(model().attributeExists("bookList"))
//                .andExpect(model().attribute("bookList",contains(book)));
    }

}
