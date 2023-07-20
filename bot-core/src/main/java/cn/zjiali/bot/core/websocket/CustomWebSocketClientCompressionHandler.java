package cn.zjiali.bot.core.websocket;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.DeflateFrameClientExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.PerMessageDeflateClientExtensionHandshaker;

import static io.netty.handler.codec.http.websocketx.extensions.compression.PerMessageDeflateServerExtensionHandshaker.MAX_WINDOW_SIZE;

@ChannelHandler.Sharable
public final class CustomWebSocketClientCompressionHandler extends WebSocketClientExtensionHandler {

    public static final CustomWebSocketClientCompressionHandler INSTANCE = new CustomWebSocketClientCompressionHandler();

    private CustomWebSocketClientCompressionHandler() {
        super(new PerMessageDeflateClientExtensionHandshaker(6, ZlibCodecFactory.isSupportingWindowSizeAndMemLevel(), MAX_WINDOW_SIZE, true, true),
                new DeflateFrameClientExtensionHandshaker(false),
                new DeflateFrameClientExtensionHandshaker(true));
    }
}