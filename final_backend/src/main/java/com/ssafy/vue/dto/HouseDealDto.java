package com.ssafy.vue.dto;

public class HouseDealDto {
    private long aptCode;
	private String apartmentName;
    private String dealAmount;
    private String recentPrice;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private String roadName;
	private String dongName;
	private String sidoName;
	private String gugunName;
	private String maxDeal;
	private String minDeal;
	private String maxFloor;
	private String minFloor;
	private String maxArea;
	private String minArea;
	
	public String getRecentPrice() {
		return recentPrice;
	}

	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}

	public String getMaxDeal() {
		return maxDeal;
	}

	public void setMaxDeal(String maxDeal) {
		this.maxDeal = maxDeal;
	}

	public String getMinDeal() {
		return minDeal;
	}

	public void setMinDeal(String minDeal) {
		this.minDeal = minDeal;
	}

	public String getMaxFloor() {
		return maxFloor;
	}

	public void setMaxFloor(String maxFloor) {
		this.maxFloor = maxFloor;
	}

	public String getMinFloor() {
		return minFloor;
	}

	public void setMinFloor(String minFloor) {
		this.minFloor = minFloor;
	}

	public String getMaxArea() {
		return maxArea;
	}

	public void setMaxArea(String maxArea) {
		this.maxArea = maxArea;
	}

	public String getMinArea() {
		return minArea;
	}

	public void setMinArea(String minArea) {
		this.minArea = minArea;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	private int buildYear;
	private String jibun;

    public long getAptCode() {
        return aptCode;
    }

    public void setAptCode(long aptCode) {
        this.aptCode = aptCode;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    public int getDealYear() {
        return dealYear;
    }

    public void setDealYear(int dealYear) {
        this.dealYear = dealYear;
    }

    public int getDealMonth() {
        return dealMonth;
    }

    public void setDealMonth(int dealMonth) {
        this.dealMonth = dealMonth;
    }

    public int getDealDay() {
        return dealDay;
    }

    public void setDealDay(int dealDay) {
        this.dealDay = dealDay;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	@Override
	public String toString() {
		return "HouseDealDto [aptCode=" + aptCode + ", aptName=" + apartmentName + ", dealAmount=" + dealAmount
				+ ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area
				+ ", floor=" + floor + ", dongName=" + dongName + ", sidoName=" + sidoName + ", gugunName=" + gugunName
				+ ", buildYear=" + buildYear + "]";
	}
    
}
