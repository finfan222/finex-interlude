package ru.finex.auth.l2.populator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.finex.auth.l2.model.dto.UserServerDto;
import ru.finex.auth.l2.model.entity.ServerDataEntity;

import java.net.InetSocketAddress;

/**
 * @author m0nster.mind
 */
@Mapper
public interface ServerDataPopulator {

    @Mapping(target = "id", source = "persistenceId")
    @Mapping(target = "address", expression = "java(buildSocketAddress(serverData))")
    @Mapping(target = "online", ignore = true)
    @Mapping(target = "registeredAvatars", ignore = true)
    UserServerDto toUserServerDto(ServerDataEntity serverData);

    default InetSocketAddress buildSocketAddress(ServerDataEntity serverData) {
        return new InetSocketAddress(serverData.getHost(), serverData.getPort());
    }

}
