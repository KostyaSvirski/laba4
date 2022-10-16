package org.laba4_2.server.controller;

import org.laba4_2.server.service.MathService;
import org.laba4_2.server.service.SaveService;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UdpController {

    private final static int PORT = 8001;
    private final static int DEMENSION_OF_BUF = 512;

    private final MathService mathService;
    private final SaveService saveService;
    private final byte[] intermediateByteArray;
    private final DatagramSocket socket;

    public UdpController() throws SocketException {
        socket = new DatagramSocket(PORT);
        intermediateByteArray = new byte[DEMENSION_OF_BUF];
        mathService = new MathService();
        saveService = new SaveService();
        System.out.println("UPD server started on port: " + socket.getLocalPort());
    }

    public void receiveMessage() throws IOException {
        try {
            DatagramPacket messagePacket = new DatagramPacket(intermediateByteArray, intermediateByteArray.length);
            socket.receive(messagePacket);
            List<String> args = Arrays.stream(new String(messagePacket.getData()).split(","))
                    .filter(s -> !s.equals(" ")).collect(Collectors.toList());
            double result = mathService.calculate(args);
            System.out.println(result);
            sendResponse(messagePacket, Double.toString(result));
            saveService.saveInfo(args, result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (!socket.isClosed()) {
                socket.close();
            }
        }
    }

    private void sendResponse(DatagramPacket messagePacket, String result) throws IOException {
        DatagramPacket responsePacket =
                new DatagramPacket(intermediateByteArray, 0, messagePacket.getAddress(), messagePacket.getPort());
        System.arraycopy(result.getBytes(Charset.defaultCharset()), 0, intermediateByteArray, 0,
                result.length());
        responsePacket.setData(intermediateByteArray);
        responsePacket.setLength(result.length());
        socket.send(responsePacket);
    }


}
