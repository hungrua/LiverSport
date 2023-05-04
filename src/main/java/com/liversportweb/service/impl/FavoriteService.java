package com.liversportweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liversportweb.DTO.FavoriteDTO;
import com.liversportweb.DTO.SportFieldDTO;
import com.liversportweb.converter.SportFieldConverter;
import com.liversportweb.entity.FavoriteEntity;
import com.liversportweb.entity.SportFieldEntity;
import com.liversportweb.entity.UserEntity;
import com.liversportweb.repository.FavoriteRepository;
import com.liversportweb.repository.SportFieldRepository;
import com.liversportweb.repository.UserRepository;
import com.liversportweb.service.IFavoriteService;
@Service
public class FavoriteService implements IFavoriteService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	SportFieldRepository sportFieldRepository;
	@Autowired
	FavoriteRepository favoriteRepository;
	
	@Autowired
	SportFieldConverter sportFieldConverter;
	@Override
	public List<SportFieldDTO> getAllFavoriteSportFieldByUser(Long id) {
		List<FavoriteEntity> listFavorite = favoriteRepository.getAllByUser(id);
		List <SportFieldDTO> result = new ArrayList<>();
		for(FavoriteEntity x : listFavorite) {
			SportFieldEntity tmp = x.getFavorite();
			result.add(sportFieldConverter.toDTO(tmp));
		}
		return result;
	}
	@Override
	public boolean save(FavoriteDTO dto) {
		if(favoriteRepository.findByUserAndSportField(dto.getUser(), dto.getSportField())!=null) {
			return false;
		}
		else{
			FavoriteEntity entity = new FavoriteEntity();
			entity.setUser(userRepository.findOne(dto.getUser()));
			entity.setFavorite(sportFieldRepository.findOne(dto.getSportField()));
			favoriteRepository.save(entity);
			return true;
		}
	}
	@Override
	public SportFieldDTO delete(Long id) {
		FavoriteEntity entity = favoriteRepository.findByUserAndSportField(gettUser().getId(), id);
		SportFieldEntity tmp = entity.getFavorite();
		favoriteRepository.delete(entity);
		return sportFieldConverter.toDTO(tmp) ;
	}
	private UserEntity gettUser() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String userName = loggedInUser.getName();
		UserEntity user = userRepository.findOneByUserName(userName);
		return user;
	}
}
