package com.mkm.empiktask.repository;

import com.mkm.empiktask.model.UserRequestCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestCountRepository extends JpaRepository<UserRequestCount, String> {

    @Modifying
    @Query(value = "UPDATE UserRequestCount urc SET urc.requestCount = urc.requestCount + 1 WHERE urc.login = :login")
    void incrementRequestCounterByOne(final String login);
}

