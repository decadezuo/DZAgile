package com.decade.agile.kit;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class DZContactsHelper {

	public static Contacts getContactPhone(Activity activity, Intent data) {
		Uri contactData = data.getData();
		Cursor c = activity.managedQuery(contactData, null, null, null, null);
		c.moveToFirst();
		Contacts contacts = getContactPhone(activity, c);
		return contacts;
	}

	/**
	 * 获取指定联系人电话号码
	 * 
	 * @param cursor
	 * @return
	 */
	public static Contacts getContactPhone(Activity activity, Cursor cursor) {

		int phoneColumn = cursor
				.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
		int phoneNum = cursor.getInt(phoneColumn);
		Contacts contacts = new Contacts();
		// System.out.print(phoneNum);
		if (phoneNum > 0) {
			// 获得联系人的ID号
			int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
			String contactId = cursor.getString(idColumn);
			// 获得联系人的电话号码的cursor;
			Cursor phones = activity.getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
					null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
							+ contactId, null, null);
			// int phoneCount = phones.getCount();
			// allPhoneNum = new ArrayList<String>(phoneCount);
			if (phones.moveToFirst()) {
				// 遍历所有的电话号码
				for (; !phones.isAfterLast(); phones.moveToNext()) {
					int index = phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
					int typeindex = phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
					int nameIndex = phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
					int phone_type = phones.getInt(typeindex);
					String phoneNumber = phones.getString(index);
					String userName = phones.getString(nameIndex);
					switch (phone_type) {
					case 2:
						contacts.name = userName;
						contacts.phone = phoneNumber;
						break;
					}
					// allPhoneNum.add(phoneNumber);
				}
				if (!phones.isClosed()) {
					phones.close();
				}
			}
		}
		return contacts;
	}

	public static class Contacts {
		private String name;
		private String phone;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
	}

}
