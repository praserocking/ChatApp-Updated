package ChatApp;
import chatApp.winada;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class ServerFrame extends Frame implements ActionListener,Runnable
{
    Thread t=new Thread(this);
    Socket cli;
    ServerSocket serv;
    DataInputStream dat;
    DataOutputStream pw;
    TextArea a=new TextArea();
    TextField c=new TextField(25);
    Button b=new Button("Send");
    ServerFrame(String name)
    {
        super(name);
        t.start();
        add(a,BorderLayout.CENTER);
        a.setText("Waiting for client...");
        try {
            serv=new ServerSocket(3333);
            cli=serv.accept();
            dat=new DataInputStream(cli.getInputStream());
            pw=new DataOutputStream(cli.getOutputStream());
        }catch(Exception e){}
            a.setText("");
            add(c,BorderLayout.NORTH);
            a.setEditable(false);
            add(b,BorderLayout.SOUTH);
            winada x=new winada();
            addWindowListener(x);
            b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae)
    {
        Object s=ae.getSource();
        String str;
        if(s==b)
        {
            try{ pw.writeUTF(c.getText());str=a.getText()+"\nSERVER: "+c.getText();a.setText(str);}catch(Exception e){}
            c.setText("");
        }
    }
    public void run()
    {
        String xx;
        while(true)
        {
             try{xx=dat.readUTF();
             if(xx!="")
             {
            xx=a.getText()+"\nCLIENT: "+xx;
            a.setText(xx);
             }}catch(Exception e){}
        }
    }
}
