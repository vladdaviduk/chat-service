package com.chatty.repository;

import com.chatty.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("select c from Chat c where c.firstOwner=?1 or c.secondOwner=?1")
    List<Chat> findByOwner(String owner);

    @Query("select c from Chat c where (c.firstOwner=?1 and c.secondOwner=?2) " +
            "or (c.firstOwner=?2 and c.secondOwner=?1)")
    Optional<Chat> findByBothOwners(String owner, String participant);
}
