package io.github.matheusreichert.worker.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import io.github.matheusreichert.worker.domain.dto.MetricDTO;
import io.github.matheusreichert.worker.domain.entity.Metric;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueToDB {
    private final InfluxDBClient client = InfluxClient.getClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Value("${bucket.name}") private String bucket;
    @Value("${org.name}") private String org;
    static{
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    @RabbitListener(queues = "${queue.name}")
    public void listenMetricsAndSaveToDB(@Payload String payload) throws JsonProcessingException {
        MetricDTO dto = QueueToDB.mapper.readValue(payload, MetricDTO.class);
        Metric metric = new Metric(dto);
        this.saveMetric(metric);
    }

    private void saveMetric(Metric metric) {

        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeMeasurement(bucket, org, WritePrecision.MS, metric);
    }
}
