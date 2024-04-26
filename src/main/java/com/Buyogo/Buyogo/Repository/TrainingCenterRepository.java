package com.Buyogo.Buyogo.Repository;

import com.Buyogo.Buyogo.Model.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {
    TrainingCenter findByContactEmail(String contactEmail);
    TrainingCenter findByContactPhone(String contactPhone);
}
