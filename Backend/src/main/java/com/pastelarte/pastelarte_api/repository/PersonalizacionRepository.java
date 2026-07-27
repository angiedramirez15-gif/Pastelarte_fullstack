// PersonalizacionRepository.java
package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.Personalizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalizacionRepository extends JpaRepository<Personalizacion, Integer> {
}