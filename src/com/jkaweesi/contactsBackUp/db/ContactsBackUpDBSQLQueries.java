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

/**
 * Queries to create contacts_backup database and tables arranged in ASC order
 * 
 * @author k-joseph
 * 
 */
public class ContactsBackUpDBSQLQueries {

	// TODO revisit otherObjects to rather have a foreign key for like
	// other_name off from other_name table and just have contact_id

	public static String CREATE_DATABASE_CONTACTS_BACKUP = "CREATE DATABASE contacts_backup;";

	public static String DROP_DATABASE_CONTACTS_BACKUP = "DROP DATABASE contacts_backup;";

	public static String CREATE_TABLE_CONTACT = "CREATE TABLE IF NOT EXISTS contact ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id TEXT NOT NULL UNIQUE,"
			+ "birth_day DATE ,website TEXT);";

	public static String CREATE_TABLE_OTHERNAME = "CREATE TABLE IF NOT EXISTS other_name ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id TEXT NOT NULL, name TEXT, "
			+ "FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	public static String CREATE_TABLE_OTHER_PHONE_NUMBER = "CREATE TABLE IF NOT EXISTS other_phone_number ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id TEXT NOT NULL, phone_number TEXT, "
			+ "FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	public static String CREATE_TABLE_OTHER_EMAIL_ADDRESS = "CREATE TABLE IF NOT EXISTS other_email_address ( "
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id TEXT NOT NULL, email_address TEXT, "
			+ "FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	public static String CREATE_TABLE_OTHER_POSTAL_ADDRESS = "CREATE TABLE IF NOT EXISTS other_postal_address ( "
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, contact_id TEXT NOT NULL, postal_address TEXT);";

	public static String CREATE_TABLE_IMAGE = "CREATE TABLE IF NOT EXISTS image(id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ " uri TEXT, bitmap BLOB, contact_id TEXT NOT NULL, FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	public static String CREATE_TABLE_NAME = "CREATE TABLE IF NOT EXISTS name ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, nick_name TEXT,"
			+ "first_name TEXT, sur_name TEXT, middle_name TEXT, "
			+ "family_name TEXT, display_name TEXT NOT NULL, contact_id TEXT NOT NULL, "
			+ " FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	public static String CREATE_TABLE_PHONE_NUMBER = "CREATE TABLE IF NOT EXISTS phone_number ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, mobile_phone_number TEXT,"
			+ "work_phone_number TEXT, home_phone_number TEXT, "
			+ " contact_id TEXT NOT NULL, FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	public static String CREATE_TABLE_EMAIL = "CREATE TABLE IF NOT EXISTS email_address (id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "main_email TEXT, contact_id TEXT NOT NULL, home_email TEXT, work_email TEXT, email_type TEXT, "
			+ " FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	// category is where we set whether the address is a home, work other
	// address
	public static String CREATE_TABLE_POSTAL_ADDRESS = "CREATE TABLE IF NOT EXISTS postal_address (id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "category TEXT NOT NULL, contact_id TEXT NOT NULL, street TEXT, city TEXT, state TEXT, zip_code TEXT, po_box TEXT, country TEXT, type TEXT, "
			+ "other_postal_address INTEGER NULL, FOREIGN KEY (other_postal_address) REFERENCES other_postal_address(id) ON UPDATE CASCADE ON DELETE CASCADE"
			+ " FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	public static String CREATE_TABLE_ORGANIZATION = "CREATE TABLE IF NOT EXISTS organization (id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "company TEXT, position TEXT, contact_id TEXT NOT NULL,"
			+ "FOREIGN KEY (contact_id) REFERENCES contact(contact_id) ON UPDATE CASCADE ON DELETE CASCADE);";

	public static String CREATE_ALL_TABLES = CREATE_TABLE_OTHERNAME
			+ CREATE_TABLE_OTHER_PHONE_NUMBER
			+ CREATE_TABLE_OTHER_EMAIL_ADDRESS
			+ CREATE_TABLE_OTHER_POSTAL_ADDRESS + CREATE_TABLE_CONTACT
			+ CREATE_TABLE_IMAGE + CREATE_TABLE_NAME
			+ CREATE_TABLE_PHONE_NUMBER + CREATE_TABLE_EMAIL
			+ CREATE_TABLE_POSTAL_ADDRESS + CREATE_TABLE_ORGANIZATION;

	public static String insertIntoContact(String contactId, String birthDay,
			String website) {
		return "INSERT INTO contact(contact_id, birth_day, website) VALUES("
				+ contactId + ", " + birthDay + ", " + website + ");";
	}

	public static String insertIntoOtherName(String contactId, String otherName) {
		return "INSERT INTO other_name(contact_id, name) VALUES (" + contactId
				+ ", " + otherName + ");";
	}

	public static String insertIntoOtherPhoneNumber(String contactId,
			String otherPhoneNumber) {
		return "INSERT INTO other_phone_number(contact_id, phone_number) VALUES ("
				+ contactId + ", " + otherPhoneNumber + ");";
	}

	public static String insertIntoOtherEmailAddress(String contactId,
			String otherEmailAddress) {
		return "INSERT INTO other_email_address(contact_id, email_address) VALUES ("
				+ contactId + ", " + otherEmailAddress + ");";
	}

	public static String insertIntoName(String nickName, String firstName,
			String surName, String middleName, String familyName,
			String displayName, String contactId) {
		return "INSERT INTO name(nick_name, first_name, sur_name, middle_name,"
				+ "family_name, display_name, contact_id) VALUES (" + nickName
				+ ", " + middleName + "," + firstName + "," + surName + ","
				+ familyName + "," + displayName + "," + contactId + ");";
	}

	public static String insertIntoPhoneNumber(String mobilePhone,
			String workPhone, String homePhone, String contactId) {
		return "INSERT INTO phone_number(mobile_phone_number, work_phone_number, home_phone_number, contact_id)"
				+ "VALUES("
				+ mobilePhone
				+ ","
				+ workPhone
				+ ","
				+ homePhone
				+ "," + contactId + ");";
	}

	public static String insertIntoEmailAddress(String mainEmail,
			String contactId, String homeEmail, String workEmail,
			String emailType) {
		return "INSERT INTO email_address(main_email, contact_id, home_email, work_email, email_type)"
				+ "VALUES("
				+ mainEmail
				+ ","
				+ contactId
				+ ","
				+ homeEmail
				+ "," + workEmail + "," + emailType + ");";
	}

	public static String insertIntoOtherPostalAddress(String contactId,
			String otherPostalAddress) {
		return "INSERT INTO other_postal_address(contact_id, postal_address) VALUES ("
				+ contactId + ", " + otherPostalAddress + ");";
	}

	public static String insertIntoOrganization(String company,
			String position, String contactId) {
		return "INSERT INTO organization(company, position, contact_id) VALUES ("
				+ company + "," + position + "," + contactId + ");";
	}
}
