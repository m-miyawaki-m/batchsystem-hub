package com.miyawaki.batchsystem.csvimporter.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobsDto {
    private String jobId;
    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;

    public JobsDto(String jobId, String jobTitle, Integer minSalary, Integer maxSalary) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public String getJobId() {
        return jobId;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    public String getJobTitle() {
        return jobTitle;
    }
    
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public Integer getMinSalary() {
        return minSalary;
    }
    
    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }
    
    public Integer getMaxSalary() {
        return maxSalary;
    }
    
    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }
}