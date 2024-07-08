package com.example.puppicasso.domain.token.dao;

import com.example.puppicasso.domain.token.entity.RefreshTokenRedis;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenRedis, Long> {
}
