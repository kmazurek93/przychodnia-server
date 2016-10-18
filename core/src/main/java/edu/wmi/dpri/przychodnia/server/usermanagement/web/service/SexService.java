package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.server.entity.Sex;
import edu.wmi.dpri.przychodnia.server.repository.SexRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by lupus on 18.10.16.
 */
@Component
public class SexService {
    @Inject
    private SexRepository sexRepository;

    public Sex findOne(Integer id) {
        return sexRepository.findOne(id);
    }
}

