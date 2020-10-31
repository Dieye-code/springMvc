package sn.samaneCorporation.springSenForageMvcFinal.repository;

import org.springframework.data.repository.CrudRepository;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Personne;

public interface IPersonneRepository  extends CrudRepository<Personne, Integer> {
}
