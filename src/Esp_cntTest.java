import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class Esp_cnt {
    private Socket socket;
    private InputStream inStream;
    private OutputStream outStream;
    public Esp_cnt(String ip_addr,int port) throws IOException {
        socket = new Socket(ip_addr,port);
        inStream= socket.getInputStream();
        outStream= socket.getOutputStream();
    }
    public void send(String msg){
        try (PrintWriter out=new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8),true)){
            out.println(msg);
        }
    }
    public String read(){
        try(Scanner in=new Scanner(inStream, String.valueOf(StandardCharsets.UTF_8))){
            if(in.hasNextLine()){
                return in.nextLine();
            }
        }
        return "";
    }
    public static void main() throws IOException {
        Esp_cnt esp=new Esp_cnt("192.168.43.148",80);
        esp.send("DQ");
    }
}

public class Esp_cntTest{
    public static void main(String[] args) throws IOException, InterruptedException {
        Esp_cnt esp=new Esp_cnt("192.168.43.148",80);
        esp.send("DQ");
        Thread.sleep(2000);
    }
}
