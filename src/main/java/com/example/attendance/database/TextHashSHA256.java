package com.example.attendance.database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TextHashSHA256 {
	public static String GetHash(String text) {
		try {
			// ハッシュ生成前にバイト配列に置き換える際のCharset
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(text.getBytes());
			
			// ハッシュ計算・結果のバイト配列を取得
			byte[] result = md.digest();
			StringBuilder sb = new StringBuilder();
			
			// バイト配列を16進数の文字列に変換
			for (byte b : result) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
