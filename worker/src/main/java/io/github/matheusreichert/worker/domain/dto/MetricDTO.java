package io.github.matheusreichert.worker.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.matheusreichert.worker.domain.entity.Metric;
import lombok.Builder;

import java.time.Instant;

@Builder
public record MetricDTO(@JsonProperty("gas_resistance") Double gasResistance,
                        @JsonProperty("hdc_humidity") Double humidity,
                        @JsonProperty("lux") Double lux,
                        @JsonProperty("pressure") Double pressure,
                        @JsonProperty("hdc_temperature") Double temperature,
                        @JsonProperty("uv") Double uv,
                        @JsonProperty("voc_index") Integer vocIndex,
                        @JsonProperty("timestamp") Instant timestamp) {
    public MetricDTO(Metric entity) {
        this(entity.getGasResistance(), entity.getHumidity(),
             entity.getLux(), entity.getPressure(), entity.getTemperature(), entity.getUv(), entity.getVocIndex(),
             entity.getTimestamp());
    }
}
