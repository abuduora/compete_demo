package com.total.goodneighbor.service;

import com.total.goodneighbor.entity.userinformation;

public interface UserService {
    public boolean saveemail(String email);
    public userinformation saverealname(String email, String realname, String id);
    public userinformation savemessage(String email,String uri,String nickname,String sex,String phone,String buildingnumber,String address);
    public int integral(String email);
}
