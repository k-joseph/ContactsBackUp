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

import android.graphics.Bitmap;

public class ContactImage {

	private String imageURI;

	private Bitmap bitmap;

	public String getImageURI() {
		return imageURI;
	}

	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

}
