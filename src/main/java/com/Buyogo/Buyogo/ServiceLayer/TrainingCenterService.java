package com.Buyogo.Buyogo.ServiceLayer;

import com.Buyogo.Buyogo.Model.TrainingCenter;
import com.Buyogo.Buyogo.Repository.TrainingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingCenterService {


    @Autowired
    private TrainingCenterRepository trainingCenterRepository;


    public TrainingCenter addTraningCenterToDB(TrainingCenter trainingCenter) {
        // Check if the center code is valid (12 characters)
        if (trainingCenter.getCenterCode().length() != 12) {
            throw new RuntimeException("Center code must be 12 characters long");
        }

        // Check if the email is already in use
        if (trainingCenterRepository.findByContactEmail(trainingCenter.getContactEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        // Check if the phone number is already in use
        if (trainingCenterRepository.findByContactPhone(trainingCenter.getContactPhone()) != null) {
            throw new RuntimeException("Phone number already exists");
        }

        return trainingCenterRepository.save(trainingCenter);
    }

    // logic to return all the training center for database
    public List<TrainingCenter> getAllList(){
        return trainingCenterRepository.findAll();
    }

}
