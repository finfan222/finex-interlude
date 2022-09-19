package ru.finex.ws.l2.network;

import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.RequiredArgsConstructor;
import ru.finex.core.network.codec.NetworkDtoDecoder;
import ru.finex.core.network.codec.NetworkDtoEncoder;
import ru.finex.network.netty.model.AbstractNetworkPipeline;
import ru.finex.ws.l2.network.codec.LengthFieldBasedFrameEncoder;
import ru.finex.ws.l2.network.codec.PayloadCodec;

import java.nio.ByteOrder;
import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author m0nster.mind
 */
@RequiredArgsConstructor(onConstructor_ = { @Inject })
public class NetworkPipeline extends AbstractNetworkPipeline {

    private static final int MAX_PACKET_SIZE = 16*1024;
    private static final int PACKET_HEADER_LENGTH = 2;


    private final NetworkClientFactory clientFactory;
    private final NetworkDtoEncoder encoder;
    private final Provider<NetworkDtoDecoder> decoderProvider;

    /*
    --------------
     HEADERS      | - meta-data
    --------------
     PAYLOAD      | - data
    --------------

    --------------
     packet length (2 byte, unsigned short)
    --------------
     ENCRYPTED PAYLOAD
    --------------

    --------------
     packet length
    --------------
     opcode (1 byte, unsigned byte)
     data (зависит от пакета)
    --------------

    data -> object (HuiPacket)


    Serializer/Deserializer - transforms bytes to object and object to bytes
    DTO - contains network data in humanable view

    Serializer<T where DTO>,               Deserializer<T where DTO>
    producers bytes from objects           produces object from bytes
     */

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, MAX_PACKET_SIZE, 0, PACKET_HEADER_LENGTH, -PACKET_HEADER_LENGTH, PACKET_HEADER_LENGTH, false));
        ch.pipeline().addLast(new LengthFieldBasedFrameEncoder(MAX_PACKET_SIZE, PACKET_HEADER_LENGTH));

        ch.pipeline().addLast("crypt", new PayloadCodec());

        ch.pipeline().addLast(decoderProvider.get());
        ch.pipeline().addLast(encoder);

        ch.pipeline().addLast(clientFactory.create());
    }
}
