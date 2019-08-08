package main

import (
	"bytes"
	"crypto/cipher"
	"crypto/des"
	"encoding/base64"
	"encoding/json"
	"fmt"
)

func main() {
	encodeToDecode()
}

// 解密
func ThriDESDeCrypt(crypted,key []byte)[]byte{
	//获取block块
	block,_ :=des.NewTripleDESCipher(key)
	//创建切片
	context := make([]byte,len(crypted))
	//设置解密方式
	blockMode := cipher.NewCBCDecrypter(block,key[:8])
	//解密密文到数组
	blockMode.CryptBlocks(context,crypted)
	//去补码
	context = PKCSUnPadding(context)
	return context
}

//加密
func ThriDESEnCrypt(origData,key []byte)[]byte{
	//获取block块
	block,_ :=des.NewTripleDESCipher(key)
	//补码
	origData = PKCSPadding(origData, block.BlockSize())
	//设置加密方式为 3DES  使用3条56位的密钥对数据进行三次加密
	blockMode := cipher.NewCBCEncrypter(block,key[:8])
	//创建明文长度的数组
	crypted := make([]byte,len(origData))
	//加密明文
	blockMode.CryptBlocks(crypted,origData)
	//base64.StdEncoding.EncodeToString()
	return []byte(base64.StdEncoding.EncodeToString(crypted))
}
//去补码
func PKCSUnPadding(origData []byte)[]byte{
	length := len(origData)
	unpadding := int(origData[length-1])
	return origData[:length-unpadding]
}

//补码
func PKCSPadding(origData []byte,blockSize int)[]byte{
	//计算需要补几位数
	padding := blockSize-len(origData)%blockSize
	//在切片后面追加char数量的byte(char)
	padtext := bytes.Repeat([]byte{byte(padding)},padding)
	return append(origData,padtext...)
}

func encodeToDecode() {
    // 必须大于8位
	key := "123456788765432112345678"
	data := `{"order_id": "201609010016562012987","dealer_id": "2736123","broker_id":
     "yiyun","real_name": "张三","card_no": "6228880199872220","phone_no":
     "13488795491","id_card": "123532612312312321","pay": "100000.00"}`
	encrypt := ThriDESEnCrypt([]byte(data), []byte(key))
	encode, _ :=base64.StdEncoding.DecodeString(string(encrypt))
	decrypt := ThriDESDeCrypt([]byte(encode), []byte(key))
	fmt.Println(string(encrypt))
	fmt.Println(string(decrypt))
}
