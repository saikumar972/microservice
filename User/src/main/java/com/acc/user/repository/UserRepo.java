package com.acc.user.repository;

import com.acc.user.entity.Userentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Userentity,Integer> {
}
