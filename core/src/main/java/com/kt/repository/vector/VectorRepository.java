package com.kt.repository.vector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.vector.Vector;
import com.kt.domain.vector.VectorType;

public interface VectorRepository extends JpaRepository<Vector, Long> {
	Boolean existsByType(VectorType type);

	Optional<Vector> findByType(VectorType type);
}
