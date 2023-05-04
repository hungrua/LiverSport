package com.liversportweb.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liversportweb.DTO.UserDTO;
import com.liversportweb.converter.UserConverter;
import com.liversportweb.entity.RoleEntity;
import com.liversportweb.entity.SportFieldEntity;
import com.liversportweb.entity.UserEntity;
import com.liversportweb.repository.RoleRepository;
import com.liversportweb.repository.SportFieldRepository;
import com.liversportweb.repository.UserRepository;
import com.liversportweb.service.IUserService;
@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private RoleRepository roleRepository;
	@Autowired
	private SportFieldRepository sportFieldRepository;
	@Autowired
	private UserConverter userConverter;


	public String save(UserDTO userDTO) {
		UserEntity entity = new UserEntity();
		UserEntity check1 = userRepository.findOneByUserName(userDTO.getUserName());
		UserEntity check2 = userRepository.findOneByEmail(userDTO.getEmail());
		if(check1!=null) {
			return "Username này đã tồn tại vui lòng chọn 1 tên khác!";
		}
		else if(check2!=null) {
			return "Email này đã được sử dụng để đăng ký";
		}
		else {
			entity = userConverter.toEntity(userDTO);
			RoleEntity roleEntity = roleRepository.findOneById(userDTO.getRoleId());
			entity.setRole(roleEntity);
			entity = userRepository.save(entity);
			userConverter.toDTO(entity);
			return("Đăng ký thành công!");
		}
	}
	@Override
	public UserDTO getUser(String userName) {
		return userConverter.toDTO(userRepository.findOneByUserName(userName));
	}

	@Override
	public UserDTO edit(UserDTO userDTO,Long id) {
		UserEntity oldEntity = userRepository.findOne(userDTO.getId());
		oldEntity.setFullname(userDTO.getFullName());
		oldEntity.setEmail(userDTO.getEmail());
		oldEntity.setPhone(userDTO.getPhone());
		oldEntity.setDistrict(userDTO.getDistrict());
		oldEntity.setCity(userDTO.getCity());
		oldEntity.setImage(userDTO.getImage());
		if(oldEntity.getRole().getId()==2 && oldEntity.isFirstLogin()==1) {
			SportFieldEntity sportFieldEntity = sportFieldRepository.getOne(userDTO.getMySportField());
			oldEntity.setMySportFieldEntity(sportFieldEntity);
		}
		oldEntity.setFirstLogin(0);
		userRepository.save(oldEntity);
		return userConverter.toDTO(oldEntity);
	}

}