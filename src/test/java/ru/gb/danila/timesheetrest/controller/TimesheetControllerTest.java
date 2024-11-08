package ru.gb.danila.timesheetrest.controller;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestClient;
import ru.gb.danila.timesheetrest.model.Timesheet;
import ru.gb.danila.timesheetrest.repository.TimesheetRepository;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TimesheetControllerTest {
    @Data
    private static class TestBody{
        private int property = 1;
    }


    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        timesheetRepository.deleteAll();
    }

    @Test
    void findAllSuccessfully() {
        List<Timesheet> expectedList = insertTimesheets();

        webTestClient.get()
                .uri("/timesheets")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new org.springframework.core.ParameterizedTypeReference<List<Timesheet>>() {})
                .value(actualList -> {
                    for (int i = 0; i < actualList.size(); i++) {
                        Timesheet actual = actualList.get(i);
                        Timesheet expected = expectedList.get(i);
                        assertEquals(expected.getId(), actual.getId());
                        assertEqualsTimesheet(expected, actual);
                    }
                });

    }

    private List<Timesheet> insertTimesheets() {
        List<Timesheet> timesheets = List.of(
                new Timesheet(12, 1L, 1L),
                new Timesheet(10, 2L, 2L),
                new Timesheet(100, 1L, 1L)
        );

        return timesheetRepository.saveAll(timesheets);
    }

    @Test
    void getByIdSuccessfully() {
        Timesheet expected = insertTimesheet();

        webTestClient.get()
                .uri("/timesheets/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Timesheet.class)
                .value(actual -> {
                    assertEqualsTimesheet(expected, actual);
                });
    }

    @Test
    void getByIdNotFound(){
        Timesheet saved = insertTimesheet();

        webTestClient.get()
                .uri("/timesheets/" + saved.getId() + 1)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void createSuccessfully() {
        Timesheet generated = generateTimesheet();

        ResponseEntity<Timesheet> entity = getRestClient().post()
                .uri("/timesheets")
                .body(generated)
                .retrieve()
                .toEntity(Timesheet.class);

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertNotNull(Objects.requireNonNull(entity.getBody()).getId());
        assertEquals(generated, entity.getBody());
    }

    @Test
    void createWhenEmptyBodyThenBadRequest(){
        webTestClient.post()
                .uri("/timesheets")
                .bodyValue(new TestBody())
                .exchange()
                .expectStatus().isBadRequest();

    }

    @Test
    void updateSuccessfully() {
        Timesheet saved = insertTimesheet();
        saved.setMinutes(142);

        webTestClient.put()
                .uri("/timesheets/" + saved.getId())
                .bodyValue(saved)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Timesheet.class)
                .value(actual -> assertEqualsTimesheet(saved, actual));

    }

    @Test
    void deleteSuccessfully() {
        Timesheet expected = insertTimesheet();

        ResponseEntity<Void> bodilessEntity = getRestClient().delete()
                .uri("/timesheets/" + expected.getId())
                .retrieve()
                .toBodilessEntity();

        assertEquals(bodilessEntity.getStatusCode(), HttpStatus.OK);
        assertFalse(timesheetRepository.existsById(expected.getId()));
    }

    @Test
    void deleteWithBadRequest(){
        Timesheet expected = insertTimesheet();

        webTestClient.delete()
                .uri("/timesheets/" + expected.getId() + 1)
                .exchange()
                .expectStatus().isBadRequest();

    }



    private Timesheet insertTimesheet() {
        return timesheetRepository.save(generateTimesheet());
    }

    private Timesheet generateTimesheet() {
        return new Timesheet(
            12,1L,2L
        );
    }

    private RestClient getRestClient() {
        return RestClient.create(String.format("http://localhost:%s", port));
    }


    private void assertEqualsTimesheet(Timesheet expected, Timesheet actual){
        assertEquals(expected.getMinutes(), actual.getMinutes());
        assertEquals(expected.getProjectId(), actual.getProjectId());
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
    }
}