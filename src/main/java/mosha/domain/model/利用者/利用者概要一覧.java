package mosha.domain.model.利用者;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Collections;
import java.util.List;

/**
 * Created by haljik on 15/06/04.
 */
public class 利用者概要一覧 {
    final List<利用者概要> values;

    public 利用者概要一覧(List<利用者概要> values) {
        this.values = values;
    }

    public List<利用者概要> list() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
