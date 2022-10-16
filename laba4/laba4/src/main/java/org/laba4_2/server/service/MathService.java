package org.laba4_2.server.service;

import java.util.List;
import java.util.stream.Collectors;

public class MathService {

    public double calculate(List<String> args) {
        if (args.size() == 2) {
            List<Double> mappedArgs = args.stream().map(Double::parseDouble).collect(Collectors.toList());
            double x = mappedArgs.get(0);
            double y = mappedArgs.get(1);
            return 5 * Math.atan(x) - 0.25 *
                    Math.cos((x + 3 * Math.abs(x - y) + Math.pow(x, 2)) /
                            (Math.pow(Math.abs(x + Math.pow(y, 2)), 3) + Math.pow(x, 3)));
        } else {
            throw new RuntimeException("incorrect size");
        }
    }
}
