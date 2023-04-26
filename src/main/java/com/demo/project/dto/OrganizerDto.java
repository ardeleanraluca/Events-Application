package com.demo.project.dto;

import com.demo.project.entity.OrganizerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerDto extends UserAccountDto {
    private List<EventDto> events;

    public OrganizerDto(OrganizerEntity organizerEntity) {
        super(organizerEntity.getUserAccountEntity());
        this.events = organizerEntity.getEvents().stream().map(EventDto::new).toList();
    }
}
