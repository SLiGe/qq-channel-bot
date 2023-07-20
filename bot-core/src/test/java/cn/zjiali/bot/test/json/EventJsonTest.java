package cn.zjiali.bot.test.json;

import cn.zjiali.bot.model.Member;
import cn.zjiali.bot.common.util.JsonUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author zJiaLi
 * @since 2023-07-19 13:51
 */
class EventJsonTest {

    @Test
    void testMember() throws IOException {
        String json = """
                 {
                      "joined_at": "2023-07-16T10:07:22+08:00",
                      "nick": "(:🇨🇳",
                      "nick2": "(:🇨🇳",
                      "roles": [
                        "4",
                        "14"
                      ]
                    }
                """;
        Member member = JsonUtil.fromJson(json, Member.class);
    }
}
