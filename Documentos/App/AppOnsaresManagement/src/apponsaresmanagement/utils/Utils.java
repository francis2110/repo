/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apponsaresmanagement.utils;

import apponsaresmanagement.controllers.AuxStageController;
import apponsaresmanagement.controllers.travels.BillCodeController;
import apponsaresmanagement.controllers.travels.TravelPanelController;
import apponsaresmanagement.controllers.truckDriver.SeeDriversDataController;
import apponsaresmanagement.jpa.controllers.ClienteJpaController;
import apponsaresmanagement.jpa.controllers.FacturaJpaController;
import apponsaresmanagement.jpa.controllers.RemolqueJpaController;
import apponsaresmanagement.jpa.controllers.TransportistaJpaController;
import apponsaresmanagement.jpa.controllers.ViajeJpaController;
import apponsaresmanagement.jpa.entities.Cliente;
import apponsaresmanagement.jpa.entities.Remolque;
import apponsaresmanagement.jpa.entities.Transportista;
import apponsaresmanagement.jpa.entities.Viaje;
import apponsaresmanagement.jpa.entities.ViajePK;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author fran
 */
public class Utils {

    private ClienteJpaController clienteController = null;
    private TransportistaJpaController truckDriverController = null;
    final String redTxtFieldEffect = "red-focused-class";
    private ViajeJpaController travels = null;
    private RemolqueJpaController trailerController = null;
    private FacturaJpaController billController = null;

