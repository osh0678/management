package com.management.mng;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<CmmLog, String> {
	Page<CmmLog>  findByUserNameOrLogDtOrRetryCallOrRmkOrExistCash(String userName, String logDt, String retryCall, String rmk, String existCash, Pageable page);
}
