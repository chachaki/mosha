package mosha.domain.model.利用者;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by haljik on 15/06/04.
 */
public class 利用者 {
    @Valid
    識別子 識別子;

    @Valid
    氏名 氏名;

    @Valid
    誕生日 誕生日;

    @NotNull(message = "性別を選択してください。")
    性別 性別;

    @Valid
    電話番号 電話番号;

    public 識別子 identifier() {return 識別子;}
    public 氏名 name() {
        return 氏名;
    }

    public 誕生日 dateOfBirth() {
        return 誕生日;
    }

    public 性別 gender() {
        return 性別;
    }

    public 電話番号 phoneNumber() {
        return 電話番号;
    }

    @Override
    public String toString() {
        return "利用者{" +
                "identifier=" + 識別子 +
                ", 氏名=" + 氏名 +
                ", 誕生日=" + 誕生日 +
                ", 電話番号=" + 電話番号 +
                ", gender=" + 性別 +
                '}';
    }
}
