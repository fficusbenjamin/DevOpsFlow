package com.napier.sem;


import BusinessLayer.*;
public class App
{
    public static void main(String[] args)
    {
        System.out.println("Starting application, it can take some time. ~20sec");

       // Only for testing purposes, the structure of classes will change in the future
        Report testCountryReport = new CountryReport();
        Report testReport = new CityReport();
        Report testReportCap = new CapitalReport();
        Report testReportPop = new PopulationReport();

        //testCountryReport.loadReport();
        //testCountryReport.displayReport();
        //testReportCap.loadReport();
        //testReportCap.displayReport();
        //testReport.loadReport();
        //testReport.displayReport();
        testReportPop.loadReport();
        testReportPop.displayReport();
    }
}