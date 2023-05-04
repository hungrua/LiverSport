package com.liversportweb.javainuse;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.liversportweb.DTO.UserDTO;
import com.liversportweb.converter.UserConverter;
import com.liversportweb.entity.UserEntity;
import com.liversportweb.repository.UserRepository;
@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserConverter userConverter;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
		boolean hasUserRole = false;
		boolean hasAdminRole = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority grantedAuthority : authorities) {
			if(grantedAuthority.getAuthority().equals("USER")) {
				hasUserRole = true;
				break;
			}
			else if(grantedAuthority.getAuthority().equals("ADMIN")) {
				hasAdminRole = true;
				break;
			}

		}
		UserEntity entity = userRepository.findOneByUserName(authentication.getName());
		UserDTO userDTO=  userConverter.toDTO(entity);
		if(hasUserRole) {
			if(userDTO.isFirstLogin()==1) redirectStrategy.sendRedirect(request, response, "/user/information/editPage");
			else redirectStrategy.sendRedirect(request, response, "/booking");
		}
		else if(hasAdminRole) {
			if(userDTO.isFirstLogin()==1) redirectStrategy.sendRedirect(request, response, "/admin/first-login");
			else redirectStrategy.sendRedirect(request, response, "/admin/match");
		}
		else {
			throw new IllegalStateException();
		}	
	}
}