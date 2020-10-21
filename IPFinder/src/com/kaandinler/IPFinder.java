package com.kaandinler;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPFinder extends JFrame implements ActionListener {

    private String IP4Address;
    private String hostName;
    private String url;

    private final JTextField jTextField;
    private final JLabel jResult;

    private final Color foundColor;

    //Constructor
    public IPFinder(){
        // we can invoke a super method in a constructor method, so we did not type that in settingUpJFrame method.
        super("IP Finder"); // Title of the app
        //

        //start construction of the jFrame.

        JLabel jLabel = new JLabel("Enter website URL: (click on the result to copy)");
        jLabel.setBounds(80,75,300,20);

        jResult = new JLabel("");
        jResult.setBounds(110,170, 250,20);

        jTextField = new JTextField();
        jTextField.setBounds(80,100,250,25);
        jTextField.setToolTipText(".com is auto added");

        JButton jButton = new JButton("Find IP");
        jButton.setBounds(110,130, 170,30);
        jButton.addActionListener(this);

        //Adding components
        add(jLabel);
        add(jTextField);
        add(jButton);
        add(jResult);

        //set the screen size
        setSize(410,280); // setting width and height of the frame
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
            if(!url.endsWith(".com")) url += ".com";

            url = url.replace(" ","");
            //set url
            setUrl(url);

            InetAddress inetAddress = InetAddress.getByName(getUrl());
            IP4Address = inetAddress.getHostAddress();
            hostName = inetAddress.getHostName();

            //Showing ip address and host name that are written.
            jResult.setForeground(foundColor);
            jResult.setText(getHostName()+ " : " + getIP4Address());

            //copy the result when clicked
            jResult.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    StringSelection selection = new StringSelection(getIP4Address());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                }
            });
            //catch there is no such a website
            }catch (UnknownHostException e) {
                e.printStackTrace();
                jResult.setForeground(Color.RED);
                jResult.setText("No such a website!");
            }
            //catch other exceptions
            catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,e.getMessage());
            }

    }
}
