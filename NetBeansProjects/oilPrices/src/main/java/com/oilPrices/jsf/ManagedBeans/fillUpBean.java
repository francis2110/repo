/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.jsf.ManagedBeans;

import com.google.code.geocoder.model.GeocoderResult;
import com.oilPrices.Utils.OIlStationsProdPrices;
import com.oilPrices.Utils.Utils;
import com.oilPrices.ejbBeans.CarsFacade;
import com.oilPrices.ejbBeans.OilStationFacade;
import com.oilPrices.ejbBeans.PreciosFacade;
import com.oilPrices.ejbBeans.RefuelingFacade;
import com.oilPrices.ejbBeans.UsersFacade;
import com.oilPrices.entities.Cars;
import com.oilPrices.entities.OilStation;
import com.oilPrices.entities.Precios;
import com.oilPrices.entities.Refueling;
import static com.oilPrices.jsf.ManagedBeans.oSSearchBean.gasolina_95;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author fran
 */
@Named(value = "fillUpBean")
@RequestScoped
public class fillUpBean implements Serializable {

    private List<SelectItem> productsListCb;
    final static String gasolina_95 = "gasolina 95", gasoleo_A = "gasoleo A",
            nuevoGasoleo_A = " nuevo gasoleo A", biodiesel_ = "biodiesel", bioetanol_ = "bioetanol", gasolina98_ = "gasolina 98", priceMode = "price",
            oilStationMode = "oilStationDate";
    private BigDecimal km, literPrice, bill;
    private String mode, fuelType, adress, radius, car;
    private Utils utils = new Utils();
    boolean oilStationPnlVisibility, pricePnlVisibility, placesVisibility;
    private List<GeocoderResult> results;
    private List<SelectItem> carsLstCb;
    private boolean gasolina95Rendered = false, gasoleoARendered = false, nuevoGasoleoRendered = false, gasolina98Rendered = false, biodieselRendered = false, bioetanolRendered = false;
    @Inject
    OilStationFacade oilStationEjb;
    @Inject
    PreciosFacade preciosEjb;
    private List<OIlStationsProdPrices> possibleOilStation;

    private Date fillUpDate;
    private OIlStationsProdPrices oilStationSelected;
    @Inject
    RefuelingFacade refuelingEjb;
    @Inject
    UsersFacade usersEjb;
    @Inject
    CarsFacade carsEjb;
    List<Cars> carLst;

    @PostConstruct
    public void init() {
        productsListCb = new ArrayList<>();
        productsListCb.add(new SelectItem("", "Select one:"));
        productsListCb.add(new SelectItem(gasolina_95, gasolina_95));
        productsListCb.add(new SelectItem(gasolina98_, gasolina98_));
        productsListCb.add(new SelectItem(gasoleo_A, gasoleo_A));
        productsListCb.add(new SelectItem(nuevoGasoleo_A, nuevoGasoleo_A));
        productsListCb.add(new SelectItem(biodiesel_, biodiesel_));
        productsListCb.add(new SelectItem(bioetanol_, bioetanol_));
        setPricePnlVisibility(false);
        setOilStationPnlVisibility(false);
        carsLstCb = new ArrayList<>();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        carLst = (List<Cars>) usersEjb.getUserbyUsername(userName).getCarsCollection();
        carsLstCb = new ArrayList<>();
        carsLstCb.add(new SelectItem("", "Seleccione coche: "));
        Iterator it = carLst.iterator();
        while (it.hasNext()) {
            Cars itemCar = (Cars) it.next();
            carsLstCb.add(new SelectItem(itemCar.getCarsPK().getMatricula(), itemCar.getCarModel()));
        }

        System.out.println("products added to combobox");

    }

    public List<GeocoderResult> getResults() {
        return results;
    }

    public void setResults(List<GeocoderResult> results) {
        this.results = results;
    }

    public boolean isGasolina95Rendered() {
        return gasolina95Rendered;
    }

    public void setGasolina95Rendered(boolean gasolina95Rendered) {
        this.gasolina95Rendered = gasolina95Rendered;
    }

