package de.tudortmund.wt2.luminary.controller;

import de.tudortmund.wt2.luminary.controller.response.ApiResponse;
import de.tudortmund.wt2.luminary.service.SparkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/weigh")
public class WeightController implements WeightBaseController {

    private final SparkService sparkService;

    public WeightController(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @Override
    public ResponseEntity<ApiResponse> updateLightCount(UUID id) throws Exception {
        return new ApiResponse(sparkService.updateLightCount(id)).buildApiResponse();
    }
}
