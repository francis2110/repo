/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oilPrices.Utils;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author fran
 */
public class Utils implements Serializable {

    public Double getDistanceHaversine(String lat1, String lat2, String long1, String long2) {
        final int radius = 6371;
        Double a;

        Double la1 = Double.parseDouble(lat1) * PI / 180;
        Double la2 = Double.parseDouble(lat2) * PI / 180;
        Double lng1 = Double.parseDouble(long1) * PI / 180;
        Double lng2 = Double.parseDouble(long2) * PI / 180;
        a = pow(sin((la2 - la1)) / 2, 2) + cos(la2) * cos(la1) * pow(sin((lng1 - lng2)) / 2, 2);
        return radius * 2 * Math.asin(pow(a, 0.5));
    }

    public String loadMessage(String messageKey) {
        //reads from the properties file using the key of each property
        String message = null;
        this.getClass().getCanonicalName();
        InputStream is = (InputStream) this.getClass().getClassLoader().getResourceAsStream("../../resources/messages/messages.properties");
        Properties props = new Properties();
        try {
            props.load(is);
            message = props.getProperty(messageKey);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    public void messageToUicomponent(FacesContext context, UIComponent toValidate,
            String message) {
        //asociates a message to a UIComponent
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, message);
        context.addMessage(toValidate.getClientId(context), facesMessage);
        ((UIInput) toValidate).setValid(false);

    }

    public boolean nullValidationComponet(FacesContext context, UIComponent toValidate, Object value, String messageId) {
        if (value == null || value.toString().replaceAll("\\s", "").isEmpty()) {
            String loadMessage = loadMessage(messageId);
            messageToUicomponent(context, toValidate, loadMessage);
            return true;
        }
        return false;
    }

    public String getSHA256String(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void verifyOilSelection(FacesContext context, UIComponent toValidate, Object value) {
        nullValidationComponet(context, toValidate, value, "oilSelectionNull");
    }

    public void verifyPrice(FacesContext context, UIComponent toValidate, Object value) {

        boolean isNull = nullValidationComponet(context, toValidate, value, "priceNull");
        if (!isNull) {
            try {
                BigDecimal number = new BigDecimal(value.toString().replaceAll("\\s", "").toString());
                int comparision = number.compareTo(new BigDecimal("0"));
                if (comparision == -1) {
                    messageToUicomponent(context, toValidate, loadMessage("negativePrice"));
                }
            } catch (NumberFormatException e) {
                messageToUicomponent(context, toValidate, loadMessage("priceFormatError"));
            }
        }

    }

    public void verifyKilometers(FacesContext context, UIComponent toValidate, Object value) {
        boolean isNull = nullValidationComponet(context, toValidate, value, "fillUpKmsNull");
        if (!isNull) {
            //     try {
            BigDecimal number = new BigDecimal(value.toString());
            int comparision = number.compareTo(new BigDecimal("0"));
            if (comparision == -1) {
                messageToUicomponent(context, toValidate, loadMessage("negativeKilometers"));
            }
//            } catch (NumberFormatException e) {
//                messageToUicomponent(context, toValidate, "kilometerFormatError");
//            }
        }
    }

    public void verifyAdress(FacesContext context, UIComponent toValidate, Object value) {
        //validation method for verify that user exist
        if (value == null || value.toString().isEmpty()) {
            String loadMessage = loadMessage("adressMssgNull");
            messageToUicomponent(context, toValidate, loadMessage);
        }

    }

    public void verifyRadius(FacesContext context, UIComponent toValidate, Object value) {
        if (value == null || value.toString().isEmpty()) {
            String loadMessage = loadMessage("radiusNull");
            messageToUicomponent(context, toValidate, loadMessage);
        } else {
            try {
                Integer.parseInt(value.toString());
            } catch (Exception e) {
                String loadMessage = loadMessage("notValidRadius");
                messageToUicomponent(context, toValidate, loadMessage);
            }
        }
    }

    public void verityDate(FacesContext context, UIComponent toValidate, Object value) {
        nullValidationComponet(context, toValidate, value, "dateNull");
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

    public void verifyCar(FacesContext context, UIComponent toValidate, Object value) {
        nullValidationComponet(context, toValidate, value, "carSelectionNull");
        SelectOneMenu carCb = (SelectOneMenu) toValidate;
        if (carCb.getChildCount() == 0) {
            String loadMessage = loadMessage("noneCar");
            messageToUicomponent(context, toValidate, loadMessage);
        }
    }

    public String kilometerFormatToNumber(String km) {
        km = km.replace(" ", "");
        return km.replace("km", "");
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
}
