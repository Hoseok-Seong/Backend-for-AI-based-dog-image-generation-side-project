package com.example.puppicasso.user.repository;

import com.example.puppicasso.user.entity.RefreshTokenRedis;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenRedis, Long> {
}