    public boolean isGasoleoARendered() {
        return gasoleoARendered;
    }

    public void setGasoleoARendered(boolean gasoleoARendered) {
        this.gasoleoARendered = gasoleoARendered;
    }

    public boolean isNuevoGasoleoRendered() {
        return nuevoGasoleoRendered;
    }

    public void setNuevoGasoleoRendered(boolean nuevoGasoleoRendered) {
        this.nuevoGasoleoRendered = nuevoGasoleoRendered;
    }

    public boolean isGasolina98Rendered() {
        return gasolina98Rendered;
    }

    public void setGasolina98Rendered(boolean gasolina98Rendered) {
        this.gasolina98Rendered = gasolina98Rendered;
    }

    public boolean isBiodieselRendered() {
        return biodieselRendered;
    }

    public void setBiodieselRendered(boolean biodieselRendered) {
        this.biodieselRendered = biodieselRendered;
    }

    public boolean isBioetanolRendered() {
        return bioetanolRendered;
    }

    public void setBioetanolRendered(boolean bioetanolRendered) {
        this.bioetanolRendered = bioetanolRendered;
    }

    public OilStationFacade getOilStationEjb() {
        return oilStationEjb;
    }

    public void setOilStationEjb(OilStationFacade oilStationEjb) {
        this.oilStationEjb = oilStationEjb;
    }

    public PreciosFacade getPreciosEjb() {
        return preciosEjb;
    }

    public void setPreciosEjb(PreciosFacade preciosEjb) {
        this.preciosEjb = preciosEjb;
    }

    public List<OIlStationsProdPrices> getPossibleOilStation() {
        return possibleOilStation;
    }

    public void setPossibleOilStation(List<OIlStationsProdPrices> possibleOilStation) {
        this.possibleOilStation = possibleOilStation;
    }

    public List<SelectItem> getProductsListCb() {
        return productsListCb;
    }

    public void setProductsListCb(List<SelectItem> productsListCb) {
        this.productsListCb = productsListCb;
    }

    public BigDecimal getKm() {
        return km;
    }

    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;

    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getLiterPrice() {
        return literPrice;
    }

