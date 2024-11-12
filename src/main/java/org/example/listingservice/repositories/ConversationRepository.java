package org.example.listingservice.repositories;


import org.example.listingservice.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findBySender_IdAndReceiver_Id(Long senderId, Long receiverId);
    Optional<Conversation> findBySender_IdOrReceiver_Id(Long senderId, Long receiverId);
    Boolean existsBySender_IdAndReceiver_Id(Long senderId, Long receiverId);
    List<Conversation> findAllBySender_IdOrReceiver_Id(Long senderId, Long receiverId);
}
