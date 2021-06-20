package ru.finex.gs.proto.helios.outcome;

import ru.finex.gs.proto.network.L2GameServerPacket;
import ru.finex.gs.proto.network.Opcode;
import ru.finex.gs.proto.network.OutcomePacket;

/**
 * @author m0nster.mind
 */
@OutcomePacket(@Opcode(0x20))
public class ServerClose extends L2GameServerPacket {
    @Override
    protected void writeImpl() {
        writeC(0x20);
    }

}
