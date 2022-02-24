package test.resliv.repository;

import org.springframework.data.repository.CrudRepository;
import test.resliv.entity.CityEntity;

public interface CityRepository extends CrudRepository <CityEntity, Long> {
    CityEntity findByCity (String city);
}
