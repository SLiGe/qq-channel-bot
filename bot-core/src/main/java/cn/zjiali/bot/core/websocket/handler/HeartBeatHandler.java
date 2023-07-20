package cn.zjiali.bot.core.websocket.handler;

import cn.zjiali.bot.common.OpCode;
import cn.zjiali.bot.model.event.HeartBeatEvent;
import cn.zjiali.bot.common.util.JsonUtil;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author zJiaLi
 * @since 2023-07-19 12:50
 */
public class HeartBeatHandler extends ChannelDuplexHandler {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent idleStateEvent) {
            IdleState state = idleStateEvent.state();
            if (state.equals(IdleState.WRITER_IDLE) || state.equals(IdleState.READER_IDLE)) {
                HeartBeatEvent heartBeatEvent = new HeartBeatEvent();
                heartBeatEvent.setOp(OpCode.HEARTBEAT.getCode());
                heartBeatEvent.setD(HeartbeatInfo.s.get());
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtil.toJson(heartBeatEvent)));
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
