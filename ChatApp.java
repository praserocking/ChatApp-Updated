package ChatApp;
import ChatApp.ClientFrame;
import ChatApp.ServerFrame;
public class ChatApp implements Runnable
{
    ChatApp()
    {
        Thread t=new Thread(this);
        t.start();
    }
    public void run()
    {
        ClientFrame a1=new ClientFrame("CLIENT");
        a1.setVisible(true);
        a1.setSize(300,200);
    }
    public static void main(String[] Arg)
    {
        new ChatApp();
        ServerFrame a=new ServerFrame("SERVER");
        a.setVisible(true);
        a.setSize(300,200);
    }
}
