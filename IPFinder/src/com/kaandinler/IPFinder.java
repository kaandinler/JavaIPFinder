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
    private JLabel jResult;

    private Color foundColor;

    //Constructor
    public IPFinder(){
        // we can invoke a super method in a constructor method, so we did not type that in settingUpJFrame method.
        super("IP Finder"); // Title of the app
        //

        //start construction of the jFrame.

        jLabel = new JLabel("Enter website URL:");
        jLabel.setBounds(50,75,150,20);

        jResult = new JLabel("");
        jResult.setBounds(50,170, 250,20);

        jTextField = new JTextField();
        jTextField.setBounds(50,100,200,20);
        jTextField.setToolTipText(".com is auto added");

        jButton = new JButton("Find IP");
        jButton.setBounds(50,130, 200,30);
        jButton.addActionListener(this);

        //Adding components
        add(jLabel);
        add(jTextField);
        add(jButton);
        add(jResult);

        setSize(310,300); // setting width and height of the frame
        setLocationRelativeTo(null); // setting position of the frame
        setLayout(null);
        setVisible(true);
        //end construction of the jFrame.

        foundColor = new Color(0x4E89AE);
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
            //include .com if it hasn't included.
            String url = jTextField.getText().trim().toLowerCase();
            if(!url.endsWith(".com")){
                url += ".com";
            }else if(url.contains("\\s+")){
                url = url.replace("\\s+","");
            }
            //set url
            setUrl(url);

            InetAddress inetAddress = InetAddress.getByName(getUrl());
            IP4Address = inetAddress.getHostAddress();
            hostName = inetAddress.getHostName();

            //Showing ip address and host name that are written.
            jResult.setForeground(foundColor);
            jResult.setText(getHostName() + " : " + getIP4Address());

            }catch (UnknownHostException e) {
                e.printStackTrace();
                jResult.setForeground(Color.RED);
                jResult.setText("No such a website!");
            }
            catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,e.getMessage());
            }

    }
}
