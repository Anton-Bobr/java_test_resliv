package test.resliv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import test.resliv.entity.CityEntity;
import test.resliv.entity.DescriptionsEntity;
import test.resliv.repository.CityRepository;
import test.resliv.repository.DescriptionsRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class BotService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DescriptionsRepository descriptionsRepository;


    public String getAnswer(Message message) {
        CityEntity cityEntity = cityRepository.findByCity(message.getText());


        if (cityEntity != null) {

            List <DescriptionsEntity> descriptionsEntityList = descriptionsRepository.findAllByCity(cityEntity);
            if (descriptionsEntityList.size() != 0) {
                return descriptionsEntityList.toString().
                        replace("]", "").
                        replace("[", "").
                        replace(",", "");
            } else {
                return "City not have description";
            }
        }
        List<CityEntity> cityEntityList = (ArrayList<CityEntity>) cityRepository.findAll();
        return "Choose a city from the list: " + cityEntityList.toString().
                                                    replace("]", "").
                                                    replace("[", "").
                                                    replace(",", "");
    }
}
