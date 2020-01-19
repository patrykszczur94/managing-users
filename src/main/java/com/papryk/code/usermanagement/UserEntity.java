package com.papryk.code.usermanagement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class UserEntity {
    private Long id;
    private String name;
    private String surname;
    private String dataFK;
}
