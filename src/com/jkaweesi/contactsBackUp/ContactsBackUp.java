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
package com.jkaweesi.contactsBackUp;

import java.util.LinkedList;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.contactsbackup.R;
import com.jkaweesi.contactsBackUp.api.ContactService;
import com.jkaweesi.contactsBackUp.pojo.Contact;

public class ContactsBackUp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts_back_up);
		Configuration config = getResources().getConfiguration();

		LinkedList<Contact> contacts = new LinkedList<Contact>();
		contacts.addAll(ContactService.getStoredContacts(getContentResolver()));

		TextView displayName = (TextView) findViewById(R.id.name);
		TextView phoneNumber = (TextView) findViewById(R.id.phone);

		// loop through and display all existing contacts in the UI
		for (int i = contacts.indexOf(contacts.getFirst()); i <= contacts
				.indexOf(contacts.getLast()); i++) {// TODO displays last only
			String name = contacts.get(i).getContactNames().getDisplayName();
			String phone = contacts.get(i).getContactPhoneNumbers()
					.getMobilePhoneNumber();

			displayName.setText(name);
			phoneNumber.setText(phone);

			if (config.orientation == Configuration.ORIENTATION_LANDSCAPE
					&& contacts.get(i).getContactEmails().getMainEmailAddress() != null) {
				String email = contacts.get(i).getContactEmails()
						.getMainEmailAddress();

				TextView emailAddress = (TextView) findViewById(R.id.email);
				emailAddress.setText(email);
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts_back_up, menu);
		return true;
	}

}
