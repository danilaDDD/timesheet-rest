package ru.gbdanila.timesheetrest.recovering;

import org.springframework.stereotype.Component;
import ru.gbdanila.recover.Recover;

@Component
public class RecoverTestComponent{
    @Recover
    public void throwIllegalArgumentException(){
        throw new IllegalArgumentException();
    }
}
