package org.szh.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.szh.beans.Note;
import org.szh.service.NoteService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(value = "notes")
public class NoteController {
	
	@Resource
	private NoteService noteService;
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public Object save(@RequestBody Note note) {
		noteService.saveNote(note);
		return "success";
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.PATCH)
	public Object update(@RequestBody Note note) {
		noteService.updateNote(note);
		return "success";
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.DELETE)
	@ApiImplicitParam(name = "noteId", value = "笔记ID", required = true, paramType = "query", dataType = "String")  
	public Object update(@RequestParam("noteId") String noteId) {
		noteService.deleteNote(noteId);
		return "success";
	}
	
	@RequestMapping(value = "/title",method = RequestMethod.GET)
	@ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String")  
	public Object findNoteByTitle(@RequestParam("title")String title) {
		Note note = noteService.getNoteByTitle(title);
		return note;
	}
	
	@RequestMapping(value = "/author",method = RequestMethod.GET)
	@ApiImplicitParam(name = "author", value = "作者", required = true, paramType = "query", dataType = "String")  
	public Object findNoteByauthor(@RequestParam("author")String author) {
		return  noteService.getNoteByAuthor(author);
	}
	
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public Object findAll() {
		return  noteService.getNotes();
	}
	
	@RequestMapping(value = "/one",method = RequestMethod.GET)
	public Object findOne(@RequestParam("noteId") String noteId) {
		return  noteService.getNoteById(noteId);
	}
	
	@RequestMapping(value = "/titleAndAuthor",method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "author", value = "作者", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String") 
	})
	public Object findNoteByauthor(@RequestParam("author")String author,@RequestParam("title")String title) {
		return  noteService.getNoteByAuthorAndTitle(author,title);
	}
	
}
