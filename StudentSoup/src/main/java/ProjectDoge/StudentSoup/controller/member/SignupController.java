package ProjectDoge.StudentSoup.controller.member;

import ProjectDoge.StudentSoup.dto.department.DepartmentSignUpDto;
import ProjectDoge.StudentSoup.dto.member.MemberFormADto;
import ProjectDoge.StudentSoup.dto.school.SchoolSignUpDto;
import ProjectDoge.StudentSoup.entity.school.Department;
import ProjectDoge.StudentSoup.entity.school.School;
import ProjectDoge.StudentSoup.service.DepartmentService;
import ProjectDoge.StudentSoup.service.MemberService;
import ProjectDoge.StudentSoup.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class SignupController {

    private final MemberService memberService;
    private final SchoolService schoolService;
    private final DepartmentService departmentService;

    @PostMapping("/signUp/2")
    public MemberFormADto signUpCheck(@RequestBody MemberFormADto dto){
        log.info("signUpCheck가 호출되었습니다. ID : [{}]", dto.getId());
        memberService.validateDuplicateMemberId(dto.getId());
        return dto;
    }

    @GetMapping("/signUp/3")
    public List<SchoolSignUpDto> signUpSchoolList(){
        log.info("signUpSchoolList 가 호출되었습니다.");
        List<School> schools = schoolService.findAll();
        List<SchoolSignUpDto> result = schools.stream()
                .map(school -> new SchoolSignUpDto(school))
                .collect(Collectors.toList());
        log.info("불러온 학교 목록 DTO : [{}]", result);
        return result;
    }

    @PostMapping("/signUp/3/{schoolId}")
    public List<DepartmentSignUpDto> signUpDepartmentList(@PathVariable Long schoolId){
        log.info("signUpDepartmentList 메소드가 실행되었습니다. schoolId : [{}]", schoolId);

        List<Department> departments = departmentService.getAllDepartmentUsingSchool(schoolId);
        List<DepartmentSignUpDto> result = departments.stream()
                .map(department -> new DepartmentSignUpDto(department))
                .collect(Collectors.toList());

        log.info("불러온 학과 목록 DTO : [{}]", result);
        return result;
    }



}
