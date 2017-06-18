package mosha.application.service;

import mosha.domain.model.利用者.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by haljik on 15/06/04.
 */
@Service
public class UserService {

    @Autowired
    リポジトリ リポジトリ;

    public 利用者 findById(識別子 id) {
        return リポジトリ.findBy(id);
    }
    public Boolean isExist(識別子 id) {return リポジトリ.isExist(id);}

    public 利用者概要一覧 list() {
        return リポジトリ.list();
    }

    public 利用者 prototype() { return リポジトリ.prototype();}

    public void register(利用者 user) {
        リポジトリ.register(user);
    }

    public void update(利用者 user) {
        リポジトリ.update(user);
    }

    public void delete(利用者 user) {
        リポジトリ.delete(user);
    }
}
