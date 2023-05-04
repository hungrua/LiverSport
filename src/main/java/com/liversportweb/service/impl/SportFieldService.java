package com.liversportweb.service.impl;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liversportweb.DTO.SportFieldDTO;
import com.liversportweb.converter.SportFieldConverter;
import com.liversportweb.entity.SportFieldEntity;
import com.liversportweb.repository.CategoryRepository;
import com.liversportweb.repository.SportFieldRepository;
import com.liversportweb.service.ISportFieldService;
@Service
public class SportFieldService implements ISportFieldService {
	
	@Autowired
	SportFieldRepository sportFieldRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SportFieldConverter sportFieldConverter;
	@PersistenceContext
    private EntityManager entityManager;
	
	public SportFieldDTO save(SportFieldDTO sportFieldDTO) {
		SportFieldEntity entity = new SportFieldEntity();
		if(sportFieldDTO.getId()!=null) {
			SportFieldEntity oldEntity = sportFieldRepository.findOne(sportFieldDTO.getId());
			entity = sportFieldConverter.toEntity(sportFieldDTO,oldEntity);
		}
		else entity = sportFieldConverter.toEntity(sportFieldDTO);
		entity.setCategory(categoryRepository.findOneById(sportFieldDTO.getCategoryId()));
		entity = sportFieldRepository.save(entity);
		return sportFieldConverter.toDTO(entity);
	}

	public void delete(Long[] ids) {
		for(Long id:ids) {
			sportFieldRepository.delete(id);
		}
	}

	public List<SportFieldDTO> getAll() {
		List<SportFieldDTO> list = new ArrayList<SportFieldDTO>();
		for(SportFieldEntity x : sportFieldRepository.findAll()) {
			list.add(sportFieldConverter.toDTO(x));
		}
		return list;
		
	}

	public List<SportFieldDTO> getByCondition(SportFieldDTO dto) {
		String city = dto.getCity();
		String district = dto.getDistrict();
		Long category_id = dto.getCategoryId();
		String name = dto.getName();
		List<SportFieldDTO> result = new ArrayList<>();
		List<SportFieldEntity> list = new ArrayList<SportFieldEntity>();
		if(city==null && district==null) {
			
			list = sportFieldRepository.findAllByCategory_id(category_id);
		}
		else if(district==null) {
			list = sportFieldRepository.findAllByCityAndCategory_id(city, category_id);
		}
		else list = sportFieldRepository.findAllByCityAndDistrictAndCategory_id(city, district, category_id);
		
		if(name==null) {
			for(SportFieldEntity x : list) {
				result.add(sportFieldConverter.toDTO(x));
			}
		}
		else {
			for(SportFieldEntity x : list) {
				String tmp = removeAccent(x.getName().toLowerCase());
				String z = removeAccent(name.toLowerCase());
				if(tmp.contains(chuanHoa(z))) result.add(sportFieldConverter.toDTO(x));
			}
		}
		return result;
	}
	public static String removeAccent(String s) {	  
	  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
	  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	  return pattern.matcher(temp).replaceAll("").replace("ă", "a").replace("â", "a").replace("đ", "d").replace("ô","o").replace("ơ","o").replace("ê","e").replace("ư","u");
	}
	public static String chuanHoa(String s) {
		StringBuilder result = new StringBuilder();
		StringTokenizer token = new StringTokenizer(s);
		while(token.hasMoreElements()) {
			String tmp = token.nextToken();
			result.append(tmp);
			result.append(" ");
		}
		return result.toString().trim();
	}

	@Override
	public SportFieldDTO findOneById(Long id) {
		SportFieldDTO result = sportFieldConverter.toDTO(sportFieldRepository.findOne(id));
		return result;
	}

}
