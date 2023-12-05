package com.total.goodneighbor.service;

public interface ShareService {
    String getimage(String email, String tname);
    public int getstatus(String email,String tname);
    public String saveshare(String email,String tname);
}
