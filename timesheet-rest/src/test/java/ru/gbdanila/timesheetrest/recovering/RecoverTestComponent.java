package ru.gbdanila.timesheetrest.recovering;

import org.springframework.stereotype.Component;
import ru.gbdanila.recover.Recover;

import java.util.NoSuchElementException;

@Component
public class RecoverTestComponent{
    @Recover
    public void notThrowNoSearchElementException(){
        throw new NoSuchElementException();
    }

    /*
    * no-recover-for defined in properties
     */
    @Recover
    public void throwIllegalArgumentException(){
        throw new IllegalArgumentException();
    }
}
