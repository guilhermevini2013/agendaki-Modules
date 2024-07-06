package com.agendaki.financially.models.preuser;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SignatureSpecification(LocalDate date, BigDecimal price, String description) {
}
