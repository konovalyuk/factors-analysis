function gather_neural_data() {
    var data = {}

    var clientType = document.getElementById("clientType").value
    var estimatedFeeScore = document.getElementById("estimatedFeeScore").value
    var amlRiskScore = document.getElementById("amlRiskScore").value
    var financeRiskScore = document.getElementById("financeRiskScore").value
    var conflictRiskScore = document.getElementById("conflictRiskScore").value
    var isContingent = document.getElementById("isContingent").value
    var isHighRiskCountry = document.getElementById("isHighRiskCountry").value
    var isBribery = document.getElementById("isBribery").value
    var isMilitaryActivities = document.getElementById("isMilitaryActivities").value
    var isGrant = document.getElementById("isGrant").value
    var isCompeteticeDecision = document.getElementById("isCompeteticeDecision").value
    var isFederal = document.getElementById("isFederal").value
    var isFeeArrangementContingent = document.getElementById("isFeeArrangementContingent").value
    
    var data = {
        "clientType": clientType,
        "estimatedFeeScore": estimatedFeeScore,
        "amlRiskScore": amlRiskScore,
        "financeRiskScore": financeRiskScore,
        "conflictRiskScore": conflictRiskScore,
        "isContingent": isContingent,
        "isHighRiskCountry": isHighRiskCountry,
        "isBribery": isBribery,
        "isMilitaryActivities": isMilitaryActivities,
        "isGrant": isGrant,
        "isCompeteticeDecision": isCompeteticeDecision,
        "isFederal": isFederal,
        "isFeeArrangementContingent": isFeeArrangementContingent,
    }

    var neural_url = "http://127.0.0.1:5000/evaluate"
    var weka_url = "http://127.0.0.1:8080/api/weka/evaluate"
    var factor_url = "http://127.0.0.1:8080/api/factors/evaluate"

    $.ajax({
        type: "POST",
        url:  factor_url,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(data) {
            document.getElementById("outputResultLinear").innerHTML = data.result;
            console.log(data.result);
        },
        error: function(result) {
            console.log(result);
            alert("Error: " + result)
        }
    });

    $.ajax({
        type: "POST",
        url:  weka_url,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(data) {
            document.getElementById("outputResultWeka").innerHTML = data.result;
            console.log(data.result);
        },
        error: function(result) {
            alert("Error: " + result)
        }
    });

    $.ajax({
        type: "POST",
        url:  neural_url,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(data) {
            document.getElementById("outputResultNeural").innerHTML = data;
            console.log(data.result);
        },
        error: function(result) {
            alert("Error: " + result)
        }
    });
}