package by.vasilevsky.leasing.service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class CalcRequest implements Serializable {
    //incoming parameters;
    private static final long serialVersionUID = 1L;
    private float prepay;
    private int duration;
    private float cost;
    private String objectType;
    private String currency;
    private float byuingOutPercent;
    private int objAge;
    //Flags
    private byte noVatInCost;
    private byte noVatOnMargin;
    private byte showInsuranceCol;

    private float insuranceRate;
    private float outgoingRate;
    //Payment list
    private float totalMargin; //total Margin without VAT
    private float totalMarginVat; //total VAT on Margin
    private float totalCost; //totall cost payments without byuingout payment and without VAT
    private float totalCostVat; //total VAT on cost without byuingout payment
    private float totalWholePay; //total payments without byuingout payment

    private float sumCost; //full cost
    private float sumCostVat; //full VAT on cost
    private float sumWholePay; // summ of all payments with VAT and byuingout summ

    private ArrayList<String> listDate;
    private ArrayList<Float> listWholePay;
    private ArrayList<Float> listRemainingDebt;
    private ArrayList<Float> listObjCost;
    private ArrayList<Float> listObjCostVat;
    private ArrayList<Float> listMargin;
    private ArrayList<Float> listMarginVat;
    private ArrayList<Float> listInsurance;


    public void setPrepay(String prep) {
        prepay = Float.parseFloat(prep);
    }

    public float getPrepay() {
        return prepay;
    }

    public void setDuration(String dur) {
        duration = Integer.parseInt(dur);
    }

    public int getDuration() {
        return duration;
    }

    public void setCost(String cst) {
        cost = Float.parseFloat(cst);
    }

    public float getCost() {
        return cost;
    }

    public void setObjAge(String age) {
        objAge = Integer.parseInt(age);
    }

    public int getObjAge() {
        return objAge;
    }

    public void setByuingOutPercent(String bop) {
        byuingOutPercent = Float.parseFloat(bop);
    }

    public float getByuingOutPercent() {
        return byuingOutPercent;
    }

    public void setOutgoingRate(float or) {
        outgoingRate = or;
    }

    public float getOutgoingRate() {
        return outgoingRate;
    }

    public void setObjectType(String ot) {
        objectType = ot;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setCurrency(String cur) {
        currency = cur;
    }

    public String getCurrency() {
        return currency;
    }

    public void setInsuranceRate(float iRate) {
        insuranceRate = iRate;
    }

    public float getInsuranceRate() {
        return insuranceRate;
    }

    public void setNoVatInCost() {
        noVatInCost = 1;
    }

    public byte getNoVatInCostFlag() {
        return noVatInCost;
    }

    public void setNoVatOnMargin() {
        noVatOnMargin = 1;
    }

    public void setShowInsuranceCol() {
        showInsuranceCol = 1;
    }

    public byte getShowInsuranceCol() {
        return showInsuranceCol;
    }

    public float getSumCostVat() {
        return sumCostVat;
    }

    public float getSumCost() {
        return sumCost;
    }

    public float getPrepaySummNoVat() {
        if (noVatInCost == 1) return prepay * cost;
        else return prepay * cost / 1.2f;
    }

    public float getPrepayVat() {
        if (noVatInCost == 1) return 0;
        else return prepay * cost / 1.2f * 0.2f;
    }

    public float getByuOutNoVat() {
        if (noVatInCost == 1) return byuingOutPercent * cost;
        else return byuingOutPercent * cost / 1.2f;
    }

    public float getByuOutVat() {
        if (noVatInCost == 1) return 0;
        else return byuingOutPercent * cost / 1.2f * 0.2f;
    }

    public float getTotalMargin() {
        return totalMargin;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public float getTotalMarginVat() {
        return totalMarginVat;
    }

    public float getTotalCostVat() {
        return totalCostVat;
    }

    public float getTotalWholePay() {
        return totalWholePay;
    }

    public float getSumWholePay() {
        return sumWholePay;
    }

    public ArrayList getRow(int index) {
        ArrayList rowList = new ArrayList();
        rowList.add(listDate.get(index));//col 2
        rowList.add(listRemainingDebt.get(index));//col 3
        rowList.add(listMargin.get(index));//col 4
        rowList.add(listObjCost.get(index));//col 5
        rowList.add(listMarginVat.get(index));//col 6
        rowList.add(listObjCostVat.get(index));//col 7
        rowList.add(listInsurance.get(index));//col 8
        rowList.add(listWholePay.get(index));//col 9
        return rowList;
    }

    public void buildPaymentList() {
        Float debt, monthCost;
        listDate = new ArrayList<String>();
        listRemainingDebt = new ArrayList<Float>();
        listObjCost = new ArrayList<Float>();
        listMargin = new ArrayList<Float>();
        listObjCostVat = new ArrayList<Float>();
        listMarginVat = new ArrayList<Float>();
        listWholePay = new ArrayList<Float>();
        listInsurance = new ArrayList<Float>();

        debt = (cost - prepay * cost);// calculate size of debt
        if (noVatInCost == 0) {
            monthCost = ((debt - byuingOutPercent * cost) / 1.2f) / duration; // ежемесячное погашение долша без НДС
        } else {
            monthCost = (debt - byuingOutPercent * cost) / duration;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMM.yyyy"); //задаём формат даты в графике
        Calendar cal = new GregorianCalendar();

        for (int i = 0; i < duration; i++) {
            cal.add(Calendar.MONTH, 1);
            listDate.add(i, sdf1.format(cal.getTime()));
            if (showInsuranceCol == 1) listInsurance.add(insuranceRate * cost / 12);
            else listInsurance.add(0f);
            listRemainingDebt.add(i, debt);
            listObjCost.add(i, monthCost);
            if (showInsuranceCol == 0) listMargin.add(i, debt * outgoingRate / 12);
            else
                listMargin.add(i, (debt * outgoingRate / 12 + insuranceRate * cost / 12));//добавляем страховку к лизинговой ставке
            if (noVatInCost == 0) listObjCostVat.add(monthCost * 0.2f);
            else listObjCostVat.add(0f);
            if (noVatOnMargin == 0) listMarginVat.add(i, listMargin.get(i) * 0.2f);
            else listMarginVat.add(0f);
            listWholePay.add(i, listObjCost.get(i) + listMargin.get(i) + listObjCostVat.get(i) + listMarginVat.get(i));

            if (noVatInCost == 0) debt = debt - monthCost * 1.2f;
            else debt = debt - monthCost;

        }
        for (int i = 0; i < duration; i++) {
            totalCost += listObjCost.get(i);
            totalMargin += listMargin.get(i);
            totalCostVat += listObjCostVat.get(i);
            totalMarginVat += listMarginVat.get(i);
            totalWholePay += listWholePay.get(i);
        }
        if (noVatInCost == 0) {
            totalCost += (prepay * cost / 1.2f);
            totalCostVat += (prepay * cost / 1.2f * 0.2f);
        } else {
            totalCost += (prepay * cost);
        }
        totalWholePay += prepay * cost;

        if (noVatInCost == 0) {
            sumCost = totalCost + byuingOutPercent * cost / 1.2f;
            sumCostVat = totalCostVat + byuingOutPercent * cost / 1.2f * 0.2f;
        } else sumCost = totalCost + byuingOutPercent * cost;

        sumWholePay = totalWholePay + byuingOutPercent * cost;
    }
}
