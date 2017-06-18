package mosha.application.service.user

import mosha.TestConfiguration
import mosha.domain.model.利用者.*
import mosha.application.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.LocalDate

/**
 * Created by haljik on 15/06/04.
 */
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = TestConfiguration.class)
@ActiveProfiles("test")
class UserServiceSpec extends Specification {

    @Autowired
    UserService service;

    @Autowired
    JdbcTemplate jdbcTemplate;

    def setup() {
        jdbcTemplate.execute("DELETE FROM USERS.USERS")
        jdbcTemplate.execute("""
INSERT INTO users.users
(user_id, name, phone_number, date_of_birth, gender)
VALUES
 ('fukawa_teruyoshi@example.com', '布川 光良', '03-1234-5678','1988-04-08','男性'),
 ('kuriyama_yuino@example.com', '栗山 友以乃', '03-1234-5678','1988-04-08','女性'),
 ('fujimura_kaoru@example.com', '藤村 薫', '03-1234-5678','1988-04-08','男性'),
 ('ijuuin_ken@example.com', '伊集院 建', '03-1234-5678','1988-04-08','男性'),
 ('yamato_michiko@example.com', '大和 路子', '03-1234-5678','1988-04-08','女性'),
 ('miyake_yukiya@example.com', '三宅 有起子', '03-1234-5678','1988-04-08','女性');
                """)
    }


    def "ユーザがIDで取得できること"() {
        given:
        def id = new 識別子('ijuuin_ken@example.com')
        when:
        def user = service.findById(id)
        then:
        user.identifier().toString() == id.toString()
        user.name().toString() == '伊集院 建'
    }

    def "全ユーザが取得できること"() {
        when:
        def users = service.list();
        then:
        users.list().size() == 6;
    }

    def "ユーザーを登録できること"() {
        given:
        def user = new 利用者()
        user.識別子 = new 識別子("new_user@example.com")
        user.氏名 = new 氏名("new_user")
        user.誕生日 = new 誕生日("1989/11/21")
        user.電話番号 = new 電話番号("0120-888-888")
        user.性別 = 性別.男性
        when:
        service.register(user)
        then:
        def actual = service.findById(user.identifier())
        actual.識別子.toString() == "new_user@example.com"
        actual.氏名.toString() == "new_user"
        actual.誕生日.date.isEqual(LocalDate.of(1989,11,21))
        actual.電話番号.toString() == "0120-888-888"
        actual.性別 == 性別.男性
    }

}
