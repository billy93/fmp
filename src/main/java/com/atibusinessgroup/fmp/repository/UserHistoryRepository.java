package com.atibusinessgroup.fmp.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.UserHistory;

/**
 * Spring Data MongoDB repository for the User entity.
 */
@Repository
public interface UserHistoryRepository extends MongoRepository<UserHistory, String> {

    Optional<UserHistory> findOneByActivationKey(String activationKey);

    List<UserHistory> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime);

    Optional<UserHistory> findOneByResetKey(String resetKey);

    Optional<UserHistory> findOneByEmailIgnoreCase(String email);

    Optional<UserHistory> findOneByLogin(String login);

    Page<UserHistory> findAllByLoginNot(Pageable pageable, String login);
}
