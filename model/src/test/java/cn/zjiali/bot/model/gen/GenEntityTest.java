package cn.zjiali.bot.model.gen;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zJiaLi
 * @since 2023-07-21 15:56
 */
class GenEntityTest {

    public static void main(String[] args) {
        new GenEntityTest().testGen();
    }

    @Test
    void testGen() {
        String className = "";
        String html = """
                <table><thead><tr><th>字段名</th> <th>类型</th> <th>描述</th></tr></thead> <tbody><tr><td>guild_id</td> <td>string</td> <td>频道ID</td></tr> <tr><td>channel_id</td> <td>string</td> <td>子频道ID</td></tr> <tr><td>author_id</td> <td>string</td> <td>作者ID</td></tr> <tr><td>thread_info</td> <td>object</td> <td><a href="#ThreadInfo">ThreadInfo</a> 主帖内容</td></tr></tbody></table>
                """;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类名:");
        className = scanner.nextLine();
        System.out.println("请输入表内容:");
        html = scanner.nextLine();
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
            switch (fieldType) {
                case "string" -> {
                    String temp = """
                                    /**
                            * {fieldName}	{fieldType}	{fieldDesc}
                            */
                                   @JsonProperty("{fieldName}")
                                   {fieldInfo}
                                   """;
                    fieldRow += "private String " + toCam(fieldName) + ";";
                    fieldList.add(temp.replace("{fieldName}", fieldName).replace("{fieldInfo}", fieldRow)
                            .replace("{fieldType}", fieldType)
                            .replace("{fieldDesc}", fieldDesc));
                }
                case "uint64" -> {
                    String temp = """
                                    /**
                            * {fieldName}	{fieldType}	{fieldDesc}
                            */
                                   @JsonProperty("{fieldName}")
                                   {fieldInfo}
                                   """;
                    fieldRow += "private Long " + toCam(fieldName) + ";";
                    fieldList.add(temp.replace("{fieldName}", fieldName).replace("{fieldInfo}", fieldRow)
                            .replace("{fieldType}", fieldType)
                            .replace("{fieldDesc}", fieldDesc));
                }
                case "int", "uint32" -> {
                    String temp = """
                                    /**
                            * {fieldName}	{fieldType}	{fieldDesc}
                            */
                                   @JsonProperty("{fieldName}")
                                   {fieldInfo}
                                   """;
                    fieldRow += "private Integer " + toCam(fieldName) + ";";
                    fieldList.add(temp.replace("{fieldName}", fieldName).replace("{fieldInfo}", fieldRow)
                            .replace("{fieldType}", fieldType)
                            .replace("{fieldDesc}", fieldDesc));
                }
                case "ISO8601 timestamp" -> {
                    String temp = """
                                   /**
                            * {fieldName}	{fieldType}	{fieldDesc}
                            */
                                   @JsonProperty("{fieldName}")
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
                                   {fieldInfo}
                                   """;
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
