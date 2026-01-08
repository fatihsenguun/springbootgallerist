package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoGallerist;
import com.fatihsengun.dto.DtoGalleristIU;
import com.fatihsengun.model.RootEntity;

public interface IRestGalleristController {
	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

}
