package org.laba4_2.server;

import org.laba4_2.server.controller.UdpController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UdpController controller = new UdpController();
        controller.receiveMessage();
    }
}