package cafe.springcafe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for creating an order")
public record CreateOrderRequest(
    @Schema(description = "Name of the dish to order", example = "Margherita Pizza")
    String dish
) {}