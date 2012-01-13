/*
******************************************************************
File: org.mkcl.els.services.impl.SecurityServiceImpl.java
Copyright (c) 2011, vishals, MKCL
All rights reserved.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

******************************************************************
 */
package org.mkcl.els.service.impl;

import java.util.Collection;
import java.util.HashSet;
import org.mkcl.els.domain.AuthUser;
import org.mkcl.els.domain.Role;
import org.mkcl.els.domain.User;
import org.mkcl.els.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityServiceImpl.
 *
 * @author vishals
 * @version 1.0.0
 */
@Service("securityService")
public class SecurityServiceImpl implements UserDetailsService {

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException, DataAccessException {        
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User Not Found");
		Collection<GrantedAuthority> roles= new HashSet<GrantedAuthority>();
		for(Role role:user.getRoles()){
			roles.add(new GrantedAuthorityImpl(role.getName()));
		}
		return new AuthUser(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, roles, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getLastLoginTime(), user.getCode(), user.getMobile());
	}
}