    public double getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    public double getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }

    public RemolqueJpaController getTrailerController() {
        if (trailerController == null) {
            trailerController = new RemolqueJpaController();
        }
        return trailerController;
    }

    public FacturaJpaController getBillController() {
        if (billController == null) {
            billController = new FacturaJpaController();
        }
        return billController;
    }

    public ClienteJpaController getClienteController() {
        if (clienteController == null) {
            clienteController = new ClienteJpaController();
        }
        return clienteController;
    }

    public void setClienteController(ClienteJpaController clienteController) {
        this.clienteController = clienteController;
    }

    public TransportistaJpaController getTruckDriverController() {
        if (truckDriverController == null) {
            truckDriverController = new TransportistaJpaController();
        }
        return truckDriverController;
    }

    public void setTruckDriverController(TransportistaJpaController truckDriverController) {
        this.truckDriverController = truckDriverController;
    }

    public void timeListener(final TextField timeTxt, final Text validator) {
        timeTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!t1 && !timeTxt.getText().equals("")) {
                    String time = timeTxt.getText().trim();
                    int counter = 0;//variable that counts ':'
                    for (int i = 0; i < time.length(); i++) {
                        if (time.charAt(i) == ':') {
                            counter++;
                        }
                    }
                    boolean correct = false; //variable for activate the red backgroud for textboxes
                    if (counter == 1) {
                        String[] hhmm = time.split(":");
                        try {
                            int hour = Integer.parseInt(hhmm[0]);
                            int minutes = Integer.parseInt(hhmm[1]);
                            if (hour <= 24 && hour >= 0 && minutes >= 0 && minutes <= 60) {
                                correct = true;
                            }
                        } catch (NumberFormatException ex) {
                            correct = false;
                        }

                    }
                    if (correct) {
                        validator.setText("");
                        timeTxt.getStyleClass().remove(redTxtFieldEffect);
                    } else {
                        validator.setText("Formato de hora incorrecto.");
                        timeTxt.getScene().getStylesheets().add("validatorCss.css");
                        timeTxt.getStyleClass().add(redTxtFieldEffect);
                    }
                }
            }
        });
    }

    public void notNullTextField(final TextField txt, final Text validator) {
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!t1) {
                    if (txt.getText().equals("")) {
                        validator.setText("Campo nulo. Rellene el campo. Campo obligatorio. ");
                        if (txt.getScene() != null) {
                            txt.getScene().getStylesheets().add("validatorCss.css");
                            txt.getStyleClass().add(redTxtFieldEffect);
                        }
                    } else {
                        validator.setText("");
                        txt.getStyleClass().remove(redTxtFieldEffect);
                    }
                }
            }
        });
    }

    public void numberCountListener(final TextField txt, final Text validator, final int num) {
        //listener for verifying the number of characters in fields related to the account number:
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!t1) {
                    //if the field has less numbers that requiered
                    if (txt.getCharacters().length() < num) {
                        validator.setText("En el campo resaltado hay menos números de los que corresponde en el formato de cuenta. ");
                        txt.getScene().getStylesheets().add("validatorCss.css");
                        txt.getStyleClass().add(redTxtFieldEffect);
                    } else {
                        validator.setText("");
                        txt.getStyleClass().remove(redTxtFieldEffect);
                    }
                }
            }
        });
    }

    //validations
    public void addnameTxtListener(final TextField nameTxt, final Text validator) {
        //looks for errors in the name of the client
        nameTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //!t1 implies that the textfield lost focus->doesn't have new value             
                if (!t1) {
                    //if client name exist the list with have a size of 1 at least
                    List<Cliente> findClienteByName = getClienteController().findClienteByName(nameTxt.getText());
                    if (findClienteByName.size() > 0) {
                        validator.setText("Nombre de cliente ya existe. ");
                        nameTxt.getScene().getStylesheets().add("validatorCss.css");
                        nameTxt.getStyleClass().add(redTxtFieldEffect);
                    } else {
                        validator.setText("");
                        nameTxt.getStyleClass().remove(redTxtFieldEffect);
                    }
                }
            }
        });
    }

    public void addclientIdTxtListener(final TextField cifTxt, final Text validator) {
        //looks for errors in the CIF/NIF of the client
        cifTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //if NIF/CIF exist
                //!t1 is to control the event focus losed
                if (!t1) {
                    if (getClienteController().findCliente(cifTxt.getText()) != null) {
                        validator.setText("El NIF/CIF  ya existe. Puede que haya introducido los datos anteriormente. ");
                        cifTxt.getScene().getStylesheets().add("/validatorCss.css");
                        cifTxt.getStyleClass().add(redTxtFieldEffect);
                    } else {
                        validator.setText("");
                        cifTxt.getStyleClass().remove(redTxtFieldEffect);
                    }
                }
            }
        });
    }

    public void addCIFTruckDriverListener(final TextField cifTxt, final Text validator) {
        cifTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!t1) {
                    Transportista findTransportista = getTruckDriverController().findTransportista(cifTxt.getText());

                    if (findTransportista != null) {
                        //if one truck Driver is found:
                        cifTxt.getScene().getStylesheets().add("/validatorCss.css");
                        cifTxt.getStyleClass().add(redTxtFieldEffect);
                        validator.setText("El NIF del transportista ya existe. Puede que haya introducido los datos anteriormente.");
                    } else {
                        validator.setText("");
                        cifTxt.getStyleClass().remove(redTxtFieldEffect);
                    }

                }
            }
        });


    }

    public void addNumericListener(TextField textField, final Text validator) {
        //Checks phone number format
        final TextField txt = textField;
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!t1) {
                    try {
                        if (!txt.getText().equals("")) {
                            Long.parseLong(txt.getText().trim());
                        }
                        validator.setText("");
                        txt.getStyleClass().remove(redTxtFieldEffect);
                    } catch (NumberFormatException exc) {
                        validator.setText("El campo resaltado no puede contener letras. ");
                        txt.getScene().getStylesheets().add("/validatorCss.css");
                        txt.getStyleClass().add(redTxtFieldEffect);
                    }
                }
            }
        });
    }

    public void addEmailListener(final TextField txt, final Text validator) {
        //checks the format of email:
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!t1) {
                    boolean contains = txt.getText().contains("@");
                    if (!contains) {
                        validator.setText("El campo resaltado no cumple con el formato de email. ");
                        txt.getScene().getStylesheets().add("/validatorCss.css");
                        txt.getStyleClass().add(redTxtFieldEffect);
                    } else {
                        validator.setText("");
                        txt.getStyleClass().remove(redTxtFieldEffect);
                    }
                }

            }
        });
    }

    public void distanceListener(final TextField txt, final Text validator) {
        //checks distance format with "," separator
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                String distance = txt.getText();
                if (!t1 && !distance.equals("")) {
                    boolean OK = numericValidatorPoint(distance, validator, "distance");
                    if (OK) {
                        txt.getStyleClass().remove(redTxtFieldEffect);
                    } else {
                        txt.getScene().getStylesheets().add("/validatorCss.css");
                        txt.getStyleClass().add(redTxtFieldEffect);
                    }
                }

            }
        });
    }

    private boolean numericValidatorPoint(String txt, final Text validator, String type) {
        //returns true if validation is ok
        boolean OK = true;
        int lastIndexOf = txt.lastIndexOf(".");
        //if we evaluate money
        if (type.equals("money")) {
            if (txt.length() - lastIndexOf - 1 != 2) {
                validator.setText("El formato de euros ha de contener dos decimales.");
                OK = false;
            } else {
                validator.setText("");
            }
        }
        //whatever
        if (OK) {
            try {
                Integer.parseInt(txt.substring(lastIndexOf + 1));
                Integer.parseInt(txt.substring(0, lastIndexOf));
            } catch (NumberFormatException e) {
                validator.setText("Hay caracteres no númericos");
                OK = false;
            }
        }
        return OK;
    }

    public void eurosListener(final TextField txt, final Text validator) {
        //checks the format of the amount in €
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                boolean OK = false;
                String money = txt.getText();
                if (!t1 && !money.equals("")) {

                    if (money.contains(".")) {
                        OK = numericValidatorPoint(money, validator, "money");
                    } else {
                        try {
                            Integer.parseInt(money);
                            validator.setText("");
                            OK = true;

                        } catch (NumberFormatException e) {
                            validator.setText("Hay caracteres no númericos");
                            OK = false;
                        }
                    }
                }
                if (OK) {
                    txt.getStyleClass().remove(redTxtFieldEffect);
                } else if (!money.equals("")) {
                    txt.getScene().getStylesheets().add("/validatorCss.css");
                    txt.getStyleClass().add(redTxtFieldEffect);
                }
            }
        });

    }

    public void addNoNumbers(final TextField txt, final Text validator) {
        //checks that string does not contain numbers
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //checks if there are numbers in the string
                boolean numbers = false;
                if (txt != null) {
                    if (!t1 && !txt.getText().equals("")) {
                        int length = txt.getLength();
                        for (int i = 0; i < length; i++) {
                            char c = txt.getText().charAt(i);
                            try {
                                Integer.parseInt(Character.toString(c));
                                numbers = true;
                            } catch (NumberFormatException e) {
                            }
                        }
                        if (numbers) {
                            validator.setText("Error. Se ha detectado un número en el campo. ");
                            txt.getScene().getStylesheets().add("/validatorCss.css");
                            txt.getStyleClass().add(redTxtFieldEffect);
                        } else {
                            validator.setText("");
                            txt.getStyleClass().remove(redTxtFieldEffect);
                        }
                    }
                    if (!t1 && txt.getText().equals("")) {
                        validator.setText("");
                        txt.getStyleClass().remove(redTxtFieldEffect);
                    }
                }
            }
        });
    }

    public Calendar getDatewithFormat(String date) {
        //returns a calendar instance with the date of the string dd/MM/yyyy
        int last = date.lastIndexOf("/");
        String year = date.substring(last + 1);
        date = date.substring(0, last);
        last = date.lastIndexOf("/");
        String month = date.substring(last + 1);
        String day = date.substring(0, last);
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        //In calendar January is 0 and December is 11
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        return cal;
    }

    public void dateValidator(final TextField txt, final Text validator) {
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                String date = txt.getText();
                boolean dateOK = true;
                int counter = 0;
                for (int i = 0; i < date.length(); i++) {
                    if (date.charAt(i) == '/') {
                        counter++;
                    }
                }
                if (counter == 2) {
                    Calendar cal = getDatewithFormat(date);
                    try {
                        cal.getTime();
                        validator.setText("");

                    } catch (Exception ex) {
                        dateOK = false;

                        String mssg = ex.getMessage();
                        if (mssg.equals("DAY_OF_MONTH")) {
                            validator.setText("El valor introducido del día no es correcto.");
                        } else if (mssg.equals("MONTH")) {
                            validator.setText("El valor introducido del mes no es correcto.");
                        } else if (mssg.equals("YEAR")) {
                            validator.setText("El valor introducido del año no es correcto.");
                        }
                    }
                } else if (!date.equals("")) {
                    dateOK = false;
                }
                if (dateOK) {
                    txt.getStyleClass().remove(redTxtFieldEffect);
                } else {
                    txt.getScene().getStylesheets().add("/validatorCss.css");
                    txt.getStyleClass().add(redTxtFieldEffect);
                }
            }
        });
    }

    public void addTrailerTxtListener(final TextField trailerTxt, final Text validator) {
        //checks if  the trailer already exist
        trailerTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                //!t1 implies that the textfield lost focus->doesn't have new value             
                if (!t1) {
                    //if trailer exist :
                    Remolque trailer = getTrailerController().findRemolque(trailerTxt.getText().trim());
                    if (trailer != null) {
                        validator.setText("La matrícula del remolque ya existe. ");
                        trailerTxt.getScene().getStylesheets().add("validatorCss.css");
                        trailerTxt.getStyleClass().add(redTxtFieldEffect);
                    } else {
                        validator.setText("");
                        trailerTxt.getStyleClass().remove(redTxtFieldEffect);
                    }
                }
            }
        });
    }

    public Cliente setClientValues(Cliente client, AnchorPane pane) {
        //sets values for client class
        client.setNombre(getTextField("#nameTxt", pane).getText());
        client.setCifNif(getTextField("#clientIdTxt", pane).getText());
        client.setMovil(getTextField("#numMovTxt", pane).getText());
        client.setFijo(getTextField("#numFijoTxt", pane).getText());
        client.setEmail(getTextField("#emailTxt", pane).getText());
        client.setContacto(getTextField("#contactTxt", pane).getText());
        client.setCalle(getTextField("#streetTxt", pane).getText());
        client.setNumero(getTextField("#numberTxt", pane).getText());
        client.setPoblacion(getTextField("#townTxt", pane).getText());
        client.setProvincia(getTextField("#provTxt", pane).getText());
        client.setCodigoPostal(Integer.parseInt(getTextField("#cpTxt", pane).getText()));
        return client;
    }

    public void cleanClientPanelTextFields(AnchorPane pane) {
        getTextField("#nameTxt", pane).setText("");
        getTextField("#clientIdTxt", pane).setText("");
        getTextField("#numMovTxt", pane).setText("");
        getTextField("#numFijoTxt", pane).setText("");
        getTextField("#emailTxt", pane).setText("");
        getTextField("#contactTxt", pane).setText("");
        getTextField("#streetTxt", pane).setText("");
        getTextField("#numberTxt", pane).setText("");
        getTextField("#townTxt", pane).setText("");
        getTextField("#provTxt", pane).setText("");
        getTextField("#cpTxt", pane).setText("");
    }

    public void getCheckBox(String idCbx, AnchorPane pane) {
//looks for a checkbox by his id:
    }

    public TextField getTextField(String idTxt, AnchorPane pane) {
        //looks for a  text field by his id
        return (TextField) pane.lookup(idTxt);
    }
