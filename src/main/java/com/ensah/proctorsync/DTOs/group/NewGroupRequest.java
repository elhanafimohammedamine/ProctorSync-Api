package com.ensah.proctorsync.DTOs.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewGroupRequest {

    private String groupName;
    private String description;
}
