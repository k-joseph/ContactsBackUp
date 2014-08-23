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
package com.jkaweesi.contactsBackUp.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import com.jkaweesi.contactsBackUp.pojo.Contact;
import com.jkaweesi.contactsBackUp.pojo.ContactEmails;
import com.jkaweesi.contactsBackUp.pojo.ContactImage;
import com.jkaweesi.contactsBackUp.pojo.ContactInstantMessenger;
import com.jkaweesi.contactsBackUp.pojo.ContactNames;
import com.jkaweesi.contactsBackUp.pojo.ContactNotes;
import com.jkaweesi.contactsBackUp.pojo.ContactPhoneNumbers;
import com.jkaweesi.contactsBackUp.pojo.ContactPostalAddresses;
import com.jkaweesi.contactsBackUp.pojo.HomePostalAddress;
import com.jkaweesi.contactsBackUp.pojo.OrganizationPostalAddress;
import com.jkaweesi.contactsBackUp.pojo.OtherPostalAddress;
import com.jkaweesi.contactsBackUp.pojo.PostalAddress;
import com.jkaweesi.contactsBackUp.pojo.WorkPostalAddress;

/**
 * Used to handle all model related issues
 * 
 * @author k-joseph
 * 
 */
public class ContactService {

	/**
	 * specific contact id returned for a unique contact
	 */
	private String contactId;

