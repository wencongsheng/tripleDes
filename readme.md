# Triple DES
名词解释：密码学中，三重数据加密算法（英语：Triple Data Encryption Algorithm，縮寫為TDEA，Triple DEA），或稱3DES（Triple DES），是一種對稱密鑰加密块密码，相当于是对每个数据块应用三次資料加密標準（DES）算法。

## 使用场景
1. 数据通讯加解密

## 环境
java version "1.8.0_181"

PHP 7.2.18 (cli)

Python 3.6.3

go version go1.11.4 darwin/amd64

js Chrome 版本 73.0.3683.103（正式版本） （64 位）

## 依赖 （java）
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>1.11</version>
</dependency>

## 说明
5个版本数据都测试成功，请按需拿取
请注意php是经过openssl_encrypt会自动base64_encode后才返回的加解密数据
**不用自己处理（base64_encode）**

注意php json_encode汉字Unicode，第二个参数 JSON_UNESCAPED_UNICODE

