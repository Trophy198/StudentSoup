package ProjectDoge.StudentSoup.repository.restaurant;

import ProjectDoge.StudentSoup.entity.restaurant.RestaurantMenu;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static ProjectDoge.StudentSoup.entity.restaurant.QRestaurant.restaurant;
import static ProjectDoge.StudentSoup.entity.restaurant.QRestaurantMenu.restaurantMenu;

@RequiredArgsConstructor
public class RestaurantMenuRepositoryImpl implements RestaurantMenuRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<RestaurantMenu> findByRestaurantMenuNameAndRestaurant_RestaurantId(String MenuName, Long restaurantId){
        RestaurantMenu query = queryFactory
                .selectFrom(restaurantMenu)
                .leftJoin(restaurantMenu.restaurant,restaurant)
                .fetchJoin()
                .where(restaurantMenu.name.eq(MenuName),restaurantMenu.restaurant.id.eq(restaurantId))
                .fetchOne();
        return Optional.ofNullable(query);

    }

    @Override
    public List<RestaurantMenu> findByRestaurantId(Long restaurantId){
        List<RestaurantMenu> query = queryFactory
                .selectFrom(restaurantMenu)
                .leftJoin(restaurantMenu.restaurant,restaurant)
                .fetchJoin()
                .where(restaurantMenu.restaurant.id.eq(restaurantId))
                .fetch();
        return query;
    }

}
