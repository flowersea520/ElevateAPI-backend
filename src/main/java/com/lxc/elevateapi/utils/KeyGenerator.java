package com.lxc.elevateapi.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class KeyGenerator {
	// 生成随机的 Secret Key
	// 生成随机的 Secret Key
	public static String generateSecretKey() {
//		定义一个长度为 32 的 byte 数组 key，这里的 32 字节对应于 256 位的密钥长度。
		byte[] key = new byte[32]; // 32 bytes = 256 bits
		// 创建一个 SecureRandom 对象用于生成随机数
		SecureRandom secureRandom = new SecureRandom();
		// 生成随机字节序列并填充到 key 数组中
		secureRandom.nextBytes(key);
		// 将生成的随机字节序列以 Base64 编码返回作为 Secret Key
		return Base64.getEncoder().encodeToString(key);

	}

	// 生成唯一的 Access Key
	public static String generateAccessKey() {
		UUID uuid = UUID.randomUUID(); // 生成一个随机的 UUID 对象
		return uuid.toString(); // 将 UUID 转换为字符串形式并返回作为 Access Key
	}

	public static void main(String[] args) {
		String secretKey = generateSecretKey();
		String accessKey = generateAccessKey();

		System.out.println("Secret Key: " + secretKey);
		System.out.println("Access Key: " + accessKey);
	}
}