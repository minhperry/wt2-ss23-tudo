package de.tudortmund.wt2.luminary.controller;

import de.tudortmund.wt2.luminary.controller.response.ApiResponse;
import de.tudortmund.wt2.luminary.model.spark.SparkDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface SparkBaseController {
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "FETCH ALL SPARKS")
    ResponseEntity<List<SparkDto>> fetchAllSparks();

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "CREATE NEW SPARK", security = @SecurityRequirement(name = "Bearer Authentication"))
    ResponseEntity<ApiResponse> createSpark(@AuthenticationPrincipal UserDetails authentication, @RequestBody String sparkContent) throws Exception;

    @PutMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "UPDATE SPARK", security = @SecurityRequirement(name = "Bearer Authentication"))
    ResponseEntity<ApiResponse> updateSpark(@AuthenticationPrincipal UserDetails authentication, @RequestParam UUID id, @RequestBody String newContent) throws Exception;

    @DeleteMapping(path = "")
    @Operation(summary = "DELETE SPARK", security = @SecurityRequirement(name = "Bearer Authentication"))
    ResponseEntity<ApiResponse> deleteSpark(@AuthenticationPrincipal UserDetails authentication, @RequestParam UUID id) throws Exception;
}