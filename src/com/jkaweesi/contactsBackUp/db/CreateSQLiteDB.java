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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jkaweesi.contactsBackUp.api.ContactService;
import com.jkaweesi.contactsBackUp.pojo.Contact;
import com.jkaweesi.contactsBackUp.pojo.OtherPostalAddress;

public class CreateSQLiteDB {

	public static SQLiteDatabase openOrCreateSQLiteDB(String databaseName,
			Activity activity) {
		SQLiteDatabase db;
		db = activity.openOrCreateDatabase(databaseName,
				SQLiteDatabase.CREATE_IF_NECESSARY, null);

		return db;
	}

	public static SQLiteDatabase createContactsDatabaseAndTables(String dbName,
			Activity activity) {
		SQLiteDatabase db = openOrCreateSQLiteDB(dbName, activity);
		setDbProperties(db);

		// TODO call ContactDBSQLQueries here

		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_CONTACT);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_OTHERNAME);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_OTHER_PHONE_NUMBER);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_OTHER_EMAIL_ADDRESS);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_OTHER_POSTAL_ADDRESS);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_IMAGE);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_NAME);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_PHONE_NUMBER);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_EMAIL);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_POSTAL_ADDRESS);
		db.execSQL(ContactsBackUpDBSQLQueries.CREATE_TABLE_ORGANIZATION);

		db.close();
		return db;
	}

	@SuppressWarnings("deprecation")
	private static void setDbProperties(SQLiteDatabase db) {
		db.setVersion(1);
		db.setLocale(Locale.getDefault());
		db.setLockingEnabled(true);
	}

	/**
	 * Inserts contacts into the previously created backup database and for now
	 * just to avoid writing several lines of code, i call
	 * {@link android.database.sqlite.SQLiteDatabase#execSQL(String)} instead of
	 * using
	 * {@link android.database.sqlite.SQLiteDatabase#insert(String, String, ContentValues)}
	 * method
	 * 
	 * @param dbName
	 * @param activity
	 * @param contentResolver
	 * @return
	 */
	public static SQLiteDatabase insertContactsDataIntoSQLiteDB(String dbName,
			Activity activity, ContentResolver contentResolver) {
		SQLiteDatabase db = openOrCreateSQLiteDB(dbName, activity);
		LinkedList<Contact> contacts = new LinkedList<Contact>();

		setDbProperties(db);
		contacts.addAll(ContactService.getStoredContacts(contentResolver));

		for (int i = contacts.indexOf(contacts.getFirst()); i <= contacts
				.indexOf(contacts.getLast()); i++) {
			Contact contact = contacts.get(i);
			// ContactsBackUpDB contactsBackUpDB = new ContactsBackUpDB();
			/*
			 * ContentValues contactValues = new ContentValues(); ContentValues
			 * otherNameValues = new ContentValues();
			 */

			// contactsBackUpDB.setContact(contact);

			String contactId = contact.getContactId();
			/*
			 * String birthDay = contact.getBirthDay().getDay() + "/" +
			 * contact.getBirthDay().getMonth() + "/" +
			 * contact.getBirthDay().getYear();
			 */
			String website = contact.getWebSite();
			List<String> otherNames = contact.getContactNames().getOtherNames();
			List<String> otherPhoneNumbers = contact.getContactPhoneNumbers()
					.getOtherPhoneNumbers();
			List<String> otherEmails = contact.getContactEmails()
					.getOtherEmailAddresses();
			List<OtherPostalAddress> otherPostalAddresses = contact
					.getContactPostalAddresses().getOtherPostalAddresses();
			String displayName = contact.getContactNames().getDisplayName();
			String firstName = contact.getContactNames().getFirstName();
			String nickName = contact.getContactNames().getNickName();
			String middleName = contact.getContactNames().getMiddleName();
			String surName = contact.getContactNames().getSurName();
			String familyName = contact.getContactNames().getFamilyName();
			String mobilePhone = contact.getContactPhoneNumbers()
					.getMobilePhoneNumber();
			String homePhone = contact.getContactPhoneNumbers()
					.getHomePhoneNumber();
			String workPhone = contact.getContactPhoneNumbers()
					.getWorkPhoneNumber();
			String mainEmail = contact.getContactEmails().getMainEmailAddress();
			String workEmail = contact.getContactEmails().getWorkEmailAddress();
			String homeEmail = contact.getContactEmails().getHomeEmailAddress();
			String emailType = contact.getContactEmails().getEmailType();

			if (contactId != null) {

				/*
				 * if (birthDay == null) { birthDay = ""; } else
				 */if (website == null) {
					website = "";
				} else if (nickName == null) {
					nickName = "";
				} else if (firstName == null) {
					firstName = "";
				} else if (surName == null) {
					surName = "";
				} else if (middleName == null) {
					middleName = "";
				} else if (familyName == null) {
					familyName = "";
				} else if (displayName == null) {
					displayName = "";
				} else if (mobilePhone == null) {
					mobilePhone = "";
				} else if (workPhone == null) {
					workPhone = "";
				} else if (homePhone == null) {
					homePhone = "";
				} else if (mainEmail == null) {
					mainEmail = "";
				} else if (homeEmail == null) {
					homeEmail = "";
				} else if (workEmail == null) {
					workEmail = "";
				} else if (emailType == null) {
					emailType = "";
				}

				String insertIntoContact = ContactsBackUpDBSQLQueries
						.insertIntoContact(enterAsString(contactId), "''",
								enterAsString(website));
				db.execSQL(insertIntoContact);

				// TODO replace with functional code for otherName,
				// otherPhoneNumber, otherEmailAddress and otherPostalAddress
				for (int j = 0; j < otherNames.size(); j++) {
					/*
					 * otherNameValues.put("contact_id", contactId);
					 * otherNameValues.put("other_name", otherNames.get(j));
					 */

					String otherName = otherNames.get(j);
					int otherNameId = j;

					db.execSQL(ContactsBackUpDBSQLQueries.insertIntoOtherName(
							contactId, otherName));

					if (otherNames.size() == 1) {
						db.execSQL(ContactsBackUpDBSQLQueries.insertIntoName(
								nickName, firstName, surName, middleName,
								familyName, displayName, contactId));
					}
				}
				for (int j = 0; j < otherPhoneNumbers.size(); j++) {
					db.execSQL(ContactsBackUpDBSQLQueries
							.insertIntoOtherPhoneNumber(contactId,
									otherPhoneNumbers.get(j)));

					if (otherPhoneNumbers.size() == 1) {
						db.execSQL(ContactsBackUpDBSQLQueries
								.insertIntoPhoneNumber(mobilePhone, workPhone,
										homePhone, contactId));
					}
				}
				for (int j = 0; j < otherEmails.size(); j++) {
					db.execSQL(ContactsBackUpDBSQLQueries
							.insertIntoOtherEmailAddress(contactId,
									otherEmails.get(j)));
				}
				for (int j = 0; j < otherPostalAddresses.size(); j++) {
					db.execSQL(ContactsBackUpDBSQLQueries
							.insertIntoOtherPostalAddress(contactId,
									otherPostalAddresses.get(j)
											.getOtherPostalAddress()));
				}

				if (displayName != null) {
					String insertIntoName = ContactsBackUpDBSQLQueries
							.insertIntoName(enterAsString(nickName),
									enterAsString(firstName),
									enterAsString(surName),
									enterAsString(middleName),
									enterAsString(familyName),
									enterAsString(displayName),
									enterAsString(contactId));
					db.execSQL(insertIntoName);
				}
				if (mobilePhone != null) {
					String insertIntoPhoneNumber = ContactsBackUpDBSQLQueries
							.insertIntoPhoneNumber(enterAsString(mobilePhone),
									enterAsString(workPhone),
									enterAsString(homePhone),
									enterAsString(contactId));
					db.execSQL(insertIntoPhoneNumber);
				}
				if (mainEmail != null) {
					String insertIntoEmail = ContactsBackUpDBSQLQueries
							.insertIntoEmailAddress(enterAsString(mainEmail),
									enterAsString(contactId),
									enterAsString(homeEmail),
									enterAsString(workEmail),
									enterAsString(emailType));
					db.execSQL(insertIntoEmail);
				}
			}
			/*
			 * contactValues.put("contact_id", contactId);
			 * contactValues.put("birth_day", birthDay.toString());
			 * contactValues.put("website", website);
			 */

		}
		db.close();
		return db;
	}

	public static String enterAsString(String stringValue) {
		return "'" + stringValue + "'";
	}
}
