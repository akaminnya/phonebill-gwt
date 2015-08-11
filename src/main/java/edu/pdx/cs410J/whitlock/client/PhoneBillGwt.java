package edu.pdx.cs410J.whitlock.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.google.gwt.regexp.shared.RegExp;

/**
 * A basic GWT class that makes sure that we can send an Phone Bill back from the server
 */
public class PhoneBillGwt implements EntryPoint {

    private TextBox customerNameField = new TextBox();
    private TextBox callerNumberField = new TextBox();
    private TextBox calleeNumberField = new TextBox();
    private TextBox startTimeField = new TextBox();
    private TextBox endTimeField = new TextBox();
    private TextBox nameFieldTB = new TextBox();
    private TextBox startFieldTB = new TextBox();
    private TextBox endFieldTB = new TextBox();
    private DateTimeFormat formatter = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
    private FlexTable table = new FlexTable();

    public void onModuleLoad() {

        customerNameField.setMaxLength(10);

        HorizontalPanel mainTable = new HorizontalPanel();

        Button button2 = new Button("Reset fields");
        button2.setWidth("132.5px");
        button2.addClickHandler(clearFields());

        Button button = new Button("Add");
        button.addClickHandler(createNewPhoneBillOnServer());
        button.setWidth("130.5px");


        table.setStyleName("flexTable2");
        table.setWidth("40em");
        //flex.setWidget(0,0, table);
        table.setText(0, 0, "Name");
        table.setText(0, 1, "Caller Number");
        table.setText(0, 2, "Callee Number");
        table.setText(0, 3, "Start Time");
        table.setText(0, 4, "End Time");
        table.setText(0, 5, "Duration");


        Label toAdd = new Label("Add new phone call");

        VerticalPanel main = new VerticalPanel();
        FlexTable inputTable = new FlexTable();
        inputTable.setStyleName("flexTable");


        Label nameL = new Label();
        nameL.setText("Name: ");
        //nameL.setStyleName("format");
        inputTable.setWidget(0, 0, nameL);
        inputTable.setWidget(0, 1, customerNameField);

        Label callerL = new Label();
        callerL.setText("Caller Number: ");
        //callerL.setStyleName("format");
        inputTable.setWidget(1, 0, callerL);

        callerNumberField.setMaxLength(12);
        inputTable.setWidget(1, 1, callerNumberField);


        Label calleeL = new Label();
        calleeL.setText("Callee Number: ");
        //calleeL.setStyleName("format");
        inputTable.setWidget(2, 0, calleeL);

        calleeNumberField.setMaxLength(12);
        inputTable.setWidget(2, 1, calleeNumberField);

        Label startTimeL = new Label();
        startTimeL.setText("Start Time: ");
        // startTimeL.setStyleName("format");
        inputTable.setWidget(3, 0, startTimeL);

        startTimeField.setMaxLength(19);
        inputTable.setWidget(3, 1, startTimeField);

        Label endTimeL = new Label();
        endTimeL.setText("End Time: ");
        //  endTimeL.setStyleName("format");
        inputTable.setWidget(4, 0, endTimeL);

        endTimeField.setMaxLength(19);
        inputTable.setWidget(4, 1, endTimeField);
        inputTable.setWidget(5, 0, button2);
        inputTable.setWidget(5, 1, button);


        callerNumberField.getElement().setPropertyString("placeholder", "ex: 503-123-4567");
        calleeNumberField.getElement().setPropertyString("placeholder", "ex: 503-123-4567");
        startTimeField.getElement().setPropertyString("placeholder", "mm/dd/yyyy hh:mm am/pm");
        endTimeField.getElement().setPropertyString("placeholder", "mm/dd/yyyy hh:mm am/pm");
        //customerNameField.addStyleName("textBox");
        //customerNameField.getElement().getStyle().setColor("#C0C0C0");
        FlexTable searchTable = new FlexTable();
        searchTable.setStyleName("flexTable");


        Label nameSL = new Label();
        nameSL.setText("Name: ");
        //nameSL.setStyleName("format");
        searchTable.setWidget(0, 0, nameSL);

        searchTable.setWidget(0, 1, nameFieldTB);


        Label startSL = new Label();
        startSL.setText("Start Time:");
        searchTable.setWidget(1, 0, startSL);

        searchTable.setWidget(1, 1, startFieldTB);

        Label endSL = new Label();
        endSL.setText("End Time:");
        //  endSL.setStyleName("format");
        searchTable.setWidget(2, 0, endSL);

        searchTable.setWidget(2, 1, endFieldTB);
        Button searchButton = new Button("Search");
        searchButton.setWidth("135px");
        searchButton.addClickHandler(searchPhoneCalls());


        //searchTable.getFlexCellFormatter().setColSpan(3, 0, 2);
        Button searchReset = new Button("Clear");
        searchReset.setWidth("95px");
        searchTable.setWidget(3, 0, searchReset);

        searchTable.setWidget(3, 1, searchButton);
        searchReset.addClickHandler(resetSearchFields());


        startFieldTB.getElement().setPropertyString("placeholder", "mm/dd/yyyy hh:mm am/pm");
        endFieldTB.getElement().setPropertyString("placeholder", "mm/dd/yyyy hh:mm am/pm");

        main.setSpacing(20);
        main.add(inputTable);
        main.add(searchTable);


        mainTable.setStyleName("VerticalPanels");
        mainTable.add(table);

        //  main.add(table);


        Label toSearch = new Label("Search for phone calls");
        toSearch.setStyleName("Labels");
        toAdd.setStyleName("Labels");
        mainTable.add(main);
        RootPanel rootPanel = RootPanel.get();
        rootPanel.add(toAdd, 570, 30);
        rootPanel.add(toSearch, 570, 230);
        rootPanel.add(mainTable);
        rootPanel.add(main, 545, 30);


    }

