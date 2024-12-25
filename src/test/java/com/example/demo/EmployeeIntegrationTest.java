package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class EmployeeIntegrationTest {

    @Container
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:9.1.0")
            .withDatabaseName("testdb")
            .withUsername("root")
            .withPassword("root");

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Transactional
    void testCreateEmployee() {
        Employee employee = Employee.builder()
                .firstName("Guru")
                .lastName("Busetti")
                .email("GuruB" + System.currentTimeMillis() + "@example.com")
                .salary(75000.0)
                .department("Engineering")
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotNull();
    }
}