    public void setLiterPrice(BigDecimal literPrice) {
        this.literPrice = literPrice;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public boolean isOilStationPnlVisibility() {
        return oilStationPnlVisibility;
    }

    public void setOilStationPnlVisibility(boolean oilStationPnlVisibility) {
        this.oilStationPnlVisibility = oilStationPnlVisibility;
    }

    public boolean isPricePnlVisibility() {
        return pricePnlVisibility;
    }

    public void setPricePnlVisibility(boolean pricePnlVisibility) {
        this.pricePnlVisibility = pricePnlVisibility;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public boolean isPlacesVisibility() {
        return placesVisibility;
    }

    public void setPlacesVisibility(boolean placesVisibility) {
        this.placesVisibility = placesVisibility;
    }

    public Date getFillUpDate() {
        return fillUpDate;
    }

    public void setFillUpDate(Date fillUpDate) {
        this.fillUpDate = fillUpDate;
    }

    public OIlStationsProdPrices getOilStationSelected() {
        return oilStationSelected;
    }

    public void setOilStationSelected(OIlStationsProdPrices oilStationSelected) {
        this.oilStationSelected = oilStationSelected;
    }

    public RefuelingFacade getRefuelingEjb() {
        return refuelingEjb;
    }

    public void setRefuelingEjb(RefuelingFacade refuelingEjb) {
        this.refuelingEjb = refuelingEjb;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public List<SelectItem> getCarsLstCb() {
        return carsLstCb;
    }

    public void setCarsLstCb(List<SelectItem> carsLstCb) {
        this.carsLstCb = carsLstCb;
    }

    public void drivingDistance() {

        results = getUtils().getGeocoderResults(getAdress());
        if (results.size() == 1) {
            getNearOilStations(results.get(0).getFormattedAddress());
        } else if (results.size() > 1) {
            setPlacesVisibility(true);
        }

    }

    public void modeSelection() {
        System.out.println("Option mode changed.");
        if (getMode() == null) {
            System.out.println("no mode selected.");
        } else if (getMode().equals("price")) {
            setPricePnlVisibility(true);
            setOilStationPnlVisibility(false);
            setPlacesVisibility(false);
        } else if (getMode().equals("oilStationDate")) {
            setPricePnlVisibility(false);
            setOilStationPnlVisibility(true);
        }
        //  RequestContext.getCurrentInstance().update("form");
    }

    public void getNearOilStations(String adr) {
        setPlacesVisibility(false);
        List<OilStation> oilStations = oilStationEjb.findAll();

        possibleOilStation = new ArrayList<>();
        int acceptedDistance = new Integer(getUtils().kilometerFormatToNumber(getRadius()));
        String[] coordinates = getUtils().getCoordinates(getAdress());
        for (OilStation o : oilStations) {
            try {
                Double distance = utils.getDistanceHaversine(o.getLatitud().replace(",", "."), coordinates[0], o.getLongitud().replace(",", "."), coordinates[1]);

                if (distance != null && distance <= acceptedDistance) {

                    Calendar cal = Calendar.getInstance();
//                    if (cal.get(Calendar.HOUR_OF_DAY) < 7 && Calendar.HOUR_OF_DAY > 0) {
//                        cal.add(Calendar.HOUR_OF_DAY, -12);
                    cal.set(Calendar.DAY_OF_MONTH, 21);
                    cal.set(Calendar.MONTH, 4);
                    List<Precios> preciosByDate = preciosEjb.getPreciosByDate(o, cal.getTime());
                    Iterator<Precios> iterator = preciosByDate.iterator();
                    if (preciosByDate.size() > 0) {
                        OIlStationsProdPrices oP = new OIlStationsProdPrices();
                        oP.setDistance(distance);
                        oP.setOilStation(o);
                        while (iterator.hasNext()) {
                            String pName;
                            Precios price = iterator.next();

                            pName = price.getProducts().getNombre();
                            switch (pName) {
                                case gasolina_95:
                                    oP.setGasolinaPrice(Double.valueOf(price.getValor().toString()));
                                    break;
                                case gasoleo_A:
                                    oP.setGasoilPrice(Double.valueOf(price.getValor().toString()));
                                    break;
                                case nuevoGasoleo_A:
                                    oP.setGasoilAPrice(Double.valueOf(price.getValor().toString()));
                                    break;
                                case gasolina98_:
                                    oP.setGasolina98Price(Double.valueOf(price.getValor().toString()));
                                    break;
                            }
                            switch (getFuelType()) {
                                case gasolina_95:
                                    setGasolina95Rendered(true);
                                    setGasolina98Rendered(false);
                                    setGasoleoARendered(false);
                                    setNuevoGasoleoRendered(false);
                                    setBioetanolRendered(false);
                                    setBiodieselRendered(false);
                                    break;
                                case gasolina98_:
                                    setGasolina95Rendered(false);
                                    setGasolina98Rendered(true);
                                    setGasoleoARendered(false);
                                    setNuevoGasoleoRendered(false);
                                    setBioetanolRendered(false);
                                    setBiodieselRendered(false);
                                    break;
                                case gasoleo_A:
                                    setGasolina95Rendered(false);
                                    setGasolina98Rendered(false);
                                    setGasoleoARendered(true);
                                    setNuevoGasoleoRendered(false);
                                    setBioetanolRendered(false);
                                    setBiodieselRendered(false);
                                    break;
                                case nuevoGasoleo_A:
                                    setGasolina95Rendered(false);
                                    setGasolina98Rendered(false);
                                    setGasoleoARendered(false);
                                    setNuevoGasoleoRendered(true);
                                    setBioetanolRendered(false);
                                    setBiodieselRendered(false);
                                    break;
                                case bioetanol_:
                                    setGasolina95Rendered(false);
                                    setGasolina98Rendered(false);
                                    setGasoleoARendered(false);
                                    setNuevoGasoleoRendered(false);
                                    setBioetanolRendered(true);
                                    setBiodieselRendered(false);
                                    break;
                                case biodiesel_:
                                    setGasolina95Rendered(false);
                                    setGasolina98Rendered(false);
                                    setGasoleoARendered(false);
                                    setNuevoGasoleoRendered(false);
                                    setBioetanolRendered(false);
                                    setBiodieselRendered(true);
                                    break;
                            }
                        }
                        possibleOilStation.add(oP);
                        System.out.println(o.getLocalidad());
                    }
                }

            } catch (IndexOutOfBoundsException e) {
                System.err.println("Error en los índices.");
                System.err.println(e.getMessage());
            }

        }

        System.out.println("calculos realizados");
        if (possibleOilStation.size() > 0) {
            setPlacesVisibility(true);
        } else {
            setPlacesVisibility(false);
        }

    }

    public void verifyPrice(FacesContext context, UIComponent toValidate, Object value) {
        if (toValidate.getParent().getParent().getId().equals("pricePnl") && isPricePnlVisibility()) {
            getUtils().verifyPrice(context, toValidate, value);
        }
    }

    /**
     * method for save data of the fillup
     *
     */
    public void saveFillUP() {
        Refueling refueling = new Refueling();
        Cars c = new Cars();
        if (carLst.size() > 0) {
            Iterator<Cars> iterator = carLst.iterator();
            while (iterator.hasNext()) {
                c = iterator.next();
                if (c.getCarsPK().getMatricula().equals(car)) {
                    refueling.setCars(c);
                    break;
                }
            }

            refueling.setKilometers(getKm());
            refueling.setPrice(getBill());
            BigDecimal productPrice = null;

            if (oilStationPnlVisibility) {
                switch (getFuelType()) {
                    case gasolina_95:
                        productPrice = new BigDecimal(getOilStationSelected().getGasolinaPrice());
                        break;
                    case gasolina98_:
                        productPrice = new BigDecimal(getOilStationSelected().getGasolina98Price());
                        break;
                    case gasoleo_A:
                        productPrice = new BigDecimal(getOilStationSelected().getGasoilPrice());
                        break;
                    case nuevoGasoleo_A:
                        productPrice = new BigDecimal(getOilStationSelected().getGasoilAPrice());
                        break;
                }

            } else if (isPricePnlVisibility()) {
                productPrice = getLiterPrice();
            }

            refueling.setLiters(getBill().divide(productPrice, 3, RoundingMode.UP));
            refueling.setRefuelingDate(getFillUpDate());
            refueling.setIdrefueling(refuelingEjb.count() + 1);
            c.getRefuelingCollection().add(refueling);
            
            refuelingEjb.create(refueling);
            setAdress(null);
            setBill(null);
            setCar(null);
            setFillUpDate(null);
            setFillUpDate(null);
            setKm(null);
            setLiterPrice(null);
            setMode(null);
            setOilStationPnlVisibility(false);
            setPlacesVisibility(false);
            setPricePnlVisibility(false);
            setRadius(null);
            
       //     carsEjb.edit(c);

        }
    }

    public void verifyAdress(FacesContext context, UIComponent toValidate, Object value) {
        //validation method for verify that user exist
        if (toValidate.getParent().getParent().getId().equals("oilStationPnl") && oilStationPnlVisibility) {
            if (value == null || value.toString().isEmpty()) {
                String loadMessage = getUtils().loadMessage("adressMssgNull");
                getUtils().messageToUicomponent(context, toValidate, loadMessage);
            }
        }
    }

    public void verifyRadius(FacesContext context, UIComponent toValidate, Object value) {
        if (toValidate.getParent().getParent().getId().equals("oilStationPnl") && oilStationPnlVisibility) {
            if (value == null || value.toString().isEmpty()) {
                String loadMessage = getUtils().loadMessage("radiusNull");
                getUtils().messageToUicomponent(context, toValidate, loadMessage);
            } else {
                try {
                    Integer.parseInt(value.toString());
                } catch (Exception e) {
                    String loadMessage = getUtils().loadMessage("notValidRadius");
                    getUtils().messageToUicomponent(context, toValidate, loadMessage);
                }
            }
        }
    }

}
