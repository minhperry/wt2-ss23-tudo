package de.tudortmund.wt2.luminary.controller;

import de.tudortmund.wt2.luminary.controller.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface WeightBaseController {
    @PutMapping(path = "",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "UPDATE LIGHT COUNT")
    ResponseEntity<ApiResponse> updateLightCount(@RequestParam UUID id) throws Exception;
}
