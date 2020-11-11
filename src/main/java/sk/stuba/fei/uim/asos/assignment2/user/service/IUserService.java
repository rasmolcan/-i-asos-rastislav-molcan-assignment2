package sk.stuba.fei.uim.asos.assignment2.user.service;

import sk.stuba.fei.uim.asos.assignment2.user.domain.AbstractUser;

import java.util.List;

/**
 * Interface pre Spring service spravujúcu používateľov
 *
 * @param <T> Trieda používateľa
 * @param <I> Trieda identifikátora používateľa
 */
public interface IUserService<T extends AbstractUser, I> {

    T add(T user);

    T update(T user);

    List<T> getAll();

    T getOne(I id);

}
