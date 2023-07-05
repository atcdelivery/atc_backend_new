package com.atcdilivery.spring.jwt.mongodb.payload.directResponse.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessHours{
    @JsonProperty("WED")
    public WED wED;
    @JsonProperty("THU") 
    public THU tHU;
    @JsonProperty("TUE") 
    public TUE tUE;
    @JsonProperty("MON") 
    public MON mON;
    @JsonProperty("FRI") 
    public FRI fRI;
    @JsonProperty("SAT") 
    public SAT sAT;
}
