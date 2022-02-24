package test.resliv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.resliv.entity.CityEntity;
import test.resliv.exeption.CityNotFoundException;
import test.resliv.exeption.CityNotRenameException;
import test.resliv.exeption.NoAnyCityException;
import test.resliv.exeption.СityAlreadyExistException;
import test.resliv.repository.CityRepository;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public CityEntity cityAdd(CityEntity cityEntity) throws СityAlreadyExistException {
        if (cityRepository.findByCity(cityEntity.getCity()) != null) {
            throw new СityAlreadyExistException("ERROR City already exists");
        }
        return cityRepository.save(cityEntity);
    }

    public List <CityEntity> getAllCities() throws NoAnyCityException {
        List <CityEntity> cityEntityArray = (List<CityEntity>) cityRepository.findAll();
        if (cityEntityArray.size() == 0) {
            throw new NoAnyCityException("ERROR Cities not found");
        }
        return cityEntityArray;
    }

    public void cityDelete(CityEntity cityEntity) throws CityNotFoundException {
        CityEntity cityEntityFull = cityRepository.findByCity(cityEntity.getCity());
        if (cityEntityFull == null) {
            throw new CityNotFoundException("ERROR City not found");
        } else {
            cityRepository.delete(cityEntityFull);
        }
    }

    public CityEntity cityUpdate(List <CityEntity> cityEntityList) throws CityNotRenameException {
        if (cityEntityList.size() == 2 &&
                ! cityEntityList.get(0).getCity().equals(cityEntityList.get(1).getCity())) {

            CityEntity cityEntityNew = cityRepository.findByCity(cityEntityList.get(1).getCity());
            CityEntity cityEntityOld = cityRepository.findByCity(cityEntityList.get(0).getCity());

            if (cityEntityOld != null && cityEntityNew == null) {
                cityEntityOld.setCity(cityEntityList.get(1).getCity());
                return cityRepository.save(cityEntityOld);
            }
        }
        throw new CityNotRenameException("ERROR City not rename, bad request");
    }
}
