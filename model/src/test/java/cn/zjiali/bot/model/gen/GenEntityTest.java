package cn.zjiali.bot.model.gen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zJiaLi
 * @since 2023-07-21 15:56
 */
class GenEntityTest {

    @Test
    void testGen() throws URISyntaxException, IOException {
        String fieldListJson = Files.readString(Paths.get("src/test/resources/field.json"));
        List<JsonNode> jsonNodes = new ObjectMapper().readValue(fieldListJson, new TypeReference<List<JsonNode>>() {
        });
        jsonNodes.forEach(jsonNode -> {
            String className = jsonNode.get("className").asText();
            String html = jsonNode.get("tableHtml").asText();
            parseClass(className, html);
        });

    }

    private void parseClass(String className, String html) {
        Document doc = Jsoup.parse(html);
        Element table = doc.select("table").first();
        Elements rows = table.select("tr");
        List<String> fieldList = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cells = row.select("td");
            String fieldName = cells.get(0).text();
            String fieldType = cells.get(1).text();
            String fieldDesc = cells.get(2).text();
            String fieldRow = """
                    """;
            String temp = """
                            /**
                    * {fieldName}	{fieldType}	{fieldDesc}
                    */
                           @JsonProperty("{fieldName}")
                           {fieldInfo}
                           """;
            switch (fieldType) {
                case "string" -> {

                    fieldRow += "private String " + toCam(fieldName) + ";";
                    fieldList.add(temp.replace("{fieldName}", fieldName).replace("{fieldInfo}", fieldRow)
                            .replace("{fieldType}", fieldType)
                            .replace("{fieldDesc}", fieldDesc));
                }
                case "uint64" -> {
                    fieldRow += "private Long " + toCam(fieldName) + ";";
                    fieldList.add(temp.replace("{fieldName}", fieldName).replace("{fieldInfo}", fieldRow)
                            .replace("{fieldType}", fieldType)
                            .replace("{fieldDesc}", fieldDesc));
                }
                case "int", "uint32" -> {
                    fieldRow += "private Integer " + toCam(fieldName) + ";";
                    fieldList.add(temp.replace("{fieldName}", fieldName).replace("{fieldInfo}", fieldRow)
                            .replace("{fieldType}", fieldType)
                            .replace("{fieldDesc}", fieldDesc));
                }
                case "ISO8601 timestamp" -> {
                    fieldRow += "private LocalDateTime " + toCam(fieldName) + ";";
                    fieldList.add(temp.replace("{fieldName}", fieldName).replace("{fieldInfo}", fieldRow)
                            .replace("{fieldType}", fieldType)
                            .replace("{fieldDesc}", fieldDesc));
                }
            }
        }
        String classText = """
                public class {className} {
                    {fieldContent}
                }
                """.replace("{fieldContent}", String.join("\n", fieldList)).replace("{className}", className);
        System.out.println(classText);
    }

    private String toCam(String input) {
        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '_') {
                capitalizeNext = true;
            } else {
                sb.append(capitalizeNext ? Character.toUpperCase(c) : c);
                capitalizeNext = false;
            }
        }
        return sb.toString();
    }
}
