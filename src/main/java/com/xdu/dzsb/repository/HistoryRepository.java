package com.xdu.dzsb.repository;

import com.xdu.dzsb.model.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findAllByOpenid(String openid);
}
