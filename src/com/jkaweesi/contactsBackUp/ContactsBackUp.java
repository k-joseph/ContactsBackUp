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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactsbackup.R;
import com.jkaweesi.contactsBackUp.api.ContactService;
import com.jkaweesi.contactsBackUp.pojo.Contact;

public class ContactsBackUp extends ListActivity {

	private static List<Contact> contacts;

	private String dbName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts_back_up);

		List<Contact> contacts = ContactService
				.getStoredContacts(getContentResolver());
		ContactsBackUp.contacts = contacts;

		getListView().setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

		setListAdapter((ListAdapter) new ContactRow(this,
				android.R.layout.simple_list_item_multiple_choice, R.id.phone,
				contacts.toArray(new Contact[contacts.size()])));

		final CheckBox selectAll = (CheckBox) findViewById(R.id.selectAll);
		final ListView listView = (ListView) findViewById(android.R.id.list);

		selectAll.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				int size = 0;
				boolean isChecked = selectAll.isChecked();
				if (isChecked == true) {
					size = getListView().getCount();
					for (int i = 0; i <= size; i++)
						listView.setItemChecked(i, true);
				} else if (isChecked == false) {
					size = getListView().getCount();
					for (int i = 0; i <= size; i++)
						listView.setItemChecked(i, false);
				}
			}
		});

		final ArrayList<String> takeAction = new ArrayList<String>();

		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		final String backUpToThis = "Normal BackUp To This Device";
		final String backUpToAnother = "Normal BackUp To Another Device";
		final String saveToSQLite = "Save to SQLite Database";
		final String saveToMysql = "Save to MSQL Database file";
		final String saveToAll = "Save to All";
		final String restoreContacts = "Restore from previous backUp";
		final String action = "Take Action";

		takeAction.add(action);
		takeAction.add(backUpToThis);
		takeAction.add(backUpToAnother);
		takeAction.add(saveToSQLite);
		takeAction.add(saveToMysql);
		takeAction.add(restoreContacts);
		takeAction.add(saveToAll);

		// Create the ArrayAdapter
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				ContactsBackUp.this, android.R.layout.simple_spinner_item,
				takeAction);

		// Set the Adapter
		spinner.setAdapter(arrayAdapter);

		// Set the ClickListener for Spinner
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> adapterView, View view,
					int i, long l) {
				String currentAction = takeAction.get(i);
				if (!currentAction.equals(action)) {
					Toast.makeText(ContactsBackUp.this,
							"Action running : " + takeAction.get(i),
							Toast.LENGTH_LONG).show();

					// TODO call into service layer to perform given task

					if (takeAction.get(i).equals(saveToSQLite)) {
						dbName = "ContactsBackUp.db";
						File dbFile = new File(
								"/data/data/com.example.contactsbackup/databases/"
										+ dbName);
						if (!dbFile.exists()) {
							ContactService
									.createSQLiteContactsDatabaseAndTables(
											dbName, ContactsBackUp.this);
							ContactService.insertContactsDataIntoSQLiteDB(
									dbName, ContactsBackUp.this,
									getContentResolver());
						}
					}

					// if was successful
					String path = null;// assign path here
					Toast.makeText(ContactsBackUp.this,
							"finished creating backup at: " + path,
							Toast.LENGTH_SHORT).show();
					// end if
				}
			}

			// If no option selected
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	@SuppressWarnings("rawtypes")
	class ContactRow extends ArrayAdapter {

		@SuppressWarnings("unchecked")
		public ContactRow(Context context, int resource, int phone,
				Contact[] contacts) {
			super(context, resource, phone, contacts);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Configuration config = getResources().getConfiguration();
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View contactRow = inflater.inflate(R.layout.contacts_list, parent,
					false);
			Contact[] contacts = ContactsBackUp.contacts
					.toArray(new Contact[ContactsBackUp.contacts.size()]);

			// for (int i = 0; i < contacts.length; i++) {
			TextView displayName = (TextView) contactRow
					.findViewById(R.id.name);
			TextView phoneNumber = (TextView) contactRow
					.findViewById(R.id.phone);

			String name = contacts[position].getContactNames().getDisplayName();
			String phone = contacts[position].getContactPhoneNumbers()
					.getMobilePhoneNumber();

			displayName.setText(name);
			phoneNumber.setText(phone);

			if (config.orientation == Configuration.ORIENTATION_LANDSCAPE
					&& contacts[position].getContactEmails()
							.getMainEmailAddress() != null) {
				String email = contacts[position].getContactEmails()
						.getMainEmailAddress();

				TextView emailAddress = (TextView) contactRow
						.findViewById(R.id.email);
				emailAddress.setText(email);
			}
			// }
			return contactRow;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts_back_up, menu);
		return true;
	}
}
