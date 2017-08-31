package com.intapp.platform.factorial.persistence.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maxim_Konovaliuk on 8/31/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "factors", schema = "public", catalog = "factors")
public class Factors implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "result_score")
    private Double resultScore;

    @Column(name = "type_client_new_exsting")
    private Double typeOfClientNewExsting;

    @Column(name = "estimated_fees")
    private Double estimatedFees;

    @Column(name = "aML_risk_scoring")
    private Double aMLRiskScoring;

    @Column(name = "finance_risk")
    private Double financeRisk;

    @Column(name = "conficts")
    private Double conficts;

    @Column(name = "is_contingent")
    private Double isContingent;

    @Column(name = "is_high_risk_country")
    private Double isHighRiskCountry;

    @Column(name = "bribery_conserns")
    private Double briberyConserns;

    @Column(name = "matter_involve_fin_tech")
    private Double matterInvolveFinTech;

    @Column(name = "matter_involve_fin_tech_gov_prod")
    private Double matterInvolveFinTechGovProd;

    @Column(name = "matter_involve_decision_mak")
    private Double matterInvolveDecisionMak;

    @Column(name = "matter_involve_financial")
    private Double matterInvolveFinancial;

    @Column(name = "fee_arrangement_continget")
    private Double feeArrangementContinget;

}
