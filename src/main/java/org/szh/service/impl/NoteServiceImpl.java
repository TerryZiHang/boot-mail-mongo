package org.szh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.szh.beans.Note;
import org.szh.repository.NoteRepository;
import org.szh.service.NoteService;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteRepository noteRepository;

	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public Note getNoteByTitle(String title) {
		Note note = noteRepository.findByTitle(title);
		return note;
	}

	@Override
	public List<Note> getNoteByAuthor(String authour) {
		List<Note> notes = noteRepository.findByAuthor(authour);
		return notes;
	}

	@Override
	public void saveNote(Note note) {
		noteRepository.save(note);
	}

	@Override
	public Note getNoteByAuthorAndTitle(String author, String title) {
		return noteRepository.findByAuthorAnfTitle(author, title);
	}

	@Override
	public void deleteNote(String id) {
		noteRepository.delete(id);
	}

	/**
	 * @param note
	 * @description:使用mongoTemplate
	 */
	@Override
	public void updateNote(Note note) {
		if(note== null || note.getNoteId()== null) {
			return;
		}
		Query query = new Query(new Criteria("noteId").is(note.getNoteId()));
		Update update = new Update();
//		if(note.getTitle() != null && !"".equals(note.getTitle())) {
//			update.set("noteId", note.getNoteId());
//		}
		if(note.getAuthor() != null && !"".equals(note.getAuthor())) {
			update.set("author", note.getAuthor());
		}
		if(note.getTitle() != null && !"".equals(note.getTitle())) {
			update.set("title", note.getTitle());
		}
		if(note.getContent() != null && !"".equals(note.getContent())) {
			update.set("content", note.getContent());
		}
		mongoTemplate.updateMulti(query, update, Note.class);
	}

	/**
	 * @param id
	 * @return
	 * @description:mongoTemplate
	 */
	@Override
	public Note getNoteById(String id) {
		Query query = new Query(new Criteria("noteId").is(id));
		return mongoTemplate.findOne(query, Note.class);
	}

	@Override
	public List<Note> getNotes() {
		return mongoTemplate.findAll(Note.class);
	}
}
