package com.demo.project.dto;

import com.demo.project.entity.StandardUserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
public class StandardUserDto extends UserAccountDto {

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
