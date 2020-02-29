package org.szh.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.szh.beans.MailMsg;
import org.szh.beans.Result;
import org.szh.service.MailService;
import org.szh.util.StringUtils;

@RestController
@RequestMapping("mails")
public class MailController {

	@Resource
	private MailService mailService;
	
	@RequestMapping(value = "/simple/send",method = RequestMethod.POST)
	public Object sendSimpleMail(@RequestBody MailMsg base) {
		if(!StringUtils.vaildMail(base.getTo())) {
			return new Result<>("邮箱格式不匹配");
		}
		return mailService.sendSimpleMail(base);
	}
	
	@RequestMapping(value = "/history",method = RequestMethod.GET)
	public Object historyRecord(@RequestParam("to") String to) {
		return mailService.historyRecord(to);
	}
	
	@RequestMapping(value = "/html/send",method = RequestMethod.POST)
	public Object sendHtmlMail(@RequestBody MailMsg base) {
		if(!StringUtils.vaildMail(base.getTo())) {
			return new Result<>("邮箱格式不匹配");
		}
		return mailService.sendHtmlMail(base);
	}
	
	@RequestMapping(value = "/attachment/send",method = RequestMethod.POST)
	public Object sendAttachmentMail(@RequestBody MailMsg base) {
		if(!StringUtils.vaildMail(base.getTo())) {
			return new Result<>("邮箱格式不匹配");
		}
		return mailService.sendAttachmentMail(base);
	}
	
	@RequestMapping(value = "/inline/send",method = RequestMethod.POST)
	public Object sendInlineResourceMail(@RequestBody MailMsg base) {
		if(!StringUtils.vaildMail(base.getTo())) {
			return new Result<>("邮箱格式不匹配");
		}
		return mailService.sendInlineResourceMail(base);
	}
}
