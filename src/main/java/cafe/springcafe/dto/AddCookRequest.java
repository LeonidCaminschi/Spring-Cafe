package cafe.springcafe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for adding a new cook")
public record AddCookRequest(
    @Schema(description = "Name of the cook", example = "John Cena", required = true)
    String name
) {}