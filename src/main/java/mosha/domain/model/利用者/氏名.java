package mosha.domain.model.利用者;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by haljik on 15/06/04.
 */
public class 氏名 {

    @NotBlank(message = "名前を入力してください。")
    String text;

    public 氏名(String text)  {
        this.text = text;
    }

    public 氏名() {
        this.text = "";
    }

    @Override
    public String toString() {
        return text;
    }
}
