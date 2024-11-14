package ru.gbdanila.timesheetrest.recovering;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecoverTest {
    @Autowired
    private RecoverTestComponent recoverTestComponent;

    @Test
    public void shouldBeNotThrow(){
        recoverTestComponent.throwIllegalArgumentException();
    }
}
