package ProjectDoge.StudentSoup.service.restaurantmenu;

import ProjectDoge.StudentSoup.dto.restaurant.RestaurantMenuFormDto;
import ProjectDoge.StudentSoup.entity.file.ImageFile;
import ProjectDoge.StudentSoup.entity.restaurant.Restaurant;
import ProjectDoge.StudentSoup.entity.restaurant.RestaurantMenu;
import ProjectDoge.StudentSoup.repository.restaurant.RestaurantMenuRepository;
import ProjectDoge.StudentSoup.service.file.FileService;
import ProjectDoge.StudentSoup.service.restaurant.RestaurantFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantMenuRegisterService {
    private final RestaurantFindService restaurantFindService;
    private final RestaurantMenuValidationService restaurantMenuValidationService;
    private final FileService fileService;
    private final RestaurantMenuRepository restaurantMenuRepository;


    @Transactional
    public Long join(RestaurantMenuFormDto dto, MultipartFile multipartFile) {
        log.info("음식점 메뉴 생성 메소드가 실행 되었습니다.");
        Restaurant restaurant = restaurantFindService.findOne(dto.getRestaurantId());
        Long fileId = fileService.join(multipartFile);
        ImageFile imageFile = fileService.findOne(fileId);
        restaurantMenuValidationService.validationDuplicateRestaurantMenu(dto);
        RestaurantMenu restaurantMenu = new RestaurantMenu().createRestaurantMenu(dto, restaurant, imageFile);
        restaurantMenuRepository.save(restaurantMenu);
        log.info("메뉴가 생성 되었습니다. [{}][{}]",restaurantMenu.getId(),restaurantMenu.getName());
        return restaurantMenu.getId();
    }

}