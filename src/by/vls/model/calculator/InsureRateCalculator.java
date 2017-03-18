package by.vls.model.calculator;

import by.vls.database.*;

import java.util.*;

public class InsureRateCalculator {
    private HashMap typeTable;
    private HashMap costTable;
    private HashMap ageTable;
    private ArrayList agetmp;
    private float resultIR;

    public float getInsuranceRate(String type, float cost, int age, String cur) {
        DataBaseConnector irconf = new DataBaseConnector();
        typeTable = irconf.getInsureTypeTable();
        ageTable = irconf.getInsureAgeTable();
        agetmp = (ArrayList) ageTable.get(type);
        if (age > (agetmp.size() - 1)) age = agetmp.size() - 1;
        resultIR = (float) typeTable.get(type) + (float) agetmp.get(age);
        switch (cur) {
            case "eur":
                cost = cost * (CurrencyConverter.EUR_BYN / CurrencyConverter.USD_BYN);
                break;
            case "byn":
                cost = cost / CurrencyConverter.USD_BYN;
                break;
            case "rub":
                cost = cost * CurrencyConverter.RUB_BYN / CurrencyConverter.USD_BYN;
                break;
        }
        if (cost < irconf.getCostDiff()) resultIR += irconf.getCostDiffRate();

        return resultIR;
    }
}
