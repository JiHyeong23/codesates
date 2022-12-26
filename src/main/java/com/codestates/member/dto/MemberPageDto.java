package com.codestates.member.dto;

import com.codestates.member.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class MemberPageDto<T> {

    private T data;
    private PageInfo pageInfo;
}
