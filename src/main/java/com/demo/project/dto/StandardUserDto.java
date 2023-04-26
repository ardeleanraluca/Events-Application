package com.demo.project.dto;

import com.demo.project.entity.StandardUserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardUserDto extends UserAccountDto {
    @NotEmpty
    private String dateOfBirth;

    @NotEmpty
    private String city;

    @NotEmpty
    private String county;

    public StandardUserDto(StandardUserEntity userEntity) {
        super(userEntity.getUserAccountEntity());
        this.city = userEntity.getCity();
        this.county = userEntity.getCounty();

    }
}
