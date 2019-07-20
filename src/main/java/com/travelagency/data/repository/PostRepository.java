package com.travelagency.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.travelagency.data.Post;


@Repository
public interface PostRepository extends JpaRepository<Post,Long>{


	@Query("SELECT g FROM Post g where g.visibility=:visibility order by g.lastUpdatedAt DESC")
	List<Post> findAllPublicPosts(@Param("visibility")String visibility);

	List<Post> findAllByOwnerIdOrderByLastUpdatedAtDesc(Long id);


}
