# coding=utf-8
import base64

import pyDes


def encode():
    des3key = "123456788765432112345678"
    iv = des3key[0:8]
    data = '{"order_id":"201609010016562012987","dealer_id":"2736123 ","broker_id ":"yiyun ","real_name ":"张三 ","card_no ":"6228880199872220 ","phone_no ":"13488795491 ","id_card ":"123532612312312321 ","pay ":"100000.00 "}'
    k = pyDes.triple_des(des3key, pyDes.CBC, iv, pad=None, padmode=pyDes.PAD_PKCS5)
    d = base64.b64encode(k.encrypt(data.encode('utf-8')))
    print("Encrypted: ", str(d, 'utf-8'))
    print("Decrypted: ", str(k.decrypt(base64.b64decode(d)), 'utf-8'))
    assert str(k.decrypt(base64.b64decode(d)), 'utf-8') == data


def main():
    encode()


if __name__ == "__main__":
    main()
