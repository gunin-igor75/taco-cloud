package ru.gil.tacocloud.cofiguration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("taco.orders")
@Data
public class OrderProps {

    private int pageSize;
}
