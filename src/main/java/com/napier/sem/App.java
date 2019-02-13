package com.napier.sem;


import BusinessLayer.*;
import BusinessLayer.DataRow.DataRow;
import DataLayer.DB_Connection;

import java.lang.reflect.Field;
import java.util.*;
public class App
{
    public static void main(String[] args)
    {
        System.out.println("Starting application, it can take some time. ~20sec");
        DB_Connection con = DB_Connection.getInstance();
        ArrayList<DataRow> rows = con.getResult("CityRow","SELECT Name,District,CountryCode AS Country FROM city;");

        displayReport(rows);

        /* Report testObj = new CapitalReport();
        System.out.println("Created object");

        for (String attribute: testObj.getPropertyNames()) {
            System.out.println(attribute);
        }

        Field[] fields = testObj.getClass().getFields();
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            if (field.getName() == "Tester")
            {
                try {
                    field.set(testObj,"newValue");

                } catch (final IllegalAccessException e) {
                    System.out.println("error setting new value");
                }

            }

        });

        System.out.println("Changed Property values:");
        for (String attribute: testObj.getPropertyValues()) {
            System.out.println(attribute);
        }

       // Only for testing purposes, the structure of classes will change in the future
       Report testCountryReport = new CountryReport();
        Report testReport = new CityReport();
        Report testReportCap = new CapitalReport();
        Report testReportPop = new PopulationReport();

        testCountryReport.loadReport();
        testCountryReport.displayReport();
        testReportCap.loadReport();
        testReportCap.displayReport();
        testReport.loadReport();
        testReport.displayReport();
        testReportPop.loadReport();
        testReportPop.displayReport();*/
    }

    public static void displayReport(ArrayList<DataRow> rows)
    {
        for (DataRow row : rows)
        {
            System.out.println(row.toString());
        }
    }
}