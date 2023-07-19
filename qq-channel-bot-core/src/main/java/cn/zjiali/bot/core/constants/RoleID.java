package cn.zjiali.bot.core.constants;

/**
 * @author zJiaLi
 * @since 2023-07-19 10:45
 */
public enum RoleID {
    ALL_MEMBER(1, "全体成员"),
    ADMIN(2, "管理员"),
    CREATOR(4, "群主/创建者"),
    CHANNEL_ADMIN(5, "子频道管理员"),
    ;

    private final int code;
    private final String desc;

    RoleID(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
