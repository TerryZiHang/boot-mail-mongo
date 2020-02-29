package org.szh.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.szh.beans.MailMsg;

@Repository
public interface MailRepository extends MongoRepository<MailMsg, String>{

	@Query(value="{\"to\":{\"$regex\":?0}}")
	List<MailMsg> findByTo(String to);
}
