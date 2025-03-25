package cafe.springcafe.web;

import cafe.springcafe.domain.Cook;
import cafe.springcafe.dto.AddCookRequest;
import cafe.springcafe.service.CookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController("/cook")
public class CookResource {

    public static final String COOK_WAS_ADDED_SUCCESSFULLY = "cook, was added successfully";
    private final Logger LOG = LoggerFactory.getLogger(CookResource.class);

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
        try {
            var cookName = request.name();
            cookService.save(new Cook(cookName));

            return ResponseEntity
                    .status(OK)
                    .body(cookName + " " + COOK_WAS_ADDED_SUCCESSFULLY);

        } catch (Exception e) {
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
