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
package com.jkaweesi.contactsBackUp.db;

import java.util.List;

import com.jkaweesi.contactsBackUp.pojo.Contact;
import com.jkaweesi.contactsBackUp.pojo.OtherPostalAddress;

/*
 * Not USED
 */
public class ContactsBackUpDB {

	private Contact contact;

	public String contactId = getContact().getContactId();

	public String nickName = getContact().getContactNames().getNickName();

	public String firstName = getContact().getContactNames().getFirstName();

	public String middleName = getContact().getContactNames().getMiddleName();

	public String surName = getContact().getContactNames().getSurName();

	public String familyName = getContact().getContactNames().getFamilyName();

	public String displayName = getContact().getContactNames().getDisplayName();

	public List<String> otherNames = getContact().getContactNames()
			.getOtherNames();

	public String mobilePhoneNumber = getContact().getContactPhoneNumbers()
			.getMobilePhoneNumber();

	public String homePhoneNumber = getContact().getContactPhoneNumbers()
			.getHomePhoneNumber();

	public String workPhoneNumber = getContact().getContactPhoneNumbers()
			.getWorkPhoneNumber();

	public List<String> otherPhoneNumbers = getContact()
			.getContactPhoneNumbers().getOtherPhoneNumbers();

	public String mainEmailAddress = getContact().getContactEmails()
			.getMainEmailAddress();

	public String homeEmailAddress = getContact().getContactEmails()
			.getHomeEmailAddress();

	public String workEmailAddress = getContact().getContactEmails()
			.getWorkEmailAddress();

	public String emailType = getContact().getContactEmails().getEmailType();

	public List<String> otherEmailAddresses = getContact().getContactEmails()
			.getOtherEmailAddresses();

	public List<String> notes = getContact().getContactNotes().getNotes();

	public String homeStreet = getContact().getContactPostalAddresses()
			.getHomePostalAddress().getStreet();

	public String homeCity = getContact().getContactPostalAddresses()
			.getHomePostalAddress().getCity();

	public String homeState = getContact().getContactPostalAddresses()
			.getHomePostalAddress().getState();

	public String homeZipCode = getContact().getContactPostalAddresses()
			.getHomePostalAddress().getZipCode();

	public String homePoBox = getContact().getContactPostalAddresses()
			.getHomePostalAddress().getPoBox();

	public String homeCountry = getContact().getContactPostalAddresses()
			.getHomePostalAddress().getCountry();

	public String workStreet = getContact().getContactPostalAddresses()
			.getWorkPostalAddress().getStreet();

	public String workCity = getContact().getContactPostalAddresses()
			.getWorkPostalAddress().getCity();

	public String workState = getContact().getContactPostalAddresses()
			.getWorkPostalAddress().getState();

	public String workZipCode = getContact().getContactPostalAddresses()
			.getWorkPostalAddress().getZipCode();

	public String workPoBox = getContact().getContactPostalAddresses()
			.getWorkPostalAddress().getPoBox();

	public String workCountry = getContact().getContactPostalAddresses()
			.getWorkPostalAddress().getCountry();

	public List<OtherPostalAddress> otherPostalAddresses = getContact()
			.getContactPostalAddresses().getOtherPostalAddresses();

	// public String type;

	public String company = getContact().getContactPostalAddresses()
			.getOrganizationPostalAddress().getCompany();

	public String titleOrPosition = getContact().getContactPostalAddresses()
			.getOrganizationPostalAddress().getPosition();

	public String occupation = getContact().getContactPostalAddresses()
			.getOccupation();

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	

}
