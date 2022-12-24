package ProjectDoge.StudentSoup.repository.member;

import ProjectDoge.StudentSoup.dto.member.MemberSearch;
import ProjectDoge.StudentSoup.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findById(String id);
    List<Member> findByName(String name);
    List<Member> findByNameAndSchool_SchoolName(String name, String schoolName);
    List<Member> findByDepartment_Id(Long id);
    List<Member> findBySchool_SchoolId(Long id);
    List<Member> search(MemberSearch memberSearch);
    Member findByNickname(String nickname);
    Member findByEmail(String email);
    Member findByEmailAndId(String email, String id);
}
