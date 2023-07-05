package com.atcdilivery.spring.jwt.mongodb.payload.directRequest.trackorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Status {
    @JsonProperty("Status")
    public String status;
    @JsonProperty("StatusDateTime")
    public Date statusDateTime;
    @JsonProperty("StatusType")
    public String statusType;
    @JsonProperty("StatusLocation")
    public String statusLocation;
    @JsonProperty("Instructions")
    public String instructions;
}
