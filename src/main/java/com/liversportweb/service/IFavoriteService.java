package com.liversportweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liversportweb.DTO.FavoriteDTO;
import com.liversportweb.DTO.SportFieldDTO;

@Service
public interface IFavoriteService {
	List <SportFieldDTO> getAllFavoriteSportFieldByUser(Long id);
	boolean save(FavoriteDTO dto);
	SportFieldDTO delete(Long id);
}
