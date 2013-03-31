package ChatApp;
import chatApp.winada;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class ClientFrame extends Frame implements ActionListener,Runnable
{
    TextArea a=new TextArea();
    TextField c=new TextField(25);
    Button b=new Button("Send");
    DataInputStream in;
    DataOutputStream out;
    Socket cli;
    Thread t;
    ClientFrame(String name)
    {
        super(name);
        t=new Thread(this,name);
        t.start();
        try{
        cli=new Socket("localhost",3333);
        in=new DataInputStream(cli.getInputStream());
        out=new DataOutputStream(cli.getOutputStream());
        }catch(Exception e){}
        add(a,BorderLayout.CENTER);
        add(c,BorderLayout.NORTH);
        c.requestFocusInWindow();
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
            try{ out.writeUTF(c.getText());str=a.getText()+"\nCLIENT: "+c.getText();a.setText(str);}catch(Exception e){}
            c.setText("");
        }
    }
    public void run()
    {
        String xx;
        while(true)
        {
             try{xx=in.readUTF();
             if(xx!="")
             {
            xx=a.getText()+"\nSERVER: "+xx;
            a.setText(xx);
             }}catch(Exception e){}
        }
    }
}