	public static List<Contact> getStoredContacts(
			ContentResolver contentResolver) {

		Contact contact = new Contact();
		ContactNames names = new ContactNames();
		ContactPhoneNumbers phones = new ContactPhoneNumbers();
		ContactEmails emails = new ContactEmails();
		ContactImage image = new ContactImage();

		List<Contact> contacts = new ArrayList<Contact>();

		Cursor cursor = contentResolver.query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {

				String id = cursor.getString(cursor
						.getColumnIndex(ContactsContract.Contacts._ID));

				boolean hasPhoneNumbers = Integer
						.parseInt(cursor.getString(cursor
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0;
				String displayName = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				String imageUri = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

				if (hasPhoneNumbers) {
					// setting up the contact object to be backed up
					contact.setContactId(id);
					names.setDisplayName(displayName);
					contact.setContactNames(names);
					// TODO set more names here

					Cursor pCur = contentResolver.query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = ?", new String[] { id }, null);
					while (pCur.moveToNext()) {
						String mobilePhoneNumber = pCur
								.getString(pCur
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						phones.setMobilePhoneNumber(mobilePhoneNumber);
						contact.setContactPhoneNumbers(phones);
						// TODO set other phone numbers here
					}
					pCur.close();

					Cursor emailCursor = contentResolver.query(
							ContactsContract.CommonDataKinds.Email.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Email.CONTACT_ID
									+ " = ?", new String[] { id }, null);
					while (emailCursor.moveToNext()) {
						String mainEmailAddress = emailCursor
								.getString(emailCursor
										.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
						String emailType = emailCursor
								.getString(emailCursor
										.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
						emails.setMainEmailAddress(mainEmailAddress);
						emails.setEmailType(emailType);
						contact.setContactEmails(emails);
						// TODO set other emails here

					}

					emailCursor.close();
				}

				if (imageUri != null) {
					try {
						Bitmap bitmap = MediaStore.Images.Media.getBitmap(
								contentResolver, Uri.parse(imageUri));
						image.setBitmap(bitmap);
						image.setImageURI(imageUri);
						contact.setImage(image);
						// TODO set more image properties here

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				contacts.add(contact);
			}

		}

		return contacts;
	}

	public Contact getAndSetNamesAndPhones(ContentResolver cr, Contact contact) {
		ContactNames names = new ContactNames();
		ContactPhoneNumbers phones = new ContactPhoneNumbers();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);

		if (cur.getCount() > 0) {
			while (cur.moveToNext()) {
				String id = cur.getString(cur
						.getColumnIndex(ContactsContract.Contacts._ID));
				String name = cur
						.getString(cur
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

				contact.setContactId(id);
				this.contactId = id;
				names.setDisplayName(name);
				contact.setContactNames(names);

				if (Integer
						.parseInt(cur.getString(cur
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					Cursor pCur = cr.query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = ?", new String[] { id }, null);
					while (pCur.moveToNext()) {
						String phone = pCur
								.getString(pCur
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

						if (pCur.getPosition() == 0)
							phones.setMobilePhoneNumber(phone);
						else if (pCur.getPosition() == 1)
							phones.setHomePhoneNumber(phone);
						else if (pCur.getPosition() == 2)
							phones.setWorkPhoneNumber(phone);
						else if (pCur.getPosition() == 3)
							phones.setOtherPhoneNumber(phone);
						else
							phones.addOtherPhoneNumber(phone);
					}
					pCur.close();
					contact.setContactPhoneNumbers(phones);
				}
			}
		}
		return contact;
	}

	public Contact getAndSetEmails(ContentResolver cr, Contact contact) {
		ContactEmails emails = new ContactEmails();

		Cursor emailCur = cr.query(
				ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
				ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
				new String[] { this.contactId }, null);
		while (emailCur.moveToNext()) {
			String email = emailCur
					.getString(emailCur
							.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
			String emailType = emailCur
					.getString(emailCur
							.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));

			emails.setEmailType(emailType);
			if (emailCur.getPosition() == 0)
				emails.setMainEmailAddress(email);
			else if (emailCur.getPosition() == 1)
				emails.setHomeEmailAddress(email);
			else if (emailCur.getPosition() == 2)
				emails.setWorkEmailAddress(email);
			else if (emailCur.getPosition() == 3)
				emails.setOtherEmailAddress(email);
			else if (emailCur.getPosition() >= 4)
				emails.addOtherEmailAddresses(email);
		}
		emailCur.close();
		contact.setContactEmails(emails);

		return contact;
	}

	public Contact getAndSetNotes(ContentResolver cr, Contact contact) {
		ContactNotes notes = new ContactNotes();

		String noteWhere = ContactsContract.Data.CONTACT_ID + " = ? AND "
				+ ContactsContract.Data.MIMETYPE + " = ?";
		String[] noteWhereParams = new String[] { this.contactId,
				ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE };
		Cursor noteCur = cr.query(ContactsContract.Data.CONTENT_URI, null,
				noteWhere, noteWhereParams, null);
		if (noteCur.moveToFirst()) {
			String note = noteCur
					.getString(noteCur
							.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));

			if (noteCur.getPosition() == 0)
				notes.setNote(note);
			else
				notes.addNote(note);
		}
		noteCur.close();
		contact.setNotes(notes);

		return contact;
	}

	public Contact getAndSetPostalAddresses(ContentResolver cr, Contact contact) {
		ContactPostalAddresses postalAddresses = new ContactPostalAddresses();
		PostalAddress address = new PostalAddress();
		HomePostalAddress homeAddress = new HomePostalAddress();
		WorkPostalAddress workAddress = new WorkPostalAddress();
		OtherPostalAddress otherAddress = new OtherPostalAddress();

		String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND "
				+ ContactsContract.Data.MIMETYPE + " = ?";
		String[] addrWhereParams = new String[] {
				this.contactId,
				ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE };
		Cursor addrCur = cr.query(ContactsContract.Data.CONTENT_URI, null,
				addrWhere, addrWhereParams, null);

		int pos = addrCur.getPosition();
		while (addrCur.moveToNext()) {
			String poBox = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
			String street = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
			String city = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
			String state = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
			String postalCode = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
			String country = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
			String type = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));

			address.setCity(city);
			address.setCountry(country);
			address.setPoBox(poBox);
			address.setState(state);
			address.setStreet(street);
			address.setType(type);
			address.setZipCode(postalCode);

			pos = addrCur.getPosition();

			if (addrCur.getPosition() == 0) {
				homeAddress = (HomePostalAddress) address;
				postalAddresses.setHomePostalAddress(homeAddress);
			} else if (addrCur.getPosition() == 1) {
				workAddress = (WorkPostalAddress) address;
				postalAddresses.setWorkPostalAddress(workAddress);
			} else if (addrCur.getPosition() == 2) {
				otherAddress = (OtherPostalAddress) address;
				postalAddresses.setOtherPostalAddress(otherAddress);
			} else if (addrCur.getPosition() >= 3) {
				otherAddress = (OtherPostalAddress) address;
				postalAddresses.addOtherPostalAddress(otherAddress);
			}
		}
		addrCur.close();
		postalAddresses
				.setOrganizationPostalAddress(getAndSetOrganizationInfo(cr));
		contact.setContactPostalAddresses(postalAddresses);

		return contact;
	}

	public Contact getAndSetInstantMessages(ContentResolver cr, Contact contact) {
		ContactInstantMessenger instantMsgs = new ContactInstantMessenger();

		String imWhere = ContactsContract.Data.CONTACT_ID + " = ? AND "
				+ ContactsContract.Data.MIMETYPE + " = ?";
		String[] imWhereParams = new String[] { this.contactId,
				ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE };

		Cursor imCur = cr.query(ContactsContract.Data.CONTENT_URI, null,
				imWhere, imWhereParams, null);
		if (imCur.moveToFirst()) {
			String msg = imCur.getString(imCur
					.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA));
			String imType;
			imType = imCur.getString(imCur
					.getColumnIndex(ContactsContract.CommonDataKinds.Im.TYPE));

			instantMsgs.setType(imType);
			if (imCur.getPosition() == 0)
				instantMsgs.setMessage(msg);
			else
				instantMsgs.addMessage(msg);
		}
		imCur.close();
		contact.setInstantMessages(instantMsgs);

		return contact;
	}

	public OrganizationPostalAddress getAndSetOrganizationInfo(
			ContentResolver cr) {
		OrganizationPostalAddress orgInfo = new OrganizationPostalAddress();

		String orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND "
				+ ContactsContract.Data.MIMETYPE + " = ?";
		String[] orgWhereParams = new String[] { this.contactId,
				ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE };
		Cursor orgCur = cr.query(ContactsContract.Data.CONTENT_URI, null,
				orgWhere, orgWhereParams, null);
		if (orgCur.moveToFirst()) {
			String orgName = orgCur
					.getString(orgCur
							.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA));
			String title = orgCur
					.getString(orgCur
							.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));

			orgInfo.setCompany(orgName);
			orgInfo.setPosition(title);
		}
		orgCur.close();

		return orgInfo;
	}
}
