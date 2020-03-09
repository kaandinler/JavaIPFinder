package com.kaandinler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPFinder extends JFrame implements ActionListener {

    private String IP4Address;
    private String hostName;
    private String url;

    private JLabel jLabel;
    private JTextField jTextField;
    private JButton jButton;
    JLabel jResult;

    //Constructor
    public IPFinder() throws Exception {
        // we can invoke a super method in a constructor method, so we did not type that in settingUpJFrame method.
        super("IP Finder"); // Title of the app
        //
        settingUpJFrame();
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public String getIP4Address(){
        return IP4Address;
    }

    public String getHostName(){
        return hostName;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        try {
            //when button is clicked, String that is written in the textfield is sent to url variable;
            setUrl(jTextField.getText());

            InetAddress inetAddress = InetAddress.getByName(getUrl());
            IP4Address = inetAddress.getHostAddress();
            hostName = inetAddress.getHostName();

            //Showing ip address and host name that are written.
            jResult.setText(getHostName() + " : " + getIP4Address());

            }catch (UnknownHostException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,e.getMessage());
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }

    }

    // jframe screen is constructed by this method
    private void settingUpJFrame(){

        jLabel = new JLabel("Enter website URL:");
        jLabel.setBounds(50,70,150,20);

        jResult = new JLabel("");
        jResult.setBounds(50,170, 250,20);

        jTextField = new JTextField();
        jTextField.setBounds(50,100,200,20);

        jButton = new JButton("Find IP");
        jButton.setBounds(50,130, 200,30);
        jButton.addActionListener(this);

        //Adding component to JFrame that are created
        add(jLabel);
        add(jTextField);
        add(jButton);
        add(jResult);

        setSize(400,300); // setting width and height of the frame
        setLocationRelativeTo(null); // setting position of the frame
        setLayout(null);
        setVisible(true);
    }


}
