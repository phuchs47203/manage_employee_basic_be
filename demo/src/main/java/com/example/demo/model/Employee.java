package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String fullNameString;
    private String emailString;
    private String departmentString;
    private String positionString;
    private String addressString;
    private String ProfilePictureString;
    private String file;
    private String timeStamp;
    private String imageName;
}
