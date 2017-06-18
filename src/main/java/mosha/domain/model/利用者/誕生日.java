package mosha.domain.model.利用者;

import mosha.domain.fundamentals.DateText;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Created by Masuda on 2016/04/02.
 */
public class 誕生日 {

    //@NotNull(message = "誕生日を入力してください。")
    LocalDate date ;
    String source;

    boolean valid = true;

    public 誕生日(@NotNull String source) {
        this.source = source;

        DateText dateText = new DateText(source);
        try {
            date = dateText.toLocalDate();
        } catch ( DateTimeException exception) {
            valid = false;
        }
    }

    public 誕生日() {
        date = null; // XXX 未指定の表現方法を検討する
        source = "";
    }

    @AssertTrue(message = "日付が正しくありません。")
    public boolean isValid() {
        if(source.equals("")) return true;
        return valid;
    }

    @Override
    public String toString() {
        if(valid) return date.toString();
        return source;
    }
}
