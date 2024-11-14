package ru.gbdanila.timesheetrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ru.gbdanila.timesheetrest.components.RecoverTestComponent;
import ru.gbdanila.timesheetrest.service.ProjectService;

@SpringBootApplication
public class TimesheetRestApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TimesheetRestApplication.class, args);

		RecoverTestComponent recoverTestComponent = ctx.getBean(RecoverTestComponent.class);
		recoverTestComponent.throwMethod();

		ProjectService projectService = ctx.getBean(ProjectService.class);
		projectService.findById(5L);


	}

}
