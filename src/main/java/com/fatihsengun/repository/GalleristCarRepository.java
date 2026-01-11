package com.fatihsengun.repository;

import com.fatihsengun.model.Car;
import com.fatihsengun.model.GalleristCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleristCarRepository extends JpaRepository<GalleristCar, Long> {

	public List<GalleristCar> findByGalleristId(Long id);
	public GalleristCar findByCarId(Long id);

}
