/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.jsf.ManagedBeans;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.google.gson.JsonArray;
import com.oilPrices.Utils.OIlStationsProdPrices;
import com.oilPrices.Utils.Utils;
import com.oilPrices.ejbBeans.OilStationFacade;
import com.oilPrices.ejbBeans.PreciosFacade;
import com.oilPrices.entities.OilStation;
import com.oilPrices.entities.Precios;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 *
 * @author fran
 */
@Named(value = "oSSearchBean")
@SessionScoped
public class oSSearchBean implements Serializable {

    private String adress, radius;
    private GeocoderResult result;
    private OIlStationsProdPrices selectedOilStation;
    private List<OIlStationsProdPrices> possibleOilStation;
    @Inject
    OilStationFacade oilStationEjb;
    @Inject
    PreciosFacade preciosEjb;
    private List<GeocoderResult> results;
    private boolean placesVisibility = false, gasolina95Rendered = false, gasoleoARendered = false, nuevoGasoleoRendered = false, gasolina98Rendered = false, biodieselRendered = false, bioetanolRendered = false;
    JsonArray rows;
    Utils utils = new Utils();
    String productSelected;
    final static String gasolina_95 = "gasolina 95", gasoleo_A = "gasoleo A", nuevoGasoleo_A = " nuevo gasoleo A", biodiesel_ = "biodiesel", bioetanol_ = "bioetanol", gasolina98_ = "gasolina 98";
    private List<SelectItem> productsListCb;

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
                System.out.println("products added to combobox");

    }

    public oSSearchBean() {
        System.out.println("Se mete en el bean.");
    }

    public List<SelectItem> getProductsListCb() {
        return productsListCb;
    }

    public void setProductsListCb(List<SelectItem> productsListCb) {
        this.productsListCb = productsListCb;
    }

    public OilStationFacade getOilStationEjb() {
        return oilStationEjb;
    }

    public void setOilStationEjb(OilStationFacade oilStationEjb) {
        this.oilStationEjb = oilStationEjb;
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

    public String getProductSelected() {
        return productSelected;
    }

    public void setProductSelected(String productSelected) {
        this.productSelected = productSelected;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public OIlStationsProdPrices getSelectedOilStation() {
        return selectedOilStation;
    }

    public void setSelectedOilStation(OIlStationsProdPrices selectedOilStation) {
        this.selectedOilStation = selectedOilStation;
    }

    public List<OIlStationsProdPrices> getPossibleOilStation() {
        return possibleOilStation;
    }

    public void setPossibleOilStation(List<OIlStationsProdPrices> possibleOilStation) {
        this.possibleOilStation = possibleOilStation;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public List<GeocoderResult> getResults() {
        return results;
    }

    public GeocoderResult getResult() {
        return result;
    }

    public void setResult(GeocoderResult result) {
        this.result = result;
    }

    public void setResults(List<GeocoderResult> results) {
        this.results = results;
    }

    public boolean isPlacesVisibility() {
        return placesVisibility;
    }

    public void setPlacesVisibility(boolean placesVisibility) {
        this.placesVisibility = placesVisibility;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }
    

    public List<GeocoderResult> getGeocoderResults(String adr) {
        Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest;
        List<GeocoderResult> results = null;
        try {
            geocoderRequest = new GeocoderRequestBuilder().setAddress(adr).setLanguage("es").getGeocoderRequest();
            results = geocoder.geocode(geocoderRequest).getResults();
        } catch (IOException ex) {
            System.err.println("Exception trying to get geocoderResponse.");
        }
        return results;
    }

    /**
     * returns coordinates for only one point. You have to be sure that the
     * geolocation only will find one place.
     *
     * @params adr:String
     * @return String that represents coordinates usable by google service.
     */
    public String[] getCoordinates(String adr) {

        List<GeocoderResult> results = getGeocoderResults(adr);

        LatLng location = results.get(0).getGeometry().getLocation();
        String lat = location.getLat().toString().replace(",", ".");
        String lng = location.getLng().toString().replace(",", ".");
        return new String[]{lat, lng};

    }

    public void getNearOilStations(String adr) {
        setPlacesVisibility(false);
        List<OilStation> oilStations = oilStationEjb.findAll();

        possibleOilStation = new ArrayList<>();
        int acceptedDistance = new Integer(kilometerFormatToNumber(getRadius()));
        String[] coordinates = getCoordinates(getAdress());
        for (OilStation o : oilStations) {
            try {
                Double distance = utils.getDistanceHaversine(o.getLatitud().replace(",", "."), coordinates[0], o.getLongitud().replace(",", "."), coordinates[1]);
//                String urlString = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="
//                        + getCoordinates(results.get(0).getFormattedAddress()) + "&destinations=" + destination
//                        + "&mode=driving&sensor=false";
//                URL url = new URL(urlString);
//                BufferedReader in;
//                in = new BufferedReader(new InputStreamReader(url.openStream()));
//                JsonParser jsonParser = new JsonParser();
//                JsonObject jsonObject = (JsonObject) jsonParser.parse(in);
//                rows = (JsonArray) jsonObject.get("rows");
//
//                String distance = rows.get(0).getAsJsonObject().get("elements").getAsJsonArray().get(0).getAsJsonObject().get("distance").getAsJsonObject().get("text").getAsString();
                if (distance != null && distance <= acceptedDistance) {

                    Calendar cal = Calendar.getInstance();
//                    if (cal.get(Calendar.HOUR_OF_DAY) < 7 && Calendar.HOUR_OF_DAY > 0) {
//                        cal.add(Calendar.HOUR_OF_DAY, -12);
                    cal.set(Calendar.DAY_OF_MONTH, 21);
                    cal.set(Calendar.MONTH,4);
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
                            switch (productSelected) {
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

    private String kilometerFormatToNumber(String km) {
        km = km.replace(" ", "");
        return km.replace("km", "");
    }

    public void onlyOneAdress() {
        getNearOilStations(getResult().getFormattedAddress());
    }

    public void drivingDistance() {
      
         results = getGeocoderResults(getAdress());
        if (results.size() == 1) {
            getNearOilStations(results.get(0).getFormattedAddress());
        } else if (results.size() > 1) {
            setPlacesVisibility(true);
        }
        
       
    }

    public void verifyAdress(FacesContext context, UIComponent toValidate, Object value) {
        //validation method for verify that user exist
        if (value == null || value.toString().isEmpty()) {
            String loadMessage = utils.loadMessage("adressMssgNull");
            utils.messageToUicomponent(context, toValidate, loadMessage);
        }

    }

    public void verifyRadius(FacesContext context, UIComponent toValidate, Object value) {
        if (value == null || value.toString().isEmpty()) {
            String loadMessage = utils.loadMessage("radiusNull");
            utils.messageToUicomponent(context, toValidate, loadMessage);
        } else {
            try {
                Integer.parseInt(value.toString());
            } catch (Exception e) {
                String loadMessage = utils.loadMessage("notValidRadius");
                utils.messageToUicomponent(context, toValidate, loadMessage);
            }
        }
    }
    public void verifyOilSelection(FacesContext context, UIComponent toValidate, Object value){
    if(value==null||value.toString().isEmpty()){
    String loadMessage=utils.loadMessage("oilSelectionNull");
    utils.messageToUicomponent(context, toValidate, loadMessage);
    }
    }

}
