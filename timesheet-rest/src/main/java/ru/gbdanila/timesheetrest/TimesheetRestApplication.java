package ru.gbdanila.timesheetrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gbdanila.timesheetrest.service.ProjectService;

@SpringBootApplication
public class TimesheetRestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TimesheetRestApplication.class, args);

		ProjectService projectService = ctx.getBean(ProjectService.class);
		projectService.findById(1L);
	}

}
