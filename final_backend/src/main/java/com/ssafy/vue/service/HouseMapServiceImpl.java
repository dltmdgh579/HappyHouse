package com.ssafy.vue.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.vue.dto.HouseDealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseDealDto> getAptRecord(long aptCode) throws Exception {
		return houseMapMapper.getAptRecord(aptCode);
	}

	@Override
	public List<HouseInfoDto> getAptAll(HashMap<String, String> map) throws Exception {
		return houseMapMapper.getAptAll(map);
	}
}
