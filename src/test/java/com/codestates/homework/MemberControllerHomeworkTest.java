package com.codestates.homework;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @Autowired
    private MemberMapper mapper;

    @Test
    void patchMemberTest() throws Exception {
        // TODO
        MemberDto.Patch patch =
                new MemberDto.Patch(1L, "홍길동", "010-1111-1111", Member.MemberStatus.MEMBER_ACTIVE);
        Member member = mapper.memberPatchToMember(patch);
        member.setStamp(new Stamp());

        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(member);

        String content = gson.toJson(patch);

        ResultActions actions = mockMvc.perform(
                patch("/v11/members/" + member.getMemberId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        );

        actions
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.data.memberStatus").value(patch.getMemberStatus().getStatus()));

    }

    @Test
    void getMemberTest() throws Exception {
        // TODO
        Member member = new Member("gdh@gmail.com", "홍길동", "010-1111-1111");
        member.setMemberId(1L);
        member.setStamp(new Stamp());

        given(memberService.findMember(Mockito.any(Long.class))).willReturn(member);

        ResultActions actions = mockMvc.perform(
                get("/v11/members/" + member.getMemberId())
                        .accept(MediaType.APPLICATION_JSON)
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.name").value(member.getName()))
                .andExpect(jsonPath("$.data.phone").value(member.getPhone()));
    }

    @Test
    void getMembersTest() throws Exception {
        // TODO
        List<MemberDto.Post> members = List.of(
        new MemberDto.Post("hgd@gmail.com","홍길동","010-1111-1111", new Stamp()),
        new MemberDto.Post("hgd@mail.com","홍길도","010-1111-1121", new Stamp()));
        members.get(0).setMemberId(1L);
        members.get(1).setMemberId(2L);

        given(memberService.findMembers(Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .willReturn(new PageImpl<>(members, PageRequest.of(0, 2)));

        ResultActions actions =
                mockMvc.perform(get("/v11/members?page=1&size=2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].email").value(members.get(0).getEmail()))
                .andExpect(jsonPath("$.data[0].name").value(members.get(0).getName()))
                .andExpect(jsonPath("$.data[0].phone").value(members.get(0).getPhone()))
                .andExpect(jsonPath("$.data[1].email").value(members.get(1).getEmail()))
                .andExpect(jsonPath("$.data[1].name").value(members.get(1).getName()))
                .andExpect(jsonPath("$.data[1].phone").value(members.get(1).getPhone()));
    }

    @Test
    void deleteMemberTest() throws Exception {
        // TODO
        Member member = new Member("gdh@gmail.com", "홍길동", "010-1111-1111");
        member.setMemberId(1L);
        member.setStamp(new Stamp());

        given(memberService.findVerifiedMember(Mockito.any(Long.class)))
                .willReturn(member);

        ResultActions actions =
                mockMvc.perform(delete("/v11/members/" + member.getMemberId())
                        .accept(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isNoContent());

    }
}
