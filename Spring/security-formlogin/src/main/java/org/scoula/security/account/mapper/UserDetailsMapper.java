package org.scoula.security.account.mapper;

import org.scoula.security.account.domain.MemberVO;

//username : pk
public interface UserDetailsMapper {
    public MemberVO get(String username);
}
