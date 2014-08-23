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

public class ContactPostalAddresses {

	private HomePostalAddress homePostalAddress;

	private WorkPostalAddress workPostalAddress;

	private OtherPostalAddress otherPostalAddress;

	private OrganizationPostalAddress organizationPostalAddress;

	private List<OtherPostalAddress> otherPostalAddresses = new ArrayList<OtherPostalAddress>();

	public HomePostalAddress getHomePostalAddress() {
		return homePostalAddress;
	}

	public void setHomePostalAddress(HomePostalAddress homePostalAddress) {
		this.homePostalAddress = homePostalAddress;
	}

	public WorkPostalAddress getWorkPostalAddress() {
		return workPostalAddress;
	}

	public void setWorkPostalAddress(WorkPostalAddress workPostalAddress) {
		this.workPostalAddress = workPostalAddress;
	}

	public OtherPostalAddress getOtherPostalAddress() {
		return otherPostalAddress;
	}

	public void setOtherPostalAddress(OtherPostalAddress otherPostalAddress) {
		this.otherPostalAddress = otherPostalAddress;
		this.otherPostalAddresses.add(otherPostalAddress);
	}

	public OrganizationPostalAddress getOrganizationPostalAddress() {
		return organizationPostalAddress;
	}

	public void setOrganizationPostalAddress(
			OrganizationPostalAddress organizationPostalAddress) {
		this.organizationPostalAddress = organizationPostalAddress;
	}

	public List<OtherPostalAddress> getOtherPostalAddresses() {
		return otherPostalAddresses;
	}

	public void addOtherPostalAddress(OtherPostalAddress otherPostalAddress) {
		this.otherPostalAddresses.add(otherPostalAddress);
	}

}
