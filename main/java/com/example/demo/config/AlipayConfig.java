package com.example.demo.config;

public class AlipayConfig {

	// Ӧ��ID,����APPID���տ��˺ż�������APPID��Ӧ֧�����˺�
	public static String app_id = "2016103000777998";

	// �̻�˽Կ������PKCS8��ʽRSA2˽Կ
	public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDHqNxmiiOCduXBnLVmPAl3N4DRRgl7E/2xfIrpF8cd3RCeLYVvue4LBT/aIr2823VrXVblWKdRdGKZaKND7KFc4L3Ez+rwZqdLT5NbCr1o6CZk8NI3p8yAAYOa/gVa5z2PhBrLWuQGuKpfkTPKfAy4XrpflF/M0nrQTks3elWLRzIid4XGpsQJHfdgZK0t7nMF9nG7CHun+Jag9fPnJB2uOlj/hhkXYe3KOjwGpLz3ZJuY1KYo4QgaHpFOFn5rHpNM8936iJYYIE42r+aZwGeAnjt/bkeTdCoiP/t8RanG/IP/pHGiVQ9q5AiHIqUSprq/UIbGNBZsR8/dHUBxQk4VAgMBAAECggEALwdyauDM/nUdJ17PR7aKkD9No324y/nofv8UBVWpfDpPKMMWNDv6ezQQV0RppuuWm+SPjFQ2jTQ56WLBWkWJuMPjT1GeSGQVseKblg1Qkj5klNA45A0tWVWB92cwQbpIQN7gJNhS79Zdo0cjiOVxZZhClDLx4eAON7TJeNbjAXwntJoB+svUjPPk1w2B3rmCA9ALNjCA1ortM0TBoYqpdrhg9pJUT56bKhQEpD6UWk1HseJ+WsyLUbbIxw5J3JFW4Rb+lqtjda5L6o1KiiQ2irpLPzTO1U6jK04xIn5T3kuyomQMendWK1LE8PaSy4wv6vQJMlsN5Tw2lEp/3GGMPQKBgQD4RgP8ktHSxpi3RFqcx7WOV5Gn7/sNbfOaNiWST48D++M0d+cmVuc/uKrVuo3xiDnvT1j0qPce2JXQY5VaV65r8a2G9hkbzpPw9kGcQXvNKiTI1Dz7WaeJYeQg7Tqi45Q0GIEvktgm2/eD93b2PbTGv+JuyjWiFVnHDBMbOii2KwKBgQDN34plHrUhI93kkb5UDmf6bvowW8PdlZTAvxeb8dM9IRq78DEZ0tJtUKyO324Y2R/+RfbfMvOC1PGJaeGokGzDZoU/IENUhoNuszf2E6IBr/Y9S2v4OnAzM5d/VoTLNd40o1l4xxIZlbli6uvZQB/yt/BwEdBQnSSH8AQwejksvwKBgBk/vZ4NLGucKOHSgMibAtrUCwsiO8z01qZjRVTo5GmKT2QBoa9jfScc17reuxrKOOIelrRjDqbDbhEAX6sNab6umHA2TP1n9+G0mZ2Th0ypMY5/DyicyI4PN40lsyXnsSXPLCwzaeohyr1QCHgodF8WGqgbxnKSa0vz7WmCc6t7AoGAMSL7iysLHD/NRDKs9K8DjjhSqpwcYZTEDkUNw4CyZFK0aMrpTTqOUt0AcpFNW8zm3++e/qLQkBd3Fs8YtvzRmMmtX+W5bEE2uYM5BhilgDWZVgPcX0DDatUTgacKxt2yjWm3fyY0YbrkpoFfjVjKx0mzr2vxBmybPX7B4Tgq0U0CgYEAiQ9WX9/bXoUVr3t9/YubDiSIqT/bLVrPfm/vCXBL7ryMU+F02m2wmSS07HW+kBPTi7W4Mbn+tlhlcXhOntKAEv49ZUoxaCwyhBpZOgY/XFIlHiMYwMFb+uPpKs8XLC4sxW0IwCMWaC3aaVoWdPqEXo9RD5FN3wflAvQtBJWlcFY=";

	// ֧������Կ,�鿴��ַ��https://openhome.alipay.com/platform/keyManage.htm ��ӦAPPID�µ�֧������Կ��
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh0myCe2DEmHjp55rGK1a/gUzWk/GQoQWaXoDPdhvYZ1uRUv1jq9pgqmeKv77PAQwZMOWDaIvld2+0u4HnoxK1vv0w4eois1WZVf98jeNGpZ3LfV9PTBLuq19EaMq9cZ3H73gsLn4zZO54YtPHwWxbJdlOHAb5DSBj2ITwVyMGC6FZvC4eqf8ssxri3Z4vMSylGDtNQAwnDrtUbNIuIp50DAcZ40shLYhB/RlXwHWk9l71mhUXFVW25GnGSwVURFwwwEeYegxuu36UC/w2S/8WXS0RuyRjEM78Nu61y2zCvHRl6rrLYNwGpkk0uHwLU6Q8p/CtZm4FUb5mImfhwNJ2wIDAQAB";

	public static String notify_url = "http://localhost:8080/pay/scuec";
	
	public static String return_url = "http://localhost:8080/pay/scuec";
	
	public static String sign_type = "RSA2";
	
	public static String charset = "utf-8";
	
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//ע�⣺ɳ����Ի�������ʽ����Ϊ��https://openapi.alipay.com/gateway.do
}
