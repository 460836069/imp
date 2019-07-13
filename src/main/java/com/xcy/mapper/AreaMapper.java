package com.xcy.mapper;

import com.xcy.pojo.Area;

import java.util.List;

public interface AreaMapper {

    List<Area> getProvince();

    List<Area> getCity(int id);

    List<Area> getCountry(int id);
}
