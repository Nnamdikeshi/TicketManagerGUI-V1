package com.Nnamdi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nc0614dn on 11/1/2016.
 */
public class TicketGUI extends JFrame {

    private JPanel rootPanel;
    private JTextArea txtProblem;
    private JComboBox priorityCmb;
    private JTextArea txtReportedBy;
    private JButton addButton;
    private JList<Ticket> openTicketsJList;
    private JList resolvedTicketsJList;
    private JButton resolvedButton;
    private JTextArea txtHow;
    private JScrollPane openTicketScroll;
    private JScrollPane resolvedTicketScroll;
    private JLabel dateLabel;
    private JTextPane ticketGUIFormTextPane;
    private JList<Ticket> resolvedJList;
    DefaultListModel<Ticket> listModel;
    DefaultListModel<Ticket> listModel1;

    public TicketGUI () {

        super("Ticket Manager GUI V.1");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(750, 450);
        Date GUIDate = new Date();
        DateFormat df = new SimpleDateFormat ("EEE MMM dd");
        String date = df.format(GUIDate);
        dateLabel.setText(date);

     
        // Priority will show 1 to start
        priorityCmb.setSelectedItem(1);

        //create the models
        listModel = new DefaultListModel<Ticket>();
        openTicketsJList.setModel( listModel );
        openTicketsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listModel1 = new DefaultListModel<Ticket>();
        resolvedTicketsJList.setModel( listModel1 );
        resolvedTicketsJList.setSelectedIndex(0);


        addButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get our user input information
                int priority = priorityCmb.getSelectedIndex();
                String problem = txtProblem.getText();
                String reportedBy = txtReportedBy.getText();

                Date newDate = new Date();

                //clears the fields for new entry
                txtReportedBy.setText("");
                txtProblem.setText("");

                // sends info to Ticket class
                Ticket newTicket = new Ticket(priority, reportedBy, problem);
                TicketGUI.this.listModel.addElement(newTicket);

                //writes to file
                try {
                    FileWriter activeTicketWriter = new FileWriter("open_tickets.txt", true);
                    BufferedWriter activeBuffWriter = new BufferedWriter(activeTicketWriter);
                    activeBuffWriter.write(newTicket.toString());
                    activeBuffWriter.newLine();
                    activeBuffWriter.close();

                } catch (IOException ioe) {
                    //handle exceptions
                    System.out.println("Could not open or read the file.");
                    System.out.println(ioe.toString());
                }
            }
        });

        resolvedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket resolveThis = TicketGUI.this.openTicketsJList.getSelectedValue();
                TicketGUI.this.listModel.removeElement(resolveThis);
                // grabs user input and clears field
                String resolvedHow = txtHow.getText();
                txtHow.setText("");
                ResolvedTicket resolvedTicket = new ResolvedTicket (resolveThis.getPriority(), resolveThis.getReporter(), resolveThis.getDescription(), resolveThis.getDateReported(), resolvedHow);
                TicketGUI.this.listModel1.addElement(resolvedTicket);

                // Saves data to txt document
                try {
                    FileWriter resolvedTicketWriter = new FileWriter("resolved_tickets.txt", true);
                    BufferedWriter resolvedBuffWriter = new BufferedWriter(resolvedTicketWriter);
                    resolvedBuffWriter.write(resolvedTicket.toString());
                    resolvedBuffWriter.newLine();
                    resolvedBuffWriter.close();

                } catch (IOException ioe) {
                    System.out.println("Could not open or read file.");
                    System.out.println(ioe.toString());
                }
            }
        });
    }
}
