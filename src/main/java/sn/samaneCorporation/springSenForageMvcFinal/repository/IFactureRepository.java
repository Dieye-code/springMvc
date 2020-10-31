package sn.samaneCorporation.springSenForageMvcFinal.repository;

import org.springframework.data.repository.CrudRepository;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Facture;

public interface IFactureRepository extends CrudRepository<Facture, Integer> {
}
