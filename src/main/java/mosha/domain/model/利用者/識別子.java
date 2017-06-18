package mosha.domain.model.利用者;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by haljik on 15/06/04.
 */
public class 識別子 {

    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスが正しくありません。")
    String mail;

    public 識別子(@NotNull String mail) {
        this.mail = mail;
    }

    public 識別子() {
        this.mail = "";
    }

    public String mail() { return mail;}

    @Override
    public String toString() {
        return mail;
    }
}
