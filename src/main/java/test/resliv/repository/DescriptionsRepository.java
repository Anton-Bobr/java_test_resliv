package test.resliv.repository;

import org.springframework.data.repository.CrudRepository;
import test.resliv.entity.CityEntity;
import test.resliv.entity.DescriptionsEntity;

import java.util.List;

public interface DescriptionsRepository extends CrudRepository <DescriptionsEntity, Long> {

    DescriptionsEntity findByCityAndDescription(CityEntity city, String description);
    List<DescriptionsEntity> findAllByCity (CityEntity city);
}
