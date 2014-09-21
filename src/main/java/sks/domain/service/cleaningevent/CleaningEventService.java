package sks.domain.service.cleaningevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sks.domain.repository.cleaningevent.CleaningEventRepository;

@Service
@Transactional
public class CleaningEventService {
    @Autowired
    CleaningEventRepository cleaningEventRepository;
}
