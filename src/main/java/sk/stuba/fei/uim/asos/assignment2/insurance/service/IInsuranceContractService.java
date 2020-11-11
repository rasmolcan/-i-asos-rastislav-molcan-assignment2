package sk.stuba.fei.uim.asos.assignment2.insurance.service;

import java.util.List;

/**
 * Interface pre Spring service pre správu poistných zmlúv.
 *
 * @param <T> Trieda poistnej zmluvy
 */
public interface IInsuranceContractService<T> {

    T create(T contract);

    T update(T contract);

    List<T> getAll();

    List<T> getByUserId(Long userId);

}
