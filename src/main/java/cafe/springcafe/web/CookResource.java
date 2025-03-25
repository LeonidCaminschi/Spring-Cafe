package cafe.springcafe.web;

import cafe.springcafe.domain.Cook;
import cafe.springcafe.dto.AddCookRequest;
import cafe.springcafe.service.CookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController("/cook")
public class CookResource {

    private final CookService cookService;

    public CookResource(CookService cookService) {
        this.cookService = cookService;
    }

    @Operation(summary = "Add a new cook", description = "Adds a new cook to the list with only their name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added a mew cook")
    })
    @PostMapping("/add")
    public ResponseEntity<String> addCook(@RequestBody AddCookRequest request) {
        String cookName;
        try {
            cookName = request.getName();
            cookService.save(new Cook(cookName));
        } catch (Exception e) {
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body(e.getMessage());
        }

        return ResponseEntity
                .status(OK)
                .body(cookName + " cook, was added succesfuly");
    }

}
