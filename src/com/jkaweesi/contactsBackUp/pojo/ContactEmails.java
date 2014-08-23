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

public class ContactEmails {

	/**
	 * personal or mobile mainly used email address
	 */
	private String mainEmailAddress;

	private String homeEmailAddress;

	private String workEmailAddress;

	private String otherEmailAddress;
	
	private String emailType;

	private List<String> otherEmailAddresses = new ArrayList<String>();

	public String getMainEmailAddress() {
		return mainEmailAddress;
	}

	public void setMainEmailAddress(String mainEmailAddress) {
		this.mainEmailAddress = mainEmailAddress;
	}

	public String getHomeEmailAddress() {
		return homeEmailAddress;
	}

	public void setHomeEmailAddress(String homeEmailAddress) {
		this.homeEmailAddress = homeEmailAddress;
	}

	public String getWorkEmailAddress() {
		return workEmailAddress;
	}

	public void setWorkEmailAddress(String workEmailAddress) {
		this.workEmailAddress = workEmailAddress;
	}

	public String getOtherEmailAddress() {
		return otherEmailAddress;
	}

	public void setOtherEmailAddress(String otherEmailAddress) {
		this.otherEmailAddress = otherEmailAddress;
		this.otherEmailAddresses.add(otherEmailAddress);
	}

	public List<String> getOtherEmailAddresses() {
		return otherEmailAddresses;
	}

	public void addOtherEmailAddresses(String otherEmailAddress) {
		this.otherEmailAddresses.add(otherEmailAddress);
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

}
