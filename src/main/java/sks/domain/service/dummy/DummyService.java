package sks.domain.service.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sks.domain.model.Dummy;
import sks.domain.repository.dummy.DummyRepository;

@Service
@Transactional
public class DummyService {
    @Autowired
    DummyRepository dummyRepository;

    public Dummy findOne() {
        return dummyRepository.findOne();
    }
}
