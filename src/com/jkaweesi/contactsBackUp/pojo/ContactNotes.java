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

import java.util.ArrayList;
import java.util.List;

public class ContactNotes {

	private String note;

	private List<String> notes = new ArrayList<String>();

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
		this.notes.add(note);
	}

	public List<String> getNotes() {
		return notes;
	}

	public void addNote(String note) {
		this.notes.add(note);
	}

}
