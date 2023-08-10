package ProjectDoge.StudentSoup.controller.admin;

import ProjectDoge.StudentSoup.dto.restaurantmenu.RestaurantMenuDto;
import ProjectDoge.StudentSoup.dto.restaurantmenu.RestaurantMenuFormDto;
import ProjectDoge.StudentSoup.dto.restaurantmenu.RestaurantMenuUpdateDto;
import ProjectDoge.StudentSoup.entity.restaurant.Restaurant;
import ProjectDoge.StudentSoup.entity.restaurant.RestaurantMenu;
import ProjectDoge.StudentSoup.entity.restaurant.RestaurantMenuCategory;
import ProjectDoge.StudentSoup.repository.restaurantmenu.RestaurantMenuRepository;
import ProjectDoge.StudentSoup.service.admin.AdminRestaurantMenuService;
import ProjectDoge.StudentSoup.service.restaurant.RestaurantFindService;
import ProjectDoge.StudentSoup.service.restaurantmenu.RestaurantMenuRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminRestaurantMenuController {

    private final RestaurantFindService restaurantFindService;
    private final RestaurantMenuRegisterService restaurantMenuRegisterService;
    private final RestaurantMenuRepository restaurantMenuRepository;

    private final AdminRestaurantMenuService adminRestaurantMenuService;

    @GetMapping("admin/{restaurantId}/restaurantMenus")
    public List<Object> RestaurantMenuList(@PathVariable Long restaurantId){
        List<Object> result = new ArrayList<>();
        List<RestaurantMenu> restaurantMenus = restaurantMenuRepository.findByRestaurantId(restaurantId);
        List<RestaurantMenuDto> restaurantMenuDtos = new ArrayList<>();
        for(RestaurantMenu restaurantMenu : restaurantMenus){
            restaurantMenuDtos.add(new RestaurantMenuDto().createRestaurantMenu(restaurantMenu,false));
        }
        result.add(restaurantMenuDtos);
        result.add(restaurantId);
        return result;
    }


    @GetMapping("admin/restaurantMenu")
    public RestaurantMenuCategory[] createRestaurantMenu(@RequestParam Long restaurantId){
        RestaurantMenuCategory[] restaurantMenu = RestaurantMenuCategory.values();
        return restaurantMenu;
    }
    @PostMapping("admin/restaurantMenu")
    public ResponseEntity<String> createRestaurantMenu(@RequestBody RestaurantMenuFormDto form , @RequestPart MultipartFile multipartFile, RedirectAttributes redirect){
        restaurantMenuRegisterService.join(form,multipartFile);
        return ResponseEntity.ok("okay");
    }
    @GetMapping("admin/restaurantMenu/edit/{restaurantMenuId}")
    public RestaurantMenuUpdateDto editRestaurantMenu(@PathVariable Long restaurantMenuId){
        RestaurantMenuUpdateDto restaurantMenuUpdateDto = adminRestaurantMenuService.adminFindUpdateRestaurantMenu(restaurantMenuId);
        return restaurantMenuUpdateDto;
    }
    @PostMapping ("admin/restaurantMenu/edit/{restaurantMenuId}")
    public Long editRestaurantMenu(@PathVariable Long restaurantMenuId,@RequestPart MultipartFile multipartFile,RestaurantMenuUpdateDto restaurantMenuUpdateDto,RedirectAttributes redirect){
        adminRestaurantMenuService.adminUpdateRestaurantMenu(restaurantMenuId,multipartFile,restaurantMenuUpdateDto);

        return restaurantMenuId;
    }
    @GetMapping ("admin/restaurantMenu/{restaurantMenuId}/{restaurantId}")
    public Long deleteRestaurantMenu(@PathVariable Long restaurantMenuId,@PathVariable Long restaurantId,RedirectAttributes redirect){
        restaurantMenuRepository.deleteById(restaurantMenuId);
        redirect.addAttribute("restaurantId",restaurantId);

        return restaurantId;
    }

}
