package ProjectDoge.StudentSoup.service.restaurantmenu;

import ProjectDoge.StudentSoup.dto.restaurant.RestaurantDetailDto;
import ProjectDoge.StudentSoup.dto.restaurant.RestaurantMenuDto;
import ProjectDoge.StudentSoup.entity.restaurant.Restaurant;
import ProjectDoge.StudentSoup.entity.restaurant.RestaurantLike;
import ProjectDoge.StudentSoup.entity.restaurant.RestaurantMenu;
import ProjectDoge.StudentSoup.entity.restaurant.RestaurantMenuLike;
import ProjectDoge.StudentSoup.repository.restaurant.RestaurantLikeRepository;
import ProjectDoge.StudentSoup.repository.restaurant.RestaurantMenuLikeRepository;
import ProjectDoge.StudentSoup.repository.restaurant.RestaurantMenuRepository;
import ProjectDoge.StudentSoup.service.restaurant.RestaurantFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantMenuCallService {
    private final RestaurantFindService restaurantFindService;
    private final RestaurantLikeRepository restaurantLikeRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final RestaurantMenuLikeRepository restaurantMenuLikeRepository;

    private final boolean LIKED = true;
    private final boolean NOT_LIKED = false;

    public ConcurrentHashMap<String, Object> restaurantDetailCall(Long restaurantId, Long memberId){

        log.info("음식점 세부사항 호출 로직이 실행되었습니다. [{}]", restaurantId);

        ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

        Restaurant restaurant = restaurantFindService.findOne(restaurantId);
        setRestaurantDetailInfo(restaurantId, memberId, resultMap, restaurant);
        log.info("음식점 세부사항 호출 로직이 완료되었습니다.");
        return resultMap;
    }

    private void setRestaurantDetailInfo(Long restaurantId,
                                         Long memberId,
                                         ConcurrentHashMap<String, Object> resultMap,
                                         Restaurant restaurant) {
        if(isLoginMember(memberId)) {
            log.info("회원이 로그인 된 상태의 음식점 세부사항을 호출합니다. [{}]", memberId);
            setLoginStatusRestaurantDetailDto(restaurantId, memberId, resultMap, restaurant);
        } else {
            log.info("로그인 되지 않은 상태의 음식점 세부사항을 호출합니다.");
            setNotLoginStatusRestaurantDetailDto(restaurantId, resultMap, restaurant);
        }
    }
    private void setLoginStatusRestaurantDetailDto(Long restaurantId, Long memberId, ConcurrentHashMap<String, Object> resultMap, Restaurant restaurant) {
        log.info("로그인이 된 상태의 음식점 세부사항과 메뉴를 호출합니다.");
        setLoginStatusRestaurantDetail(restaurantId, memberId, resultMap, restaurant);
        setLoginStatusRestaurantMenu(restaurantId, memberId, resultMap);
    }

    private void setNotLoginStatusRestaurantDetailDto(Long restaurantId, ConcurrentHashMap<String, Object> resultMap, Restaurant restaurant) {
        log.info("로그인이 되어있지 않은 상태의 음식점 세부사항과 메뉴를 호출합니다.");
        setNotLoginStatusRestaurantDetail(resultMap, restaurant);
        setNotLoginStatusRestaurantMenu(restaurantId, resultMap);
    }


    private boolean isLoginMember(Long memberId) {
        return memberId != null;
    }

    private void setLoginStatusRestaurantDetail(Long restaurantId,
                                                Long memberId,
                                                ConcurrentHashMap<String, Object> resultMap,
                                                Restaurant restaurant) {
        log.info("로그인 된 회원의 음식점 세부사항을 호출합니다.");
        RestaurantLike restaurantLike = restaurantLikeRepository.findRestaurantLikeByRestaurantIdAndMemberId(restaurantId, memberId)
                .orElse(null);

        setMemberRestaurantLikeDetail(resultMap, restaurant, restaurantLike);
        log.info("로그인 된 회원의 음식점 세부사항 호출이 완료되었습니다.");
    }

    private void setMemberRestaurantLikeDetail(ConcurrentHashMap<String, Object> resultMap, Restaurant restaurant, RestaurantLike restaurantLike) {
        if(restaurantLike == null){
            getNotLikeRestaurantDto(resultMap, restaurant);
        } else {
            getLikeRestaurantDto(resultMap, restaurant);
        }
    }

    private void setNotLoginStatusRestaurantDetail(ConcurrentHashMap<String, Object> resultMap, Restaurant restaurant){
        log.info("로그인이 되어있지 않은 상태의 음식점 세부사항을 호출합니다.");
        getNotLikeRestaurantDto(resultMap, restaurant);
    }

    private void getLikeRestaurantDto(ConcurrentHashMap<String, Object> resultMap, Restaurant restaurant) {
        RestaurantDetailDto dto = new RestaurantDetailDto().createRestaurantDetailDto(restaurant, LIKED);
        resultMap.put("restaurant", dto);
    }

    private void getNotLikeRestaurantDto(ConcurrentHashMap<String, Object> resultMap, Restaurant restaurant) {
        RestaurantDetailDto dto = new RestaurantDetailDto().createRestaurantDetailDto(restaurant, NOT_LIKED);
        resultMap.put("restaurant", dto);
    }

    private void setLoginStatusRestaurantMenu(Long restaurantId,
                                              Long memberId,
                                              ConcurrentHashMap<String, Object> resultMap) {
        log.info("로그인 된 상태의 음식점 메뉴들을 호출합니다.");
        List<RestaurantMenuDto> restaurantMenuDtoList = new ArrayList<>();
        List<RestaurantMenu> restaurantMenuList = restaurantMenuRepository.findByRestaurantId(restaurantId);
        for(RestaurantMenu restaurantMenu : restaurantMenuList){
            restaurantMenuDtoList.add(getLoginStatusRestaurantMenuDto(memberId, restaurantMenu));
        }
        resultMap.put("restaurantMenu", restaurantMenuDtoList);

    }

    private RestaurantMenuDto getLoginStatusRestaurantMenuDto(Long memberId, RestaurantMenu restaurantMenu){
        RestaurantMenuLike restaurantMenuLike = restaurantMenuLikeRepository.findRestaurantMenuLikeByRestaurantMenuIdAndMemberId(restaurantMenu.getId(), memberId)
                .orElse(null);
        if(restaurantMenuLike == null)
            return getNotLikeRestaurantMenuDto(restaurantMenu);
        return getLikeRestaurantMenuDto(restaurantMenu);
    }

    private void setNotLoginStatusRestaurantMenu(Long restaurantId, ConcurrentHashMap<String, Object> resultMap){
        log.info("로그인 되지 않은 상태의 음식점 메뉴들을 호출합니다.");
        List<RestaurantMenuDto> restaurantMenuDtoList = new ArrayList<>();
        List<RestaurantMenu> restaurantMenuList = restaurantMenuRepository.findByRestaurantId(restaurantId);
        for(RestaurantMenu restaurantMenu : restaurantMenuList){
            restaurantMenuDtoList.add(getNotLoginStatusRestaurantMenuDto(restaurantMenu));
        }
        resultMap.put("restaurantMenu", restaurantMenuDtoList);
        log.info("로그인 되지 않은 상태의 음식점 메뉴 호출이 완료되었습니다.");
    }

    private RestaurantMenuDto getNotLoginStatusRestaurantMenuDto(RestaurantMenu restaurantMenu){
        return getNotLikeRestaurantMenuDto(restaurantMenu);
    }

    private RestaurantMenuDto getNotLikeRestaurantMenuDto(RestaurantMenu restaurantMenu){
        return new RestaurantMenuDto().createRestaurantMenu(restaurantMenu, NOT_LIKED);
    }

    private RestaurantMenuDto getLikeRestaurantMenuDto(RestaurantMenu restaurantMenu){
        return new RestaurantMenuDto().createRestaurantMenu(restaurantMenu, LIKED);
    }
}