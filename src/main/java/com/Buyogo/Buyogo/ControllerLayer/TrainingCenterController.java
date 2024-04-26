package com.Buyogo.Buyogo.ControllerLayer;

import com.Buyogo.Buyogo.Model.TrainingCenter;
import com.Buyogo.Buyogo.ServiceLayer.TrainingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/training")
public class TrainingCenterController {

    @Autowired
    private TrainingCenterService trainingCenterService;

     //Endpoint to create a new training center.
    @PostMapping("/createCenter")
    public ResponseEntity<String> createCenter(@RequestBody TrainingCenter trainingCenter) {
        try {
            // for validation check
            trainingCenterService.addTraningCenterToDB(trainingCenter);
            return ResponseEntity.ok("Newly Saved Training Center");
        } catch (RuntimeException e) {
            // for error handle
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    /**
     * Endpoint to get all the list of training center
     * Also you can filter data according to your requiremtn
     * user filter in param and filter according to name of the center,city,state,pincode or courses
     */
    @GetMapping("/getAllTrainingCenter")
    public ResponseEntity<List<TrainingCenter>> getAllTrainingCenters(@RequestParam(value = "filter", required = false) String filter) {
        try {
            // for validation checking
            List<TrainingCenter> trainingCenters = trainingCenterService.getAllList();

            if (filter != null && !filter.isEmpty()) {
                // Split the filter string by the colon to get the filter criteria
                String[] filterParts = filter.split(":");
                if (filterParts.length == 2) {
                    String filterField = filterParts[0];
                    String filterValue = filterParts[1];

                    // Filter the list based on the provided criteria
                    trainingCenters = trainingCenters.stream()
                            .filter(tc -> {
                                switch (filterField) {
                                    case "name":
                                        return tc.getCenterName().equalsIgnoreCase(filterValue);
                                    case "city":
                                        return tc.getAddress().getCity().equalsIgnoreCase(filterValue);
                                    case "state":
                                        return tc.getAddress().getState().equalsIgnoreCase(filterValue);
                                    case "pincode":
                                        return tc.getAddress().getPincode().equalsIgnoreCase(filterValue);
                                    case "courses":
                                        return tc.getCoursesOffered().contains(filterValue);
                                    default:
                                        return true;
                                }
                            })
                            .collect(Collectors.toList());
                }
            }

            return ResponseEntity.ok(trainingCenters);
        } catch (Exception e) {
            // for error handle or any bad request
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
