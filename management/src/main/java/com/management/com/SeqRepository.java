package com.management.com;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeqRepository extends JpaRepository<MasterSeq, String>{
}

