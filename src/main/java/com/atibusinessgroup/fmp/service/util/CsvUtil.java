package com.atibusinessgroup.fmp.service.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CsvUtil {

    private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(StringBuilder writer, List<String> values) throws IOException {
        writeLine(writer, values, DEFAULT_SEPARATOR, ' ');
    }
    private static String followCSVFormat(String value) {
        String result = value;
        if(result.contains("\"")){
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

    public static void writeLine(StringBuilder w, List<String> values, char separators, char customQuote) throws IOException {
        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(String value : values) {
            if(!first) {
                stringBuilder.append(separators);
            }

            if(customQuote == ' ') {
                stringBuilder.append(followCSVFormat(value));
            } else {
                stringBuilder.append(customQuote).append(followCSVFormat(value)).append(customQuote);
            }

            first = false;
        }

        stringBuilder.append("\n");
        w.append(stringBuilder.toString());
    }

}
