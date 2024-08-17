package com.multidbdemo.app.config.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersRepository extends JpaRepository<Teachers,Long> {
}
