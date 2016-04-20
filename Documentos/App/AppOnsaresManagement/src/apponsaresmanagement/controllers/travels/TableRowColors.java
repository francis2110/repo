/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.controllers.travels;

import apponsaresmanagement.jpa.entities.Viaje;
import apponsaresmanagement.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.concurrent.Task;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author fran
 */
public class TableRowColors implements Callback<TableColumn<Viaje, String>, TableCell<Viaje, String>> {

    private Utils utils = null;

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    @Override
    public TableCell call(TableColumn p) {
        TableCell cell = new TableCell<Task, Object>() {
            @Override
            protected void updateItem(Object t, boolean bln) {
                //css styles:
                String paymentOKStyle = "paymentOK", clientpaymentDelayStyle = "clientpaymentDelay", travelnotpaidtoDriverStyle = "travelnotpaidtoDriver",
                        registeredTravelStyle = "registeredTravel", notregisteredTravelStyle = "notregisteredTravel", treasuryStateStyle = "treasuryState";
                String cssStyle = "";
                Viaje v = null;
                
                if (getTableRow() != null && getTableRow().getItem() != null) {
                    v = (Viaje) getTableRow().getItem();
                  
                    //remove all previosly assigned  css styles from the cell
                    getStyleClass().remove(paymentOKStyle);
                    getStyleClass().remove(clientpaymentDelayStyle);
                    getStyleClass().remove(travelnotpaidtoDriverStyle);
                    getStyleClass().remove(registeredTravelStyle);
                    getStyleClass().remove(notregisteredTravelStyle);
                    getStyleClass().remove(treasuryStateStyle);
                    super.updateItem(t, bln);
                    //determine of to format the cell based on the status of the travel
                    String travelState = getUtils().getTravelState(v);
                    String clientState = v.getEstadoCliente(), driverState = v.getEstadoTransportista();
//                    String treasuryState = v.getEstadoHacienda();
//                    boolean treasuryOk = false; //variable for check if the treasury has paid iva or not
//                    if (treasuryState.equals("Pendiente")) {
//                        treasuryOk = false;
//                    } else {
//                        treasuryOk = true;
//                    }
                    if (travelState.equals("notRegistered")) {
                        //if the travel is notregistered:
                        cssStyle = notregisteredTravelStyle;
                    } else if (travelState.equals("clientDelay")) {
                        //if the travel is not paid in date by the client:
                        cssStyle = clientpaymentDelayStyle;
                    } else if (travelState.equals("driverDelay")) {
                        //if the driver does not receive the payment the next month:
                        cssStyle = travelnotpaidtoDriverStyle;
                    } else if (travelState.equals("treasuryPays")) {
                        //treasury has to pay "iva"
                        cssStyle = treasuryStateStyle;
                    } else if (travelState.equals("registered")) {
                        //travel registered
                        cssStyle = registeredTravelStyle;
                    } else if (travelState.equals("ok")) {
                        //payments ok
                        cssStyle = paymentOKStyle;
                    }
                    //sets the css style:
                    getStyleClass().add(cssStyle);
                    if (t != null) {
                        setText(formatCellContent(t, getTableColumn().getId()));
                    } else {
                        setText("");
                    }
                }
            }
        };
        return cell;
    }

    private String formatCellContent(Object t, String columnId) {
        /**
         * return the text to be represented in cells with the correct format:
         *
         * @param t: object that contains data, columId: the id of the column
         * @return: String with the correct format
         */
        if (columnId.equals("dateCol")) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(t);
        } else if (columnId.equals("travelTypeCol")) {
            if (t.equals("T")) {
                return "Terrestre";
            } else if (t.equals("EX")) {
                return "Exportación";
            }
        } else if (columnId.equals("arriveHourCol") || columnId.equals("exitHourCol")) {
            return getUtils().gethhmm((Date) t);
        } else if (columnId.equals("ivaCol")) {
            if (t.equals("S")) {
                return "IVA asumido por cliente.";
            } else if (t.equals("N")) {
                return "OnsaresLog. asume el IVA.";
            }
        }

        return t.toString();

    }

    private boolean checkDriverDelay(Viaje v) {
        /**
         * checks if the driver has delayment with his salary. We consider that
         * the payment must be done the month after the travel is done
         *
         * @param v the travel checked for looking if has delayment:
         * @return true if has delayment, false if not
         */
        Date travelDate = v.getFecha();
        Calendar travelDateCal = Calendar.getInstance();
        travelDateCal.setTime(travelDate);
        int travelMonth = travelDateCal.get(Calendar.MONTH);
        int travelYear = travelDateCal.get(Calendar.YEAR);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int actualMonth = cal.get(Calendar.MONTH);
        int actualYear = cal.get(Calendar.YEAR);
        if (actualYear - travelYear == 1) {
            if (actualMonth - travelMonth != -11) {
                return true;
            }
        } else if (actualYear - travelYear > 1) {
            return true;
        } else if (actualMonth - travelMonth > 1) {
            return true;
        }
        return false;
    }

    private boolean checkClientDelays(Viaje v) {
        /**
         * checks if are delays with client payments:
         *
         * @param v the travel for check if have delays in payments
         * @return returns true if are delay in payments
         */
        Date travelDate = v.getFecha();
        Calendar travelDateCal = Calendar.getInstance();
        travelDateCal.setTime(travelDate);
        travelDateCal.add(Calendar.DAY_OF_MONTH, v.getTipoPago());
        Calendar actualDate = Calendar.getInstance();
        actualDate.setTime(new Date());
        if (travelDateCal.getTime().before(actualDate.getTime())) {
            return true;
        } else {
            return false;
        }

    }
}