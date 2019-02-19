package com.napier.sem;


import BusinessLayer.*;

import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {







        System.out.println("Starting application, it can take some time. ~20sec");
        testMenu();

    }
    public static void testMenu()
    {
        int choice = 10;
        Scanner input = new Scanner(System.in);

        while (choice != 0)
        {
            System.out.println("Please choose 1-4 for testing method, or 0 to quit: \n 1 City Report \n 2 Capital City Report \n 3 Country Report \n 4 Population Report");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    //Only for testing purposes, the structure of classes will change in the future
                    Report testReport = new CityReport();
                    testReport.loadReport();
                    testReport.displayReport();
                    break;
                case 2:
                    Report testReportCap = new CapitalReport();
                    testReportCap.loadReport();
                    testReportCap.displayReport();
                    break;
                case 3:
                    Report newCountry = new CountryReport();
                    newCountry.loadReport();
                    newCountry.displayReport();
                    break;
                case 4:
                    Report testReportPop = new PopulationReport();
                    testReportPop.loadReport();
                    testReportPop.displayReport();
                    break;
                default:
                    System.out.println("Please choose 1, 2, 3 or 4 only");
            }
        }

    }
}

