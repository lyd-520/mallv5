package com.tuling.tulingmall.bo;

import com.tuling.tulingmall.model.UmsAdmin;
import com.tuling.tulingmall.model.UmsPermission;
import com.tuling.tulingmall.model.UmsRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 * Created on 2018/4/26.
 */
public class AdminUserDetails implements UserDetails {
    private UmsAdmin umsAdmin;
     private List<UmsRole> roleList;
    private List<UmsPermission> permissionList;
    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsRole> roleList) {
        this.umsAdmin = umsAdmin;
        this.roleList = roleList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        Collection< GrantedAuthority> authorityList= roleList.stream()
                .map(role->new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        if(permissionList!=null){
            authorityList.addAll( permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList()) );
        }

         return  authorityList;
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }

    public UmsAdmin getUmsAdmin() {
             return umsAdmin;
    }
}
