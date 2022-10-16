package org.laba4_2.client.sender;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class UpdClientController {

    private final static int PORT = 8001;
    private final static int DEMENSION_OF_BUF = 512;
    private final static String IP = "127.0.0.1";

    private byte[] intermediateByteArray;
    private final DatagramSocket socket;

    public UpdClientController() throws SocketException {
        socket = new DatagramSocket();
        intermediateByteArray = new byte[DEMENSION_OF_BUF];
        System.out.println("UDP Client started on port: " + socket.getLocalPort());
    }

    public void sendMessage() throws IOException {
        try {
            System.out.println("type 2 digits: ");
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();
            intermediateByteArray = message.getBytes(Charset.defaultCharset());
            DatagramPacket massagePacket =
                    new DatagramPacket(intermediateByteArray, intermediateByteArray.length, InetAddress.getByName(IP), PORT);
            socket.send(massagePacket);
            receiveMessage();

        } finally {
            if(!socket.isClosed()) {
                socket.close();
            }
        }
    }

    private void receiveMessage() throws IOException {
        DatagramPacket response = new DatagramPacket(intermediateByteArray, intermediateByteArray.length);
        socket.receive(response);
        System.out.println("result is: " + new String(response.getData()).trim());
    }
}
