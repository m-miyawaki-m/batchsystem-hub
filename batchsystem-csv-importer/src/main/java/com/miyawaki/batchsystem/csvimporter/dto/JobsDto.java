package com.miyawaki.batchsystem.csvimporter.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobsDto {
    private String jobId;
    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;
}