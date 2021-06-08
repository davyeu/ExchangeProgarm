/**
 * David BM 302518097
 *
 * Full documentation is in MultiServer class file.
 */

package O1.Client;
import O1.ExchangeRequest;
import O1.Server.ExchangeRate;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Client extends JFrame implements Runnable,ActionListener, ChangeListener {
    /** variables related to communication */

    private Socket socket=null;
    private ObjectOutputStream output;
    private Scanner input;
    private String host="localhost";
    private ExchangeRequest exchangeRequest;

    /** variables related to graphic user interface */
    String [] coinsNames1={"USD","EUR","GBP","JPY","RUB","NIS"};
    String [] coinsNames2={"NIS","USD","EUR","GBP","JPY","RUB"};
    JComboBox fromCoins;
    JComboBox  toCoins;
    JSpinner spinner;
    JLabel resLabel;
    JButton exChangeBtn;

    public Client()  {
        super("exchange money");
        /** orders related to GUI */
        fromCoins = new JComboBox(coinsNames1);
        toCoins= new JComboBox(coinsNames2);
        fromCoins.addActionListener(this);
        toCoins.addActionListener(this);
        JLabel fromLabel=new JLabel();
        fromLabel.setText("from");
        JLabel toLabel = new JLabel();
        toLabel.setText("to");
        JPanel FromPanel=new JPanel();
        FromPanel.setLayout(new GridLayout(2,1));
        JPanel ToPanel=new JPanel();
        ToPanel.setLayout(new GridLayout(2,1));
        FromPanel.add(fromLabel);
        FromPanel.add(fromCoins);
        ToPanel.add(toLabel);
        ToPanel.add(toCoins);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(FromPanel);
        panel.add(ToPanel);
        SpinnerModel value =
                new SpinnerNumberModel(1, //initial value
                        0, //minimum value
                        150, //maximum value
                        1); //step
        spinner = new JSpinner(value);
        spinner.addChangeListener(this);
        JLabel numLabel=new JLabel();
        numLabel.setText("choose the amount to exchange");
        numLabel.getHorizontalAlignment();
        exChangeBtn=new JButton();
        exChangeBtn.setText("press here to exchange");
        exChangeBtn.addActionListener(this);
        JPanel panel2=new JPanel();
        panel2.setLayout(new GridLayout(2,1));
        panel2.add(panel);
        panel2.add(exChangeBtn);
        add(panel2, BorderLayout.NORTH);

        JPanel amountPanel=new JPanel();
        amountPanel.add( numLabel);
        amountPanel.add(spinner);
        resLabel=new JLabel();
        resLabel.getHorizontalAlignment();

        add(amountPanel,BorderLayout.CENTER);
        add( resLabel,BorderLayout.SOUTH);
        setSize(300, 175);
        setVisible(true);


    }

    /**
     * create socket in order to connect the server
     * and execute the current thread
     */
    public void startClient(){
        try {
            socket = new Socket(host, 7777);
            //set the fixed streams for the client needs
            output=new ObjectOutputStream(socket.getOutputStream());
            input = new Scanner(socket.getInputStream());
        } catch (UnknownHostException e) {
            System.out.println("Don't know about host: "+host);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Couldn't get I/O for the connection to: "+host);
            System.exit(1);
        }
        ExecutorService worker= Executors.newFixedThreadPool(1);
        worker.execute(this);
    }

    @Override
    public void run() {
        System.out.println("after connections");
        try {
            //send the exchangeRequest to the server
            output.writeObject(exchangeRequest);
            output.flush();
            double exchangeRes; // receive the result from the server and put her in this variable
            while(input.hasNextDouble())
            {
                    exchangeRes = input.nextDouble();
                System.out.println("The Thread name is " + Thread.currentThread().getName());
                resLabel.setText("the exchange from  " +String.valueOf(exchangeRequest.getNum())+" "+
                        exchangeRequest.getFrom() +
                        " to  " + exchangeRequest.getTo() + " is: " +
                        String.valueOf(exchangeRes*exchangeRequest.getNum()));
                    System.out.print("the exchange from  " +String.valueOf(exchangeRequest.getNum())+" "+
                            exchangeRequest.getFrom() +
                            " to  " + exchangeRequest.getTo() + " is: " +
                            String.valueOf(exchangeRes*exchangeRequest.getNum())+"\n");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        finally {
            input.close();
            try {
                socket.close();
                output.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==exChangeBtn){
            String from= (String) fromCoins.getSelectedItem();
            String to=(String) toCoins.getSelectedItem();
            Double num=new Double((int)spinner.getValue());
            exchangeRequest =new ExchangeRequest(from,to,num);
        }
        startClient();

    }
    public void stateChanged(ChangeEvent e) {

    }
}
