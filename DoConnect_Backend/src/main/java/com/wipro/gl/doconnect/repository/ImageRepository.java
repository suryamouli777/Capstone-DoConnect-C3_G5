package com.wipro.gl.doconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wipro.gl.doconnect.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	public Optional<Image> findByName(String name);
	
}