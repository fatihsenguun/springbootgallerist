package com.fatihsengun.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoGallerist;
import com.fatihsengun.dto.DtoGalleristIU;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.Gallerist;
import com.fatihsengun.repository.GalleristRepository;
import com.fatihsengun.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private IGlobalMapper globalMapper;

	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {

		Gallerist gallerist = globalMapper.toGalleristEntity(dtoGalleristIU);
		Date currentDate = new Date();
		gallerist.setCreateTime(currentDate);
		gallerist.getAddress().setCreateTime(currentDate);
		Gallerist response = galleristRepository.save(gallerist);
		return globalMapper.toGalleristDto(response);
	}

}
