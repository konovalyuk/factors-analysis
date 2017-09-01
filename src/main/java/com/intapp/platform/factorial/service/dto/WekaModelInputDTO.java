package com.intapp.platform.factorial.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WekaModelInputDTO {
    private Double clientType;
    private Double estimatedFeeScore;
    private Double amlRiskScore;
    private Double financeRiskScore;
    private Double conflictRiskScore;
    private Double isContingent;
    private Double isHighRiskCountry;
    private Double isBribery;
    private Double isMilitaryActivities;
    private Double isGrant;
    private Double isCompeteticeDecision;
    private Double isFederal;
    private Double isFeeArrangementContingent;
}
