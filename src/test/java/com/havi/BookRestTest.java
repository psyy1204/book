package com.havi;

import com.havi.domain.Book;
import com.havi.service.BookRestService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(BookRestService.class)
//테스트 대상이 되는 빈을 주입받는다
public class BookRestTest {

    @Rule
    //하나의 메서드가 끝날때마다 정의한 값으로 초기화
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private BookRestService bookRestService;

    @Autowired
    private MockRestServiceServer server;
    //MockRestServiceServer: 클라이언트와 서버 사이의 REST를 위한 객체

    @Test
    public void rest_테스트() { //요청의 응답과 기댓값 같은지 테스트
        this.server.expect(requestTo("/rest/test"))
                .andRespond(withSuccess(new ClassPathResource(
                        "/test.json", getClass()), MediaType.APPLICATION_JSON));
        Book book = this.bookRestService.getRestBook();
        assertThat(book.getTitle()).isEqualTo("테스트");
    }

    @Test
    public void rest_error_테스트() { //에러가 발생했을 경우
        this.server.expect(requestTo("/rest/test"))
                .andRespond(withServerError());
        this.thrown.expect(HttpServerErrorException.class);
                            //HTTP 500 에러발생 클래스
        this.bookRestService.getRestBook();
    }
}
