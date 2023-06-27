package io.github.matheusreichert.worker.domain.entity;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import io.github.matheusreichert.worker.domain.dto.MetricDTO;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Data
@Measurement(name = "weather-station")
public class Metric {
    @Column
    Double gasResistance;
    @Column(name = "hdc_humidity")
    Double humidity;
    @Column(name = "hdc_temperature")
    Double temperature;
    @Column
    Double lux;
    @Column
    Double pressure;
    @Column
    Double uv;
    @Column(name = "voc_index")
    Integer vocIndex;
    @Column(timestamp = true)
    Instant timestamp;

    public Metric(@NotNull MetricDTO dto) {
        this.gasResistance = dto.gasResistance();
        this.humidity = dto.humidity();
        this.lux = dto.lux();
        this.pressure = dto.pressure();
        this.temperature = dto.temperature();
        this.uv = dto.uv();
        this.vocIndex = dto.vocIndex();
        this.timestamp = dto.timestamp();
    }
}
