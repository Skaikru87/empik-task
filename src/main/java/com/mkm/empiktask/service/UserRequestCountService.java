package com.mkm.empiktask.service;

import com.mkm.empiktask.model.UserRequestCount;
import com.mkm.empiktask.repository.UserRequestCountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserRequestCountService {

    final UserRequestCountRepository userRequestCountRepository;

    public UserRequestCountService(UserRequestCountRepository userRequestCountRepository) {
        this.userRequestCountRepository = userRequestCountRepository;
    }

    @Transactional
    public void saveOrIncrementUserRequestCountByLogin(String login) {

        if (userRequestCountRepository.existsById(login)) {
            userRequestCountRepository.incrementRequestCounterByOne(login);
        } else {
            userRequestCountRepository.save(UserRequestCount.createFirst(login));
        }
    }
}
