package mosha.infrastructure.datasource.user;

import mosha.domain.model.利用者.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by haljik on 15/06/04.
 */
@Repository
public class UserDatasource implements リポジトリ {
    @Autowired
    UserMapper mapper;

    @Override
    public 利用者 findBy(識別子 id) {
        return mapper.findBy(id);
    }

    @Override
    public Boolean isExist(識別子 id) {
        if( findBy(id) == null ) return false;
        return true;
    }

    @Override
    public 利用者概要一覧 list() {
        return new 利用者概要一覧(mapper.list());
    }

    @Override
    public 利用者 prototype() {
        return new 利用者();
    }

    @Override
    public void register(利用者 user) {
        mapper.register(user);
    }

    @Override
    public void update(利用者 user) {
        mapper.update(user);
    }

    @Override
    public void delete(利用者 user) {
        mapper.delete(user);
    }
}
