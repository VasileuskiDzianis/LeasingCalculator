package by.vasilevsky.leasing.service;

import java.util.ArrayList;

import by.vasilevsky.leasing.dao.DataBaseConnector;

public class LeasingRateCalculator {
    private float rate;

    public CalcRequest getRate(CalcRequest newcalc) {
        DataBaseConnector db = new DataBaseConnector();
        float otMargin, oaMargin;
        ArrayList obAgeTbl;
        otMargin = (float) db.getObjTypeMarginTable().get(newcalc.getObjectType());
        obAgeTbl = (ArrayList) db.getObjAgeMarginTable().get(newcalc.getObjectType());
        if (newcalc.getObjAge() > (obAgeTbl.size() - 1)) oaMargin = (float) obAgeTbl.get(obAgeTbl.size() - 1);
        else oaMargin = (float) obAgeTbl.get(newcalc.getObjAge());

        rate = (float) db.getBaseRateTable().get(newcalc.getCurrency());
        rate = rate + db.getBaseCurrencyMargin() + oaMargin +
                +otMargin - db.getRegClientDiscount() - db.getObjCostDicount() -
                db.getSegDiscount();
        newcalc.setOutgoingRate(rate);
        if (newcalc.getShowInsuranceCol() == 1) {
            newcalc.setInsuranceRate(new
                    InsureRateCalculator().getInsuranceRate(newcalc.getObjectType(),
                    newcalc.getCost(), newcalc.getObjAge(), newcalc.getCurrency()));
        }

        return (newcalc);
    }

}
