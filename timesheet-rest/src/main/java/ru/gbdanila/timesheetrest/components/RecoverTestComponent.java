package ru.gbdanila.timesheetrest.components;

import org.springframework.stereotype.Component;
import ru.gbdanila.recover.Recover;

@Component
public class RecoverTestComponent{
    @Recover
    public void throwMethod(){
        throw new IllegalArgumentException();
    }
}
