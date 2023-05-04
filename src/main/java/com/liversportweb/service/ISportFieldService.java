package com.liversportweb.service;

import java.util.List;

import com.liversportweb.DTO.SportFieldDTO;

public interface ISportFieldService {
	SportFieldDTO save(SportFieldDTO sportFieldDTO);
	void delete(Long [] ids);
	List<SportFieldDTO> getAll();
	List<SportFieldDTO> getByCondition(SportFieldDTO sportFieldDTO);
	SportFieldDTO findOneById(Long id);
}
