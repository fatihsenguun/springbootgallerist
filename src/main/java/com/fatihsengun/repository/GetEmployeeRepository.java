package com.fatihsengun.repository;

import com.fatihsengun.model.Gallerist;
import com.fatihsengun.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetEmployeeRepository extends JpaRepository<User,Long> {
   public List<User> findByGallerist(Gallerist gallerist);
}
