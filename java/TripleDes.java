

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
public class TripleDes {
    public static byte[] TripleDesEncrypt(byte[] content, byte[] key) throws Exception {
        byte[] icv = new byte[8];
        System.arraycopy(key, 0, icv, 0, 8);
        return TripleDesEncrypt(content, key, icv);
    }
    protected static byte[] TripleDesEncrypt(byte[] content, byte[] key, byte[] icv) throws
            Exception {
        final SecretKey secretKey = new SecretKeySpec(key, "DESede");
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        final IvParameterSpec iv = new IvParameterSpec(icv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(content);
    }
    public static byte[] TripleDesDecrypt(byte[] content, byte[] key) throws Exception {
        byte[] icv = new byte[8];
        System.arraycopy(key, 0, icv, 0, 8);
        return TripleDesDecrypt(content, key, icv);
    }
    protected static byte[] TripleDesDecrypt(byte[] content, byte[] key, byte[] icv) throws
            Exception {
        final SecretKey secretKey = new SecretKeySpec(key, "DESede");
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

        final IvParameterSpec iv = new IvParameterSpec(icv); cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        return cipher.doFinal(content);
    }
    public static void main(String[] args) throws Exception {
        byte[] content = "{\"order_id\":\"201609010016562012987\",\"dealer_id\":\"2736123\",\"broker_id\":\"yiyun\",\"real_name\":\"张三\",\"card_no\":\"6228880199872220\",\"phone_no\":\"13488795491\",\"id_card\":\"123532612312312321\",\"pay\":\"100000.00\"}".getBytes("utf-8");
        byte[] key = "123456788765432112345678".getBytes("utf-8");
        byte[] enc = EncodeTest.TripleDesEncrypt(content, key);
        byte[] enc64 = Base64.encodeBase64(enc);
        System.out.println("encrypt: " + new String(enc64));
        byte[] dec64 = Base64.decodeBase64("0eUw2Nk2isVYjjJQE4xb65kk4HSUGy3Bp0t8PhCVy2xK6mbFfRzMt4/b0T10ZQ0dIQqY/mFQbPunq0Cy/Dqr8d3ongbX5zT7E3Rmi2PoYB2wDMjt3El+3HTtcjEAQCIJTO2budgJltbWKZ4e34GwGvWPHQgxD7bYrcSUAlUUv3S9WqVqScFa8TCJ0+WVGK3C5thx43LoU7i0YdyWZ+VsJGh3g4dsCZWprqeiLLam56bK2i0/gxqHqzVJVnAW2+WvWkJCfKBZGHPvGV/ktv5OMZYe8FVmRjZt");
        byte[] dec = EncodeTest.TripleDesDecrypt(dec64, key);
        System.out.println("decrypt: " + new String(dec));
    }
}
