package com.decade.agile.kit;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.decade.framework.kit.DZLog;

/**
 * @description: 一个简单的key-value存取类，对SharePreference进行了封装。(From AndroidKit)
 * @author: Decade
 * @date: 2013-5-24
 * 
 */

public class DZKeyValue {
	private final SharedPreferences mSP;
	private Editor mEditor;

	/**
	 * 构造方法。
	 * 
	 * @param context
	 * @param kvName
	 *            键值表名称。
	 * @param mode
	 *            打开的模式。值为Context.MODE_APPEND, Context.MODE_PRIVATE,
	 *            Context.WORLD_READABLE, Context.WORLD_WRITEABLE.
	 */
	public DZKeyValue(Context context, String kvName, int mode) {
		mSP = context.getSharedPreferences(kvName, mode);
		mEditor = mSP.edit();
	}

	/**
	 * 获取保存着的boolean对象。
	 * 
	 * @param key
	 *            键名
	 * @param defValue
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public boolean getBoolean(String key, boolean defValue) {
		return mSP.getBoolean(key, defValue);
	}

	/**
	 * 获取保存着的int对象。
	 * 
	 * @param key
	 *            键名
	 * @param defValue
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public int getInt(String key, int defValue) {
		return mSP.getInt(key, defValue);
	}

	/**
	 * 获取保存着的long对象。
	 * 
	 * @param key
	 *            键名
	 * @param defValue
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public long getLong(String key, long defValue) {
		return mSP.getLong(key, defValue);
	}

	/**
	 * 获取保存着的float对象。
	 * 
	 * @param key
	 *            键名
	 * @param defValue
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public float getFloat(String key, float defValue) {
		return mSP.getFloat(key, defValue);
	}

	/**
	 * 获取保存着的String对象。
	 * 
	 * @param key
	 *            键名
	 * @param defValue
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public String getString(String key, String defValue) {
		return mSP.getString(key, defValue);
	}

	/**
	 * 获取所有键值对。
	 * 
	 * @return 获取到的所胡键值对。
	 */
	public Map<String, ?> getAll() {
		return mSP.getAll();
	}

	/**
	 * 设置一个键值对，它将在{@linkplain #commit()}被调用时保存。<br/>
	 * 注意：当保存的value不是boolean, byte(会被转换成int保存),int, long, float,
	 * String等类型时将调用它的toString()方法进行值的保存。
	 * 
	 * @param key
	 *            键名称。
	 * @param value
	 *            值。
	 * @return 引用的KV对象。
	 */
	private DZKeyValue put(String key, Object value) {
		if (value == null) {
			mEditor.putString(key, null);
		} else if (value instanceof Boolean) {
			mEditor.putBoolean(key, (Boolean) value);
		} else if (value instanceof Integer || value instanceof Byte) {
			mEditor.putInt(key, (Integer) value);
		} else if (value instanceof Long) {
			mEditor.putLong(key, (Long) value);
		} else if (value instanceof Float) {
			mEditor.putFloat(key, (Float) value);
		} else if (value instanceof String) {
			mEditor.putString(key, (String) value);
		} else {
			DZLog.w(getClass(),"值不是Boolean, Integer, Byte, Long, Float, String的类型之一，将调用它的toString()进行保存");
			mEditor.putString(key, value.toString());
		}
		return this;
	}

	/**
	 * 保存键值对。
	 * 
	 * @param key
	 *            键名
	 * @param value
	 *            值。<br/>
	 *            <b>注意：</b>当保存的value不是boolean, byte(会被转换成int保存),int, long,
	 *            float, String等类型时将调用它的toString()方法进行值的保存。
	 * @return 当且仅当提交成功时返回true, 否则返回false.
	 */
	public boolean commit(String key, Object value) {
		return put(key, value).commit();
	}

	/**
	 * 移除键值对。
	 * 
	 * @param key
	 *            要移除的键名称。
	 * @return 引用的KV对象。
	 */
	public DZKeyValue remove(String key) {
		mEditor.remove(key);
		return this;
	}

	/**
	 * 清除所有键值对。
	 * 
	 * @return 引用的KV对象。
	 */
	public DZKeyValue clear() {
		mEditor.clear();
		return this;
	}

	/**
	 * 是否包含某个键。
	 * 
	 * @param key
	 *            查询的键名称。
	 * @return 当且仅当包含该键时返回true, 否则返回false.
	 */
	public boolean contains(String key) {
		return mSP.contains(key);
	}

	/**
	 * 提交修改的键值对。
	 * 
	 * @return 当且仅当提交成功时返回true, 否则返回false.
	 */
	private boolean commit() {
		boolean commit = mEditor.commit();
		mEditor = mSP.edit();
		return commit;
	}
}
