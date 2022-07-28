package co.edu.iudigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.iudigital.model.Caso;

public interface ICasoRepository 
	extends JpaRepository<Caso, Long>{

	@Query("UPDATE Caso c SET c.visible = ?1 WHERE "
			+ "c.id = ?2")
	Boolean setVisible(Boolean visible, Long id);
}
