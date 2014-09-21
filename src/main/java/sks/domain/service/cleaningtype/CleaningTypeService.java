package sks.domain.service.cleaningtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sks.domain.repository.cleaningtype.CleaningTypeRepository;

@Service
@Transactional
public class CleaningTypeService {
    @Autowired
    CleaningTypeRepository cleaningTypeRepository;
}
