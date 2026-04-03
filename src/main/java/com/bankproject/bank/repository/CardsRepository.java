package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Accounts;
import com.bankproject.bank.entity.Cards;
import com.bankproject.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardsRepository extends JpaRepository<Cards, Long> {
    List<Cards> findByAccount(Accounts account);

    @Procedure(procedureName = "freeze_card")
    void freezeCard(@Param("_card_id") Long cardId);

    @Procedure(procedureName = "un_freeze_card")
    void unFreezeCard(@Param("_card_id") Long cardId);

    @Procedure(procedureName = "new_card")
    void newCard(@Param("_account_id") Long accountId, @Param("_card_type") String cardType);

    @Procedure(procedureName = "delete_card")
    void deleteCard(@Param("_card_id") Long cardId);
}
