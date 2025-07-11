package org.example;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
@Builder
@Getter
@Setter
public class Service {
    private int id;
    private int employeeId;
    private String nameService;
    private BigDecimal price;
    private Duration leadTime;
}
