package lodz.uni.portal.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lodz.uni.portal.model.PortalUser;
import lodz.uni.portal.model.UserProfile;
import lodz.uni.portal.model.type.UserAccountStatusType;

@Service("portalUserDetailsService")
public class PortalUserDetailsService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(PortalUserDetailsService.class);

	@Autowired
	private UserService userService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PortalUser portalUser = userService.findByUsername(username);
		if (portalUser == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		boolean enabled = isUserEnabled(portalUser);

		return new User(portalUser.getNickname(), 
						portalUser.getPassword(), 
						enabled, true, true, true,
						getUserGrantedAuthorities(portalUser));
	}
	
	private boolean isUserEnabled(PortalUser user) {
		String activeStatus = UserAccountStatusType.ACTIVE.getType();
		String userStatus = user.getUserAccountStatus().getType();
		boolean enabled = false;
		if (activeStatus.equals(userStatus)) {
			enabled = true;
		}
		return enabled;
	}
	
	private List<GrantedAuthority> getUserGrantedAuthorities(PortalUser user) {
		List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
		
		for (UserProfile userProfile : user.getUserProfiles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
		}
		
		logger.info("Found " + authorities.size() + " authorities");
		return authorities;
	}
	
}
