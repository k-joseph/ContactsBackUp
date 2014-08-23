/**
 * The contents of this file are subject to the open source or Public License
 * you may not use this file except in
 * compliance with the License.
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) k-joseph, All Rights Reserved.
 * 
 * Owned and created by: KAWEESI JOSEPH
 */
package com.jkaweesi.contactsBackUp.pojo;

import java.sql.Date;


/**
 * Contact object with properties such as name, phone number, email, etc
 * 
 * @author k-joseph
 * 
 */
public class Contact {

	private String contactId;
	
	private ContactNames contactNames;

	private ContactEmails contactEmails;

	private ContactPhoneNumbers contactPhoneNumbers;

	private ContactPostalAddresses contactPostalAddresses;
	
	private ContactInstantMessenger instantMessages;
	
	private ContactNotes notes;
	
	private ContactImage image;

	private String webSite;

	private Date birthDay;

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public ContactNames getContactNames() {
		return contactNames;
	}

	public void setContactNames(ContactNames contactNames) {
		this.contactNames = contactNames;
	}

	public ContactEmails getContactEmails() {
		return contactEmails;
	}

	public void setContactEmails(ContactEmails contactEmails) {
		this.contactEmails = contactEmails;
	}

	public ContactPhoneNumbers getContactPhoneNumbers() {
		return contactPhoneNumbers;
	}

	public void setContactPhoneNumbers(ContactPhoneNumbers contactPhoneNumbers) {
		this.contactPhoneNumbers = contactPhoneNumbers;
	}

	public ContactPostalAddresses getContactPostalAddresses() {
		return contactPostalAddresses;
	}

	public void setContactPostalAddresses(
			ContactPostalAddresses contactPostalAddresses) {
		this.contactPostalAddresses = contactPostalAddresses;
	}

	public ContactImage getImage() {
		return image;
	}

	public void setImage(ContactImage image) {
		this.image = image;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Date getBirthDay() {
		return birthDay;

	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	
	public ContactNotes getNotes() {
		return notes;
	}

	public void setNotes(ContactNotes notes) {
		this.notes = notes;
	}

	public void stringBufferTest() {
		String format = "";
	}

	public ContactInstantMessenger getInstantMessages() {
		return instantMessages;
	}

	public void setInstantMessages(ContactInstantMessenger instantMessages) {
		this.instantMessages = instantMessages;
	}

}
