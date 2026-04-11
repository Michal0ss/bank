package com.bankproject.bank.repository;

import com.bankproject.bank.entity.Branches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchesRepository extends JpaRepository<Branches, Long> {
}
