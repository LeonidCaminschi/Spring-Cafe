package cafe.springcafe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for adding a new cook")
public class AddCookRequest {

    @Schema(description = "Name of the cook", example = "John Cena", required = true)
    private String name;

    public AddCookRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
