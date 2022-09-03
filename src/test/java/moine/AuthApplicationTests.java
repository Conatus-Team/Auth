package moine;

import lombok.extern.slf4j.Slf4j;
import moine.domain.middle.Url;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ANNOTATED)
@SpringBootTest
@Slf4j
public class AuthApplicationTests {

    @Test
    public void test() {

        System.out.println(Url.MIDDLE.getUrl());
    }
}