    private ClickHandler resetSearchFields() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                nameFieldTB.setText("");
                startFieldTB.setText("");
                endFieldTB.setText("");
            }

        };


    }

    private ClickHandler clearFields() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                customerNameField.setText("");
                callerNumberField.setText("");
                calleeNumberField.setText("");
                startTimeField.setText("");
                endTimeField.setText("");

            }
        };


    }

    private ClickHandler searchPhoneCalls() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String customerName = nameFieldTB.getValue();
                String startTime = startFieldTB.getValue();
                String endTime = endFieldTB.getValue();
                Date start = null;
                Date end = null;
                boolean valid = true;


                boolean missing = checkMissingSearchFields(customerName, startTime, endTime);
                if (!missing) {

                    try {
                        start = formatter.parseStrict(startFieldTB.getValue());
                    } catch (IllegalArgumentException ex) {
                        Window.alert("Start time was entered in wrong format");
                        valid = false;
                    }


                    try {
                        end = formatter.parseStrict(endFieldTB.getValue());
                    } catch (IllegalArgumentException ex) {
                        Window.alert("End time was entered in wrong format");
                        valid = false;
                    }
                    if (valid) {
                        PhoneBillServiceAsync async = GWT.create(PhoneBillService.class);
                        async.searchCalls(customerName, prettyPrintCalls(start, end));
                        nameFieldTB.setText("");
                        startFieldTB.setText("");
                        endFieldTB.setText("");
                    }


                };


            }

            private AsyncCallback<AbstractPhoneBill> prettyPrintCalls(final Date start, final Date end) {
                return new AsyncCallback<AbstractPhoneBill>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        Window.alert("onFailure: " + ex.toString());
                    }

                    @Override
                    public void onSuccess(AbstractPhoneBill aBill) {
                        table.removeAllRows();
                        table.setStyleName("flexTable2");
                        table.setWidth("40em");
                        //flex.setWidget(0,0, table);
                        table.setText(0, 0, "Name");
                        table.setText(0, 1, "Caller Number");
                        table.setText(0, 2, "Callee Number");
                        table.setText(0, 3, "Start Time");
                        table.setText(0, 4, "End Time");
                        table.setText(0, 5, "Duration");
                        Window.alert("Display phone calls during this period");
                        search(start, end, aBill);

                    }

                    ;


                };
            }


        };
    }


    private ClickHandler createNewPhoneBillOnServer() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String customerName = customerNameField.getValue();
                String callerNumber = callerNumberField.getValue();
                String calleeNumber = calleeNumberField.getValue();
                String startTime = startTimeField.getValue();
                String endTime = endTimeField.getValue();
                Date start = null;
                Date end = null;
                boolean valid = true;

                boolean missing = checkMissingArgs(customerName, callerNumber, calleeNumber, startTime, endTime);

                if (!missing) {
                    if (!checkPhone(callerNumber)) {
                        Window.alert("Caller number was in incorrect format! Please re-enter!");
                        if (!checkPhone(calleeNumber))
                            Window.alert("Callee number was in incorrect format! Please re-enter!");
                        valid = false;
                    } else if (!checkPhone(calleeNumber)) {
                        Window.alert("Callee number was in incorrect format! Please re-enter!");
                        valid = false;
                    }
                    try {
                        start = formatter.parseStrict(startTimeField.getValue());
                    } catch (IllegalArgumentException ex) {
                        Window.alert("Start time was entered in wrong format");
                        valid = false;
                    }
                    try {
                        end = formatter.parseStrict(endTimeField.getValue());
                    } catch (IllegalArgumentException ex) {
                        Window.alert("End time was entered in wrong format");
                        valid = false;
                    }


                    if (valid) {
                        PhoneBillServiceAsync async = GWT.create(PhoneBillService.class);
                        PhoneCall aCall = new PhoneCall(callerNumber, calleeNumber, start, end);
                        async.addCall(customerName, aCall, displayPhoneBill());
                    }


                }
            }


            private AsyncCallback<AbstractPhoneBill> displayPhoneBill() {
                return new AsyncCallback<AbstractPhoneBill>() {

                    @Override
                    public void onFailure(Throwable ex) {
                        Window.alert("onFailure: " + ex.toString());
                    }

                    @Override
                    public void onSuccess(AbstractPhoneBill phonebill) {

                        table.removeAllRows();
                        table.setStyleName("flexTable2");
                        table.setWidth("40em");
                        //flex.setWidget(0,0, table);
                        table.setText(0, 0, "Name");
                        table.setText(0, 1, "Caller Number");
                        table.setText(0, 2, "Callee Number");
                        table.setText(0, 3, "Start Time");
                        table.setText(0, 4, "End Time");
                        table.setText(0, 5, "Duration");
                        StringBuilder sb = new StringBuilder(phonebill.toString());

                        Collection<AbstractPhoneCall> calls = phonebill.getPhoneCalls();

                        update(phonebill);


                        for (AbstractPhoneCall call : calls) {
                            sb.append(call);
                            sb.append("\n");
                        }

                        Window.alert(sb.toString());
                    }
                };
            }
        };
    }


    boolean checkMissingSearchFields(String customerName, String startTime, String endTime) {
        boolean missing = true;
        if (customerName.isEmpty()) {
            Window.alert("Missing customer name");
        } else if (startTime.isEmpty()) {
            Window.alert("Missing start time");
        } else if (endTime.isEmpty()) {
            Window.alert("Missing end time");
        } else
            missing = false;

        return missing;
    }


    boolean checkMissingArgs(String customerName, String callerNumber, String calleeNumber, String startTime, String endTime) {

        boolean missing = true;

        if (customerName.isEmpty()) {
            Window.alert("Missing customer name");
        } else if (callerNumber.isEmpty()) {
            Window.alert("Missing caller number");
        } else if (calleeNumber.isEmpty()) {
            Window.alert("Missing callee number");
        } else if (startTime.isEmpty()) {
            Window.alert("Missing start time");
        } else if (endTime.isEmpty()) {
            Window.alert("Missing end time");
        } else
            missing = false;


        return missing;

    }

    private void search(Date startTime, Date endTime, AbstractPhoneBill aBill) {
        PhoneCall call;
        int count = 1;
        for (Object o : aBill.getPhoneCalls()) {

            call = (PhoneCall) o;
            if (call.getStartTime().after(startTime) || call.getStartTime().equals(startTime)) {

                if (call.getStartTime().before(endTime) || call.getStartTime().equals(endTime)) {
                    table.setText(count, 0, aBill.getCustomer());
                    table.setText(count, 1, call.getCaller());
                    table.setText(count, 2, call.getCallee());
                    table.setText(count, 3, formatter.format(call.getStartTime()));
                    table.setText(count, 4, formatter.format(call.getEndTime()));
                    table.setText(count, 5, String.valueOf(call.getDuration()));
                    ++count;


                }

            }
        }
    }

    private void update(AbstractPhoneBill result) {
        int count = 1;
        PhoneCall call;
        for (Object o : result.getPhoneCalls()) {
            call = (PhoneCall) o;
            table.setText(count, 0, result.getCustomer());
            table.setText(count, 1, call.getCaller());
            table.setText(count, 2, call.getCallee());
            table.setText(count, 3, formatter.format(call.getStartTime()));
            table.setText(count, 4, formatter.format(call.getEndTime()));
            table.setText(count, 5, String.valueOf(call.getDuration()));
            ++count;
        }
    }

    /**
     * @param number first String
     */
    private static boolean checkPhone(String number) {
        RegExp pattern = RegExp.compile("\\d{3}-\\d{3}-\\d{4}");
        MatchResult matcher = pattern.exec(number);
        boolean match = pattern.test(number);
        if (!match) {
            return false;
        }
        return true;

    }


}
