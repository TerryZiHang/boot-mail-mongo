package org.szh.service;

import org.szh.beans.MailMsg;
import org.szh.beans.Result;

public interface MailService {

	Result<?> sendSimpleMail(MailMsg base);

	Result<?> historyRecord(String to);

	Result<?> sendHtmlMail(MailMsg base);

	Result<?> sendAttachmentMail(MailMsg base);

	Result<?> sendInlineResourceMail(MailMsg base);

}