package com.xdu.dzsb.repository;

import com.xdu.dzsb.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByOpenid(String openid);
    
    @Override
    Optional<User> findById(Integer id);
    
    @Transactional
    @Modifying
    @Query("UPDATE User user set user.name = :name, user.avatar = :avatar WHERE user.id = :id")
    void updateNameAndAvatar(@Param("id") Integer id, @Param("name") String name, @Param("avatar") String avatar);
    
    @Transactional
    @Modifying
    @Query("UPDATE User user set user.sex = :sex, user.birthday = :birthday, user.bust = :bust, user.waistline = :waistline, user.hipline = :hipline, user.signature = :signature, user.height = :height, user.weight = :weight WHERE user.id = :userId")
    void updateNameAndAvatar(@Param("userId") Integer userId, @Param("sex") Integer sex, @Param("birthday") Date birthday,
                             @Param("bust") Integer bust, @Param("waistline") Integer waistline, @Param("hipline") Integer hipline,
                             @Param("signature") String signature, @Param("height") Integer height, @Param("weight") Integer weight);
}