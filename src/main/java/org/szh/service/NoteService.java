package org.szh.service;

import java.util.List;

import org.szh.beans.Note;

public interface NoteService {
	
	Note getNoteByTitle(String title);

	List<Note> getNoteByAuthor(String authour);
	
	Note getNoteById(String id);
	
	void saveNote(Note note);
	
	void updateNote(Note note);

	Note getNoteByAuthorAndTitle(String author, String title);
	
	void deleteNote(String id);

	List<Note> getNotes();

}
