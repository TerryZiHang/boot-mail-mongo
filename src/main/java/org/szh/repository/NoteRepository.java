package org.szh.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.szh.beans.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

	@Query(value = "{\"title\":{\"$regex\":?0}}")
	Note findByTitle(String title);

	@Query(value = "{\"author\":{\"$regex\":?0}}")
	List<Note> findByAuthor(String author);
	
	@Query(value = "{\"author\":{\"$regex\":?0},\"title\":{\"$regex\":?1}}")
	Note findByAuthorAnfTitle(String author,String title);
}
