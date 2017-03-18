package by.vls.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ContextListenerMock {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/vls";
    private static final String user = "dan";
    private static final String password = "pilotpen";
    // JDBC variables for opening and managing connection
    public static Connection con;
    private static Statement stmt;
    private static ResultSet rs1, rs2, rs3, rs4, rs5;

    public HashMap testSQL() {
        String queryBR = "SELECT * FROM baserate";// запрос на получение базовой ставки по валюте
        String queryOTM = "SELECT * FROM objtypemargin"; // запрос на получение маржи по типу предмета
        String queryIT = "SELECT * FROM insuretypebaserate"; //запрос на получение базового страхового тарифа по предмету
        String queryOAM = //запрос на получение таблицы маржи по возрасту предмета
                "SELECT agemargin_car.age, margin_car, margin_lorry, margin_truck, margin_buildmach, margin_farm, " +
                        "margin_equip, margin_realest " +
                        "FROM agemargin_car, agemargin_lorry, agemargin_truck, agemargin_buildmach, agemargin_farmmach, " +
                        "agemargin_equip, agemargin_realestate " +
                        "WHERE agemargin_car.age=agemargin_lorry.age AND agemargin_car.age=agemargin_truck.age AND " +
                        "agemargin_car.age=agemargin_buildmach.age AND agemargin_farmmach.age=agemargin_car.age " +
                        "AND agemargin_equip.age=agemargin_car.age AND agemargin_realestate.age=agemargin_car.age;";
        String queryIAM = //запрос на получение таблицы  страховой маржи по возрасту предмета
                "SELECT ageinsure_car.age, imargin_car, imargin_lorry, imargin_truck, imargin_buildmach, imargin_farm, " +
                        "imargin_equip, imargin_realest " +
                        "FROM ageinsure_car, ageinsure_lorry, ageinsure_truck, ageinsure_buildmach, ageinsure_farmmach, " +
                        "ageinsure_equip, ageinsure_realestate " +
                        "WHERE ageinsure_car.age=ageinsure_lorry.age AND ageinsure_car.age=ageinsure_truck.age AND " +
                        "ageinsure_car.age=ageinsure_buildmach.age AND ageinsure_farmmach.age=ageinsure_car.age " +
                        "AND ageinsure_equip.age=ageinsure_car.age AND ageinsure_realestate.age=ageinsure_car.age;";

        HashMap configCalc = new HashMap();
        HashMap baseRateTable = new HashMap();
        HashMap objTypeTable = new HashMap();
        HashMap objAgeTable = new HashMap();
        HashMap insureTypeTable = new HashMap();
        HashMap insureAgeTable = new HashMap();
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs1 = stmt.executeQuery(queryBR);


            while (rs1.next()) {
                baseRateTable.put(rs1.getString("currency"), rs1.getFloat("rate"));
            }
            rs2 = stmt.executeQuery(queryOTM);

            while (rs2.next()) {
                objTypeTable.put(rs2.getString("objtype"), rs2.getFloat("objtypemargin"));
            }
            rs3 = stmt.executeQuery(queryIT);

            while (rs3.next()) {
                insureTypeTable.put(rs3.getString("objtype"), rs3.getFloat("insurebaserate"));
            }
            rs4 = stmt.executeQuery(queryOAM);
            ArrayList<Float> car = new ArrayList<Float>();
            ArrayList<Float> lorry = new ArrayList<Float>();
            ArrayList<Float> truck = new ArrayList<Float>();
            ArrayList<Float> buildingmachines = new ArrayList<Float>();
            ArrayList<Float> farmingmachinery = new ArrayList<Float>();
            ArrayList<Float> equipment = new ArrayList<Float>();
            ArrayList<Float> realestate = new ArrayList<Float>();
            while (rs4.next()) {
                car.add(rs4.getFloat("margin_car"));
                lorry.add(rs4.getFloat("margin_lorry"));
                truck.add(rs4.getFloat("margin_truck"));
                buildingmachines.add(rs4.getFloat("margin_buildmach"));
                farmingmachinery.add(rs4.getFloat("margin_farm"));
                equipment.add(rs4.getFloat("margin_equip"));
                realestate.add(rs4.getFloat("margin_realest"));
            }
            objAgeTable.put("car", car);
            objAgeTable.put("lorry", lorry);
            objAgeTable.put("truck", truck);
            objAgeTable.put("buildingmachines", buildingmachines);
            objAgeTable.put("farmingmachinery", farmingmachinery);
            objAgeTable.put("equipment", equipment);
            objAgeTable.put("realestate", realestate);
            rs5 = stmt.executeQuery(queryIAM);
            ArrayList<Float> icar = new ArrayList<Float>();
            ArrayList<Float> ilorry = new ArrayList<Float>();
            ArrayList<Float> itruck = new ArrayList<Float>();
            ArrayList<Float> ibuildingmachines = new ArrayList<Float>();
            ArrayList<Float> ifarmingmachinery = new ArrayList<Float>();
            ArrayList<Float> iequipment = new ArrayList<Float>();
            ArrayList<Float> irealestate = new ArrayList<Float>();
            while (rs5.next()) {
                icar.add(rs5.getFloat("imargin_car"));
                ilorry.add(rs5.getFloat("imargin_lorry"));
                itruck.add(rs5.getFloat("imargin_truck"));
                ibuildingmachines.add(rs5.getFloat("imargin_buildmach"));
                ifarmingmachinery.add(rs5.getFloat("imargin_farm"));
                iequipment.add(rs5.getFloat("imargin_equip"));
                irealestate.add(rs5.getFloat("imargin_realest"));
            }
            insureAgeTable.put("car", icar);
            insureAgeTable.put("lorry", ilorry);
            insureAgeTable.put("truck", itruck);
            insureAgeTable.put("buildingmachines", ibuildingmachines);
            insureAgeTable.put("farmingmachinery", ifarmingmachinery);
            insureAgeTable.put("equipment", iequipment);
            insureAgeTable.put("realestate", irealestate);


            configCalc.put("baseRate", baseRateTable);
            configCalc.put("objTypeMargin", objTypeTable);
            configCalc.put("objAgeTable", objAgeTable);
            configCalc.put("insureTypeTable", insureTypeTable);
            configCalc.put("insureAgeTable", insureAgeTable);


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs1.close();
                rs2.close();
                rs3.close();
                rs4.close();
                rs5.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

        return configCalc;
    }

    public Connection getConnection() {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return con;
    }

}
