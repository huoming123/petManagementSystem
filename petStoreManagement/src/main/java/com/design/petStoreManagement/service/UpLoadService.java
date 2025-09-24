package com.design.petStoreManagement.service;

import com.design.petStoreManagement.pojo.res.RestFulBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UpLoadService {
    RestFulBean<Map> upload(MultipartFile coverFile);
}