//looks for a text component by his id

    public Text getText(String idTxt, AnchorPane pane) {
        return (Text) pane.lookup(idTxt);
    }

    //looks for a label component by his id
    public Label getLabel(String idLbl, AnchorPane pane) {
        return (Label) pane.lookup(idLbl);
    }

    //method that fills labels to show client info:
    public void setClientLabelsInfo(AnchorPane pane, Cliente cliente) {
        getLabel("#nameLbl", pane).setText(cliente.getNombre());
        getLabel("#idLbl", pane).setText(cliente.getCifNif());
        getLabel("#mobileLbl", pane).setText(cliente.getMovil());
        getLabel("#phoneLbl", pane).setText(cliente.getFijo());
        getLabel("#emailLbl", pane).setText(cliente.getEmail());
        getLabel("#contactLbl", pane).setText(cliente.getContacto());
        getLabel("#streetLbl", pane).setText(cliente.getCalle());
        getLabel("#numberLbl", pane).setText(cliente.getNumero());
        getLabel("#townLbl", pane).setText(cliente.getPoblacion());
        getLabel("#regionLbl", pane).setText(cliente.getProvincia());
        getLabel("#cpLbl", pane).setText(Integer.toString(cliente.getCodigoPostal()));
        String dispatcher = "";
        if (cliente.getDespachante().equals("D")) {
            dispatcher = "Sí";
        } else {
            dispatcher = "No";
        }
        getLabel("#dispatcherLbl", pane).setText(dispatcher);
        //if the client has not any travel:
//        if (cliente.getNumViajes() == null) {
//            cliente.setNumViajes(0);
//        }
//        getLabel("#viajesLbl", pane).setText(cliente.getNumViajes().toString());
        getLabel("#viajesLbl", pane).setText(Integer.toString(cliente.getViajeCollection().size()));
    }

    public ViajeJpaController getTravelController() {
        if (travels == null) {
            travels = new ViajeJpaController();
        }
        return travels;
    }

    public void setDriversInfo(BorderPane panel, Transportista driver) throws IOException {
        //sets the info on the labels on the seeDriversData window:
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/apponsaresmanagement/views/truckDriver/seeDriversData.fxml");
        fxmlLoader.setLocation(resource);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fxmlLoader.load(resource.openStream());
        SeeDriversDataController controller = fxmlLoader.getController();
        controller.getNameLbl().setText(driver.getNombre());
        controller.getNifLbl().setText(driver.getNif());
        controller.getFirstSurnameLbl().setText(driver.getApellido1());
        if (driver.getApellido2() == null) {
            controller.getSecondSurnameLbl().setText("");
        } else {
            controller.getSecondSurnameLbl().setText(driver.getApellido2());
        }
        controller.getTruckRegistrationNumLbl().setText(driver.getMatriculaTractora());
        if (driver.getDirección() == null) {
            controller.getAddressLbl().setText("");
        } else {
            controller.getAddressLbl().setText(driver.getDirección());
        }
        if (driver.getMatriculaRemolque() == null) {
            controller.getTrailerRegistrationLbl().setText("");
        } else {
            controller.getTrailerRegistrationLbl().setText(driver.getMatriculaRemolque());
        }
        Collection<Viaje> travels = driver.getViajeCollection();
        controller.getTravelLbl().setText(String.valueOf(driver.getViajeCollection().size()));
        //        if (driver.getNumViajes() == null) {
        //            controller.getTravelLbl().setText("0");
        //        } else {
        //            controller.getTravelLbl().setText(driver.getNumViajes().toString());
        //        }
        String[] kmsIncome = getKilometersAndDriversIncome(travels);
        controller.getKmsLbl().setText(kmsIncome[0] + " kms.");
//        if (driver.getKms() == null) {
//            controller.getKmsLbl().setText("0 Km.");
//        } else {
//            controller.getKmsLbl().setText(driver.getKms().toString() + " km.");
//        }
//        if (driver.getIngresosTotales() == null) {
//            controller.getTotalIncomeLbl().setText("0  €");
//        } else {
//            controller.getTotalIncomeLbl().setText(driver.getIngresosTotales().toString());
//        }
        controller.getTotalIncomeLbl().setText(kmsIncome[1] + "€");
        //obtener facturado més anterior
        controller.getLastMonthIncomeLbl().setText(lastMonthDriverIncome(travels));

        if (driver.getValoración() == null) {
            controller.getAppreciationLbl().setText("Sin valorar.");
        } else {
            controller.getAppreciationLbl().setText(driver.getValoración().toString());
        }
        if (driver.getEmail() == null) {
            controller.getEmailLbl().setText("");
        } else {
            controller.getEmailLbl().setText(driver.getEmail().toString());
        }
        if (driver.getTelefono() == null) {
            controller.getPhoneNumberLbl().setText("");
        } else {
            controller.getPhoneNumberLbl().setText(driver.getTelefono());
        }
        if (!"".equals(driver.getNumCuenta())) {
            String accountNumber = driver.getNumCuenta().toString();
            controller.getEntidadLbl().setText(accountNumber.substring(0, 4));
            controller.getOfficeNumberLbl().setText(accountNumber.substring(4, 8));
            controller.getControlDigitNumberLbl().setText(accountNumber.substring(8, 10));
            controller.getAccountNumberLbl().setText(accountNumber.substring(10, 20));
        }
        controller.getNifLbl().setText(driver.getNif());
        panel.setCenter(pane);

    }

    private String[] getKilometersAndDriversIncome(Collection<Viaje> travels) {
        Double kms = new Double("0.00");
        BigDecimal driverIncome = new BigDecimal("0.00");
        for (Viaje v : travels) {
            kms = kms + Double.parseDouble(v.getKms().replace(',', '.'));
            if ("Pagado".equals(v.getEstadoTransportista())) {
                driverIncome = driverIncome.add(v.getPagoACamionero());
            }
        }
        String values[] = {kms.toString(), driverIncome.toString()};
        return values;
    }

    public String lastMonthDriverIncome(Collection<Viaje> travels) {
        BigDecimal lastMonthIncome = new BigDecimal("0.00");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int actualMonth = cal.get(Calendar.MONTH);
        for (Viaje v : travels) {
            cal.setTime(v.getFecha());
            int monthTravel = cal.get(Calendar.MONTH);
            boolean lastmonth = false;
            if (actualMonth - monthTravel == 1 || actualMonth - monthTravel == -11) {
                lastmonth = true;
            }

            if ("Pagado".equals(v.getEstadoTransportista()) && lastmonth) {
                lastMonthIncome = lastMonthIncome.add(v.getPagoACamionero());
            }
        }
        return lastMonthIncome.toString() + " €";
    }

    //fix the size of the aux window
    public void fixAuxWindow(Stage auxStage, Parent root) {
        Scene scene = new Scene(root, 400, 300);
        auxStage.setScene(scene);
        auxStage.setMaxHeight(300);
        auxStage.setMinHeight(300);
        auxStage.setMaxWidth(400);
        auxStage.setMinWidth(400);
    }

    public AnchorPane setPopUpStageOK(String title, String content) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/apponsaresmanagement/views/auxStage.fxml");
        fxmlLoader.setLocation(resource);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane panel = (AnchorPane) fxmlLoader.load(resource.openStream());
        AuxStageController auxController = fxmlLoader.getController();
        auxController.getTitleTxt().setText(title);
        auxController.getContentLbl().setText(content);
        return panel;
    }

    public void setTravelValues(Viaje v, TravelPanelController controller) throws ParseException {
        //method to set the values for the travel with the panel fields
        if (!controller.getDateTxt().getText().equals("") && !controller.getProvidedHourTxt().equals("")) {
            v.setFecha(controller.getDate());
            v.getFecha();
        }
        NumberFormat instance = DecimalFormat.getInstance();
        if (controller.getTravelValueTxt().getText() != null && !controller.getTravelValueTxt().getText().equals("")) {
            v.setIngreso(new BigDecimal(controller.getTravelValueTxt().getText()));
        }
        if (controller.getMoneyforDriverTxt().getText() != null && !controller.getMoneyforDriverTxt().getText().equals("")) {
            v.setPagoACamionero(new BigDecimal(controller.getMoneyforDriverTxt().getText()));
        }
        if (controller.getDistanceTxt().getText() != null) {
            v.setKms(controller.getDistanceTxt().getText().trim());
        }
        if (controller.getContainerTxt().getText() != null) {
            v.setNumeroContenedor(controller.getContainerTxt().getText().trim());
        }
        if (controller.getSealTxt().getText() != null) {
            v.setPrecinto(controller.getSealTxt().getText().trim());
        }
        if (controller.getContainerTypeTxt().getText() != null) {
            v.setTipoContenedor(controller.getContainerTypeTxt().getText().trim());
        }
        if (controller.getPackageTxt().getText() != null && !controller.getPackageTxt().getText().equals("")) {
            v.setBultos(Integer.parseInt(controller.getPackageTxt().getText().trim()));
        }
        if (controller.getContainerStateCkb().isSelected()) {
            v.setEstadoContenedor("lleno");
        } else {
            v.setEstadoContenedor("vacio");
        }
        if (controller.getCommodityTxt().getText() != null) {
            v.setMercancia(controller.getCommodityTxt().getText().trim());
        }
        if (controller.getWeightTxt().getText() != null && !controller.getWeightTxt().getText().equals("")) {
            v.setPeso(Double.parseDouble(controller.getWeightTxt().getText().trim()));
        }
        if (controller.getOriginTxt().getText() != null) {
            v.setOrigen(controller.getOriginTxt().getText().trim());
        }
        if (controller.getDestinationTxt() != null) {
            v.setDestino(controller.getDestinationTxt().getText().trim());
        }
        if (controller.getCollectionPlaceTxt().getText() != null) {
            v.setLugarRecogida(controller.getCollectionPlaceTxt().getText().trim());
        }
        if (controller.getLoadPlaceTxt().getText() != null) {
            v.setLugarCarga(controller.getLoadPlaceTxt().getText().trim());
        }
        if (controller.getCarrierTxt().getText() != null) {
            v.setCargador(controller.getCarrierTxt().getText().trim());
        }
        if (controller.getReferenceTxt().getText() != null) {
            v.setReferencia(controller.getReferenceTxt().getText().trim());
        }
        Calendar arriveCal = Calendar.getInstance();
        if (controller.getDate() != null) {
            arriveCal.setTime(controller.getDate());
        }
        String arriveHour = controller.getArriveHourTxt().getText().trim();
        if (!arriveHour.equals("")) {
            String[] hhMM = arriveHour.split(":");
            arriveCal.set(arriveCal.get(Calendar.YEAR), arriveCal.get(Calendar.MONTH), arriveCal.get(Calendar.DAY_OF_MONTH), Integer.parseInt(hhMM[0]), Integer.parseInt(hhMM[1]));
            v.setHoraLlegada(arriveCal.getTime());
        }
        String exitHour = controller.getExitHourTxt().getText().trim();
        if (!exitHour.equals("")) {
            String[] hhMM = exitHour.split(":");
            Calendar exitCal = Calendar.getInstance();
            exitCal.setTime(controller.getDate());
            exitCal.set(exitCal.get(Calendar.YEAR), exitCal.get(Calendar.MONTH), exitCal.get(Calendar.DAY_OF_MONTH), Integer.parseInt(hhMM[0]), Integer.parseInt(hhMM[1]));
            v.setHoraSalida(exitCal.getTime());
        }
        if (controller.getDeliveryPlaceTxt().getText() != null) {
            v.setLugarEntrega(controller.getDeliveryPlaceTxt().getText().trim());
        }
        if (controller.getPaymentTypeTxt().getText() != null && !controller.getPaymentTypeTxt().getText().equals("")) {
            v.setTipoPago(Integer.parseInt(controller.getPaymentTypeTxt().getText()));
        }
        if (controller.getShippingExpensesTxt().getText() != null) {
            v.setGastosNaviera(controller.getShippingExpensesTxt().getText().trim());
        }
        if (controller.getCustomsTxt().getText() != null) {
            v.setAduana(controller.getCustomsTxt().getText().trim());
        }
        if (controller.getOthersDescriptionTFld().getText() != null) {
            v.setOtrosGastos(controller.getOthersDescriptionTFld().getText());
        }
        if (controller.getOtherExpensesTxt().getText() != null) {
            v.setCantidadOtros(controller.getOtherExpensesTxt().getText().trim());
        }
        if (controller.getShippingTxt().getText() != null) {
            v.setNaviera(controller.getShippingTxt().getText().trim());
        }
        if (controller.getBoatTxt().getText() != null) {
            v.setBuque(controller.getBoatTxt().getText().trim());
        }
        if (controller.getObservationsTxt().getText() != null) {
            v.setObservaciones(controller.getObservationsTxt().getText().trim());
        }
        if (controller.getClientPaymentCb().getSelectionModel().getSelectedItem() != null) {
            v.setEstadoCliente(controller.getClientPaymentCb().getSelectionModel().getSelectedItem().toString());
        }
        if (controller.getDriverPaymentCb().getSelectionModel().getSelectedItem() != null) {
            v.setEstadoTransportista(controller.getDriverPaymentCb().getSelectionModel().getSelectedItem().toString());
        }
        if (controller.getClientCb().getSelectionModel().getSelectedItem() != null) {
            v.setCliente(getClienteforTravel(controller));
        }
        if (controller.getDriverCb().getSelectionModel().getSelectedItem() != null) {
            v.setTransportista(getTruckDriverController().findDriverbyNameAndSurname(controller.getDriverCb().getSelectionModel().getSelectedItem().toString()));
        }
        if (controller.getTravelTypeCb().getSelectionModel().getSelectedItem() != null) {
            String travelType = controller.getTravelTypeCb().getSelectionModel().getSelectedItem().toString().trim();
            String ivaOp = controller.getIvaCb().getSelectionModel().getSelectedItem().toString();
            if (travelType.equals("Terrestre")) {
                //we set iva 'S' if client pays iva
                v.setTipoViaje("T");
                if (ivaOp.equals("Abonado por cliente")) {
                    v.setIva("S");
                } else if (ivaOp.equals("No abonado por cliente")) {
                    v.setIva("N");
                }
            } else {
                //we set iva 'S' because there is no iva and the company has not to pay
                v.setTipoViaje("EX");
                v.setIva("S");
            }
        }
        {
            //if client pays iva:
            if (v.getIva().equals("S")) {
                v.setEstadoHacienda("Ninguno");
            } else {
                v.setEstadoHacienda(controller.getTreasuryStateCb().getSelectionModel().getSelectedItem().toString().trim());
            }

            if (controller.getDispatcherCb().getSelectionModel().getSelectedItem() != null) {
                v.setDespachante(controller.getDispatcherCb().getSelectionModel().getSelectedItem().toString().trim());
            }
            if (v.getViajePK() == null) {
                ViajePK vPK = new ViajePK(controller.generateAlbaranNumber(), v.getCliente().getCifNif(), v.getTransportista().getNif());
                v.setViajePK(vPK);
            }
        }
    }

    public Cliente getClienteforTravel(TravelPanelController controller) {
        //returns the first element of the list of clients found by name. If more than one client has the same name 
        //WE HAVE TO IMPROVE THIS METHOD: show new stage with a grid with data of clients and the user clicks one
        return getClienteController().findClienteByName(controller.getClientCb().getSelectionModel().getSelectedItem().toString()).get(0);

    }

    public String gethhmm(Date date) {
        //returns hour:minutes from a date:
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        String min = String.valueOf(cal.get(Calendar.MINUTE));
        if (min.length() < 2) {
            min = "0" + min;
        }
        return hour + ":" + min;
    }

    public String getTravelState(Viaje t) {
        /**
         * gets the state of travel at the moment
         *
         * @params Viaje : travel that is evaluated
         * @return: String with the state of the travel
         */
        String clientState = t.getEstadoCliente(), driverState = t.getEstadoTransportista();
        String treasuryState = t.getEstadoHacienda();

        boolean treasuryOk = false; //tariable for check if the treasury has paid iva or not
        if (treasuryState.equals("Pendiente")) {
            treasuryOk = false;
        } else {
            treasuryOk = true;
        }
        if (t.getFacturaidFactura() == null) {
            //if the travel is notregistered:
            return "notRegistered";
        } else if (clientState.equals("Pendiente") && checkClientDelays(t)) {
            //if the travel is not paid in date by the client:
            return "clientDelay";
        } else if (driverState.equals("Pendiente") && checkDriverDelay(t)) {
            //if the driver does not receive the payment the next month:
            return "driverDelay";
        } else if (!treasuryOk) {
            //treasury has to pay "iva"
            return "treasuryPays";
        } else if (clientState.equals("Pendiente") && !checkClientDelays(t) || driverState.equals("Pendiente") && !checkDriverDelay(t)) {
            //travel registered
            return "registered";
        } else {
            //payments ok
            return "ok";
        }

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
        if (v.getTipoPago() != null) {
            travelDateCal.add(Calendar.DAY_OF_MONTH, v.getTipoPago());
            Calendar actualDate = Calendar.getInstance();
            actualDate.setTime(new Date());
            if (travelDateCal.getTime().before(actualDate.getTime())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String generateBillNumber() {
        /**
         * method for create the number of bill. The number must have six digits
         * for the number of bill ,a '/' and the number of the year
         *
         * @return the key value of the bill
         */
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Integer year = cal.get(Calendar.YEAR);
        //Integer numberTravels = getUtils().getTravelController().getViajeCount();
        Integer numberofBills = getBillController().getBillsinYear(year.toString()) + 1;
        String bills = numberofBills.toString();
        int charsNumber = bills.length();
        if (charsNumber < 6) {
            //we add a 0 for each digit down 6
            for (int i = 6 - charsNumber; i > 0; i--) {
                bills = "0" + bills;
            }
        }
        return bills + "/" + year.toString().substring(2);

    }
     public String getoverDistClassPath() throws URISyntaxException {
        /**
         * Gets the path of the folder over disk in hierarchy
         *
         * @return absolute path of the class that is running;
         */
        File file = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        String absolutePath = file.getAbsolutePath();
        return absolutePath.substring(0, absolutePath.lastIndexOf("dist/"));
    }
}
