package com.kakao.pay.divvy.store.jpa.repository;

import com.kakao.pay.divvy.store.jpa.jpo.MoneyDivvyJpo;
import org.springframework.data.repository.CrudRepository;

public interface MoneyDivvyRepository extends CrudRepository<MoneyDivvyJpo, String> {
}
