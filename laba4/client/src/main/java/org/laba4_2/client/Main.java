package org.laba4_2.client;

import org.laba4_2.client.sender.UpdClientController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UpdClientController controller = new UpdClientController();
        controller.sendMessage();
    }
}