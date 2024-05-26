package com.example.puppicasso.domain.user.repository;

import com.example.puppicasso.domain.user.entity.RefreshTokenRedis;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenRedis, Long> {
}
