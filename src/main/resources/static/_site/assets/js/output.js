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

    $.ajax({
        type: "POST",
        url: neural_url,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(data) {
           // alert("Successful POST operation." + data.result)
            document.getElementById("outputResult").innerHTML = data;
        },
        error: function(result) {
            alert("Error: " + result)
        }
      });
}