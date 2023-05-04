package com.liversportweb.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liversportweb.DTO.FavoriteDTO;
import com.liversportweb.DTO.SportFieldDTO;
import com.liversportweb.service.IFavoriteService;

@RestController
public class FavoriteAPI {
	
	@Autowired
	IFavoriteService favoriteService;
	@GetMapping("/user/favorite/{id}")
	public List<SportFieldDTO> getFavorite(@PathVariable("id") Long id){
		List<SportFieldDTO> result  = favoriteService.getAllFavoriteSportFieldByUser(id);
		return result;
	}
	
	@PostMapping("user/favorite/add")
	public String addFavorite(@RequestBody FavoriteDTO dto) {
		boolean existed = favoriteService.save(dto);
		if(existed) return "Đã thêm vào danh sách yêu thích"; 
		else return "Sân hiện tại đã có trong danh sách yêu thích";
	}
	@DeleteMapping("user/favorite/delete/{id}")
	public SportFieldDTO removeFavorite(@PathVariable("id") Long id) {
		SportFieldDTO result= favoriteService.delete(id) ;
		return result;
	}
	
}
