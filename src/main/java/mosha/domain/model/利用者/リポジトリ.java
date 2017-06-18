package mosha.domain.model.利用者;

/**
 * Created by haljik on 15/06/04.
 */
public interface リポジトリ {
    利用者 findBy(識別子 id);
    Boolean isExist(識別子 id);

    利用者概要一覧 list();

    利用者 prototype();

    void register(利用者 user);

    void update(利用者 user);

    void delete(利用者 user);
}
