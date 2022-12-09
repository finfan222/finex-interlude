package ru.finex.ws.l2.repository;

import ru.finex.core.repository.CrudRepository;
import ru.finex.core.repository.NamedQuery;
import ru.finex.core.repository.Query;
import ru.finex.ws.l2.model.entity.AvatarPrototypeView;
import ru.finex.ws.l2.model.entity.AvatarView;

import java.util.List;
import java.util.Optional;

/**
 * @author m0nster.mind
 */
public interface AvatarRepository extends CrudRepository<AvatarView, Integer> {

    @Query("SELECT avatar FROM AvatarView avatar WHERE avatar.login = :login")
    List<AvatarView> findByLogin(String login);

    @NamedQuery
    Optional<AvatarPrototypeView> findPrototypeByName(String name);

}
