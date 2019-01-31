package com.napier.sem;

        import java.sql.*;
        import DataLayer.*;
        import BusinessLayer.*;
public class App
{
    public static void main(String[] args)
    {
        System.out.println("Starting application, it can take some time. ~20sec");

        //Only for testing purposes, the structure of classes will change in the future
        Report testReport = new CityReport();
        testReport.loadReport();
        testReport.displayReport();
    }
}