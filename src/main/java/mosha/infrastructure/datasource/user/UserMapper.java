package mosha.infrastructure.datasource.user;

import mosha.domain.model.利用者.利用者;
import mosha.domain.model.利用者.識別子;
import mosha.domain.model.利用者.利用者概要;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    利用者 findBy(@Param("identifier") 識別子 id);

    List<利用者概要> list();

    void register(@Param("user") 利用者 user);

    void update(@Param("user") 利用者 user);

    void delete(@Param("user") 利用者 user);
}
