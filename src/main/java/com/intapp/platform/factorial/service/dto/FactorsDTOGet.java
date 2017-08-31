package com.intapp.platform.factorial.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * Created by maksymk on 4/7/2017.
 */
@Data
@NoArgsConstructor
public class FactorsDTOGet implements Serializable{

    private Long id;

    private Double resultScore;

    private Double typeOfClientNewExsting;

    private Double estimatedFees;

    private Double aMLRiskScoring;

    private Double financeRisk;

    private Double conficts;

    private Double isContingent;

    private Double isHighRiskCountry;

    private Double briberyConserns;

    private Double matterInvolveFinTech;

    private Double matterInvolveFinTechGovProd;

    private Double matterInvolveDecisionMak;

    private Double matterInvolveFinancial;

    private Double feeArrangementContinget;

}
