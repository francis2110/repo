/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.Utils;

import com.oilPrices.entities.Refueling;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author fran
 */
public class ConsumptionTbl extends Refueling {

    BigDecimal consumption;

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    public void calculateConsumption(BigDecimal Km, BigDecimal liters) {
        setConsumption(liters.divide(Km,3,RoundingMode.UP).multiply(new BigDecimal(100)));
    }
}
