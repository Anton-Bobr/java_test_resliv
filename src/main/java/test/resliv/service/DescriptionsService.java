package test.resliv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.resliv.entity.CityEntity;
import test.resliv.entity.DescriptionsEntity;
import test.resliv.exeption.*;
import test.resliv.repository.CityRepository;
import test.resliv.repository.DescriptionsRepository;

import java.util.List;

@Service
public class DescriptionsService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DescriptionsRepository descriptionsRepository;

    public DescriptionsEntity descriptionCityAdd (DescriptionsEntity descriptionsEntity, String city)
                                                    throws CityNotFoundException, DescriptionAlreadyExistsException {
        CityEntity cityEntity = cityRepository.findByCity(city);
        if (cityEntity == null) {
            throw new CityNotFoundException("ERROR City not found");
        }
        if (descriptionsRepository.findByCityAndDescription(cityEntity, descriptionsEntity.getDescription()) != null) {
            throw new DescriptionAlreadyExistsException("ERROR Description already exists for city" + city);
        }
        descriptionsEntity.setCity(cityEntity);
        return descriptionsRepository.save(descriptionsEntity);

    }


    public List<DescriptionsEntity> getCityDescriptions (String city) throws CityNotFoundException {
        CityEntity cityEntity = cityRepository.findByCity(city);
        if (cityEntity == null) {
            throw new CityNotFoundException("ERROR City not found");
        }
        return cityEntity.getDescriptions();
    }


    public void descriptionDelete (DescriptionsEntity descriptionsEntity, String city) throws CityNotFoundException,
                                                                                                DescriptionNotFoundException {
        CityEntity cityEntity = cityRepository.findByCity(city);
        if (cityEntity == null) {
            throw new CityNotFoundException("ERROR City not found");
        }
        DescriptionsEntity descriptionsEntityDel = descriptionsRepository.findByCityAndDescription(cityEntity,
                                                                                                    descriptionsEntity.getDescription());
        if (descriptionsEntityDel == null) {
            throw new DescriptionNotFoundException("ERROR Description not found");
        }
        descriptionsRepository.delete(descriptionsEntityDel);
    }


    public DescriptionsEntity descriptionUpdate (List<DescriptionsEntity> descriptionsEntityList, String city) throws CityNotFoundException,
                                                                                                                      DescriptoinNotRenameException {

        CityEntity cityEntity = cityRepository.findByCity(city);
        if (cityEntity == null) {
            throw new CityNotFoundException("ERROR City not found");
        }
        DescriptionsEntity descriptionsEntityNew = descriptionsRepository.findByCityAndDescription(cityEntity,
                                                                        descriptionsEntityList.get(1).getDescription());
        DescriptionsEntity descriptionsEntityOld = descriptionsRepository.findByCityAndDescription(cityEntity,
                                                                        descriptionsEntityList.get(0).getDescription());

        if (descriptionsEntityList.size() != 2 ||
                descriptionsEntityList.get(0).getDescription().equals(descriptionsEntityList.get(1).getDescription()) ||
                    descriptionsEntityList.get(1).getDescription().equals("") ||
                        descriptionsEntityNew != null) {
            throw new DescriptoinNotRenameException("ERROR Description not rename");
        }
        descriptionsEntityOld.setDescription(descriptionsEntityList.get(1).getDescription());
        return descriptionsRepository.save(descriptionsEntityOld);
    }
}
