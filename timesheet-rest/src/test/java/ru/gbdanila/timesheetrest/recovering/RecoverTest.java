package ru.gbdanila.timesheetrest.recovering;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RecoverTest {
    @Autowired
    private RecoverTestComponent recoverTestComponent;

    @Test
    public void shouldBeNotThrow(){
        recoverTestComponent.notThrowNoSearchElementException();
    }

    @Test
    public void shouldThrow(){
        boolean throwTrue = false;
        try{
            recoverTestComponent.throwIllegalArgumentException();
        }catch (IllegalArgumentException e){
            throwTrue = true;
        }

        assertTrue(throwTrue);
    }
}
