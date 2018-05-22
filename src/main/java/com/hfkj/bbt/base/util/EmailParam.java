package com.hfkj.bbt.base.util;

/**
 * 
 *发送邮件参数 
 * @version 1.0
 * @author qhliao
 */
public class EmailParam {
	// smtp服务器地址
	private String smtp;
	// 发件人
	private String from;
	// 收件人
	private String to;
	// 抄送
	private String copyto;
	// 主题
	private String subject;
	// 内容
	private String content;
	// 账号
	private String username;
	// 密码
	private String password;
	/**
	 * @return the smtp
	 */
	public String getSmtp() {
		return smtp;
	}
	/**
	 * @param smtp the smtp to set
	 */
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the copyto
	 */
	public String getCopyto() {
		return copyto;
	}
	/**
	 * @param copyto the copyto to set
	 */
	public void setCopyto(String copyto) {
		this.copyto = copyto;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}







}
