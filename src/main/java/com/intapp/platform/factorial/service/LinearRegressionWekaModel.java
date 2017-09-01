package com.intapp.platform.factorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import weka.classifiers.functions.LinearRegression;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

@Service
public class LinearRegressionWekaModel {
    private String filePath = "Workbook3.csv";
    private LinearRegression model;

    ApplicationContext context;

    public LinearRegressionWekaModel( ApplicationContext context) throws Exception {
//        this.context = context;
//        Resource resource =
//                context.getResource("classpath:Workbook3.csv");
//
//        resource.getInputStream();
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(filePath);
        Instances data = source.getDataSet();
        //mark first column for data set as the value we want to predict
        data.setClassIndex(0);

        //build model
        this.model = new LinearRegression();
        model.buildClassifier(data);

        /*
        Instance testInstance = data.get(2);

        DenseInstance testInstance2 = new DenseInstance(13);
        Integer[] arrDataRaw = {-1000,1,3,1,2,1,2,1,1,0,0,1,0,1};
        for (int itr=0; itr < arrDataRaw.length-1;itr++){
            testInstance2.setValue(itr,arrDataRaw[itr]);
        }
        double price = model.classifyInstance(testInstance2);
        System.out.println("Test instance (" + testInstance2 + "): " + price);
        */
    }

    public double clasifyInstance(DenseInstance instanceToClassify) throws Exception {
        double riskEstimation = model.classifyInstance(instanceToClassify);
        return riskEstimation;
    }

    public String createdModelInfo(){
        return model.toString();
    }

}
