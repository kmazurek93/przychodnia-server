package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Sex;
import edu.wmi.dpri.przychodnia.server.repository.SexRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class SexService {
    @Inject
    private SexRepository sexRepository;

    @Transactional(readOnly = true)
    public Sex findOne(Integer id) {
        return sexRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Sex> getAll() {
        return newArrayList(sexRepository.findAll());
    }
}

