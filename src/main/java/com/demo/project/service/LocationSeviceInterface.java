package com.demo.project.service;

import com.demo.project.dto.LocationDto;

public interface LocationSeviceInterface {
    public LocationDto createLocation(LocationDto locationDto);
    public boolean deleteLocation(Long id);
}
