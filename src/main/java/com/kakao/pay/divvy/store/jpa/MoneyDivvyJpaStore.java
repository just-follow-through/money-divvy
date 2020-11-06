package com.kakao.pay.divvy.store.jpa;

import com.kakao.pay.divvy.model.domain.MoneyDivvy;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.model.store.MoneyDivvyStore;
import com.kakao.pay.divvy.store.jpa.jpo.MoneyDivvyJpo;
import com.kakao.pay.divvy.store.jpa.repository.MoneyDivvyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class MoneyDivvyJpaStore implements MoneyDivvyStore {

    private final MoneyDivvyRepository activeTicketRepository;

    @Override
    public void save(MoneyDivvy moneyDivvy) {

        this.activeTicketRepository.save(new MoneyDivvyJpo(moneyDivvy));

    }

    @Override
    public Optional<MoneyDivvy> find(ReceivableToken token) {
        return this.activeTicketRepository.findById(token.toString())
                .map(MoneyDivvyJpo::toDomain);
    }
}
