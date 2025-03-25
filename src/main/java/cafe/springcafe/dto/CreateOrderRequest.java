package cafe.springcafe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for creating an order")
public class CreateOrderRequest {

    @Schema(description = "Name of the dish to order", example = "Margherita Pizza", required = true)
    private String dish;

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }
}