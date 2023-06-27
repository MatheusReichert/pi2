package io.github.matheusreichert.worker;

import io.github.matheusreichert.worker.domain.dto.MetricDTO;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class WorkerWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkerWsApplication.class, args);
	}

}
