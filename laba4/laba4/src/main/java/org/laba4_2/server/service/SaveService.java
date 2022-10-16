package org.laba4_2.server.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SaveService {

    public void saveInfo(List<String> args, double result) throws URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource("log.txt").toURI());
        String str = createLog(args, result);
        try {
            Files.writeString(path, str, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            System.out.println("incorrect path");
            e.printStackTrace();
        }
    }

    private String createLog(List<String> args, double result) {
        StringBuilder sb = new StringBuilder("Args: ");
        sb.append(args.get(0)).append(" ");
        sb.append(args.get(1).toCharArray()[1]);
        sb.append(". Result: ").append(result);
        System.out.println(sb);
        return sb.toString();
    }
}
