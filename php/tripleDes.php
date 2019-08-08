<?php
header('content-type:application/json;charset=utf-8');

$des3key = '123456788765432112345678';
$desUtils = new DesUtil($des3key);
$data = <<<EOF
     {"order_id": "201609010016562012987","dealer_id": "2736123","broker_id":
     "yiyun","real_name": "张三","card_no": "6228880199872220","phone_no":
     "13488795491","id_card": "123532612312312321","pay": "100000.00"}
EOF;
$encode = $desUtils->encrypt($data);
//$code = "mBSddfukIEwq7fTFr2veMuFuLbo8pUUCZEkEsAmbr5b2LP/cjmcNmhI6FFjR3FsuqZ9zuCLgqSOd8GTRI7pI8Gss/xvzWNlgg5cthz8eGpB2nqeOQip+N7FGp1fsjKawNcY0BWwSH2/+PUX8NtT5yy2j28mioefYtU2BEXVvKPwcL3ULBN1U0WOlz2BFeYWHAMkoJaVW8gWi/uZiIPGlMHjrL6FNluXc6xhxffcvdTD2u/RV0vbxA4tAQ5WIEEsTZ4hoPzKwg1CXYzr58f1+YatexrNJYFhzlLIly/pIlAsdZIoZ+NCKhA==";
$decode = $desUtils->decrypt($encode);
//echo "encode: $encode\n";
echo $decode;

class DesUtil {
    private $des3key; // 密钥向量量
    private $iv; // 混淆向量量

    /**
     * 构造，传递⼆二个已经进⾏行行base64_encode的KEY与IV
     *
     * @param string $des3key
     * @param string $iv
     */
    function __construct($des3key, $iv = null) {
        $this->des3key = $des3key;
        $this->iv = $iv;
    }

    /**
     * 加密
     * @param <type> $value
     * @return string <type>
     */
    public function encrypt($value) {
        $iv = substr($this->des3key, 0, 8);
        $ret = openssl_encrypt($value, 'DES-EDE3-CBC', $this->des3key, 0, $iv);
        if (false === $ret) {
            return openssl_error_string();
        }
        return $ret;
    }

    /**
     * 解密
     * @param <type> $value
     * @return <type>
     */
    public function decrypt($value) {
        $iv = substr($this->des3key, 0, 8);
        $ret = openssl_decrypt($value, 'DES-EDE3-CBC', $this->des3key, 0, $iv);
        if (false === $ret) {
            return openssl_error_string();
        }
        return $ret;
    }
}
