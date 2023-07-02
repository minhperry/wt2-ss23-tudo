package de.tudortmund.wt2.luminary.controller;

import de.tudortmund.wt2.luminary.controller.response.ApiResponse;
import de.tudortmund.wt2.luminary.service.SparkService;
import de.tudortmund.wt2.luminary.model.spark.SparkDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/sparks")
public class SparkController implements SparkBaseController {
    private final SparkService sparkService;

    public SparkController(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @Override
    public ResponseEntity<List<SparkDto>> fetchAllSparks() {
        return new ResponseEntity<>(this.sparkService.fetchAllIdeas(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> createSpark(UserDetails authentication, String sparkContent) throws Exception {
        return new ApiResponse(sparkService.createSpark(sparkContent, authentication)).buildApiResponse();
    }

    @Override
    public ResponseEntity<ApiResponse> updateSpark(UserDetails authentication, UUID id, String newContent) throws Exception {
        return new ApiResponse(sparkService.updateSpark(id, newContent, authentication)).buildApiResponse();
    }

    @Override
    public ResponseEntity<ApiResponse> deleteSpark(UserDetails authentication, UUID id) throws Exception {
        return new ApiResponse(sparkService.deleteSpark(id, authentication)).buildApiResponse();
    }
}