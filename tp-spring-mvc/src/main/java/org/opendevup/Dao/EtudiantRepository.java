package org.opendevup.Dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.opendevup.entitee.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	
	public List<Etudiant> findByNom(String n);
	
	public Optional<Etudiant> findById(Long id);
	
	public List<Etudiant> findAll();
	
	public void deleteById(Long id);
	
	@Query("select e from Etudiant e where e.nom like :x")
	public Page<Etudiant> chercherEtudiants(@Param("x") String mc, Pageable pageable);

	@Query("select e from Etudiant e where e.dateNaissance >:x and e.dateNaissance <:y")
	public List<Etudiant> chercherEtudiants(@Param("x")Date d1,@Param("y")Date d2);

}
