package com.Buyogo.Buyogo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    @Column(name = "center_name")
    private String centerName;

    @NotBlank
    @Size(min = 12, max = 12)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @Column(name = "center_code")
    private String centerCode;

    @Embeddable
    @Data
    public static class Address {
        @NotBlank
        @Column(name = "detailed_address")
        private String detailedAddress;

        @NotBlank
        private String city;

        @NotBlank
        private String state;

        @NotBlank
        @Pattern(regexp = "\\d{6}")
        private String pincode;
    }

    @Embedded
    private Address address;

    @Column(name = "student_capacity")
    private Integer studentCapacity;


    @Column(name = "created_on")
    @CreationTimestamp
    private Instant createdOn;

    @Email
    @Column(name = "contact_email")
    private String contactEmail;

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    @Column(name = "contact_phone")
    private String contactPhone;


    @ElementCollection
    @CollectionTable(name = "center_courses", joinColumns = @JoinColumn(name = "center_id"))
    @Column(name = "course_offered")
    private List<String> coursesOffered;


    // if lombok is not working then you can enable this
//    public String getContactEmail() {
//        return contactEmail;
//    }
//
//    public void setContactEmail(String contactEmail) {
//        this.contactEmail = contactEmail;
//    }
//
//    public String getContactPhone() {
//        return contactPhone;
//    }
//
//    public void setContactPhone(String contactPhone) {
//        this.contactPhone = contactPhone;
//    }
//
//    public String getCenterCode() {
//        return centerCode;
//    }
//
//    public void setCenterCode(String centerCode) {
//        this.centerCode = centerCode;
//    }



}
