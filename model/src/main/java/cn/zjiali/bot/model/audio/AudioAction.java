package cn.zjiali.bot.model.audio;

/**
 * @author zJiaLi
 * @since 2023-07-21 15:42
 */
public class AudioAction {
    /**
     * guild_id	string	频道id
     */
    private String guildId;
    /**
     * channel_id	string	子频道id
     */
    private String channelId;
    /**
     * audio_url	string	音频数据的url status为0时传
     */
    private String audioUrl;
    /**
     * text	string	状态文本（比如：简单爱-周杰伦），可选，status为0时传，其他操作不传
     */
    private String text;

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
