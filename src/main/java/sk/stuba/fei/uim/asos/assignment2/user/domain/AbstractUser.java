package sk.stuba.fei.uim.asos.assignment2.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * Abstraktná trieda používateľa
 *
 * @param <T> Trieda identifikátora používateľa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractUser<T> {

    @NonNull
    protected T id;

}
