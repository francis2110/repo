/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.Utils;

import com.oilPrices.entities.OilStation;

/**
 *
 * @author fran
 */
public class OIlStationsProdPrices extends OilStation{
    private Double gasoilPrice,gasolinaPrice, gasoilAPrice, gasolina98Price,distance;
    private OilStation oilStation;

 
    

    public OilStation getOilStation() {
        return oilStation;
    }

    public void setOilStation(OilStation oilStation) {
        this.oilStation = oilStation;
    }

    public Double getGasoilPrice() {
        return gasoilPrice;
    }

    public void setGasoilPrice(Double gasoilPrice) {
        this.gasoilPrice = gasoilPrice;
    }

    public Double getGasolinaPrice() {
        return gasolinaPrice;
    }

    public void setGasolinaPrice(Double gasolinaPrice) {
        this.gasolinaPrice = gasolinaPrice;
    }

    public Double getGasoilAPrice() {
        return gasoilAPrice;
    }

    public void setGasoilAPrice(Double gasoilAPrice) {
        this.gasoilAPrice = gasoilAPrice;
    }

    public Double getGasolina98Price() {
        return gasolina98Price;
    }

    public void setGasolina98Price(Double gasolina98Price) {
        this.gasolina98Price = gasolina98Price;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    
}
