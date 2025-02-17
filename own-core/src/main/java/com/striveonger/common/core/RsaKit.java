package com.striveonger.common.core;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * @author Mr.Lee
 * @since 2024-11-13 23:48
 */
public class RsaKit {

    private static final String CA_PUBLIC = "30819F300D06092A864886F70D010101050003818D0030818902818100DD4F2C013282904AAF7A696649F7158109C8B5D30F6BF1E30E7BA2F817E7B04718B184A31FC20BE4BC2DAD2A23326BB015232E5E2D78B2EA40AFB46B93C33F98E1FE10158686EA6C5E5F3E4DD72E91ACCE26ACE3EDA56CD9F151B953ED0FCD1B8AE1A5DE5837C9318269B5C197739E64D21F93CEAB8B7842A9B697EAC805F9430203010001";
    private static final String CA_PRIVATE = "30820278020100300D06092A864886F70D0101010500048202623082025E02010002818100DD4F2C013282904AAF7A696649F7158109C8B5D30F6BF1E30E7BA2F817E7B04718B184A31FC20BE4BC2DAD2A23326BB015232E5E2D78B2EA40AFB46B93C33F98E1FE10158686EA6C5E5F3E4DD72E91ACCE26ACE3EDA56CD9F151B953ED0FCD1B8AE1A5DE5837C9318269B5C197739E64D21F93CEAB8B7842A9B697EAC805F94302030100010281804CCB0D34F926EF14CB6149B391D4E8245795CAEA0AFEE4F769EE22F5BDADCAB050F440572E4CF23D93C2B1C4DE63BF9461314D04A6F8ABF4B72F8106974678095A9CF537C5A8C6C1C2EE45D6DBE607908ED3ED466778D66B5D4492EA1FAC4B851C9B218AA1ABF1205456A79C6801055B5253BC52C97363FF05C587363BA33ED9024100F3F61C82B9E000C31E349D372264DC87CCAC513E33290A884B8FA86CB228AC5D7B9BE93591A3B833EA339A39CBC9124781590271D7DC24B80ACEF9D5BA564317024100E83AE7489AC6C4141B86215FD03BF6850F1DC14EB3AF0DCF1146BAE6D17693842996C981A4AD2EF72956E0B8DEBB28AD01891B814D821FA703953CCCD9FA06B5024100B8A78F3AEF7FFA73B578338B58D0290FECBE7D1BEC53633278A6FADF93163028EDF7BBD41D3163AA7FD9A1219FFAC888310273544203D2132FB32BED370635EF024100892ED2956728E7876142F8D01AB87145E4CA14F922D7D3F6702432204BB81A28465116B408CD6D23003E9BFD5945821FC0FA774F08B4477267816F2B2BD0B0C90241008F7F8146BA805CA740873A07DC263FA66716157B91E2ED7013700CA013FFEF4B8154BF3AEC8DF020F43001B464EFE9DA882549C3CC3002FB9105A07DBC912399";

    /**
     * 加密(默认CA公钥加密)
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        return publicKeyEncrypt(str, CA_PUBLIC);
    }

    /**
     * 解密(默认CA私钥解密)
     * @param str
     * @return
     */
    public static String decrypt(String str) {
        return privateKeyDecrypt(str, CA_PRIVATE);
    }

    /**
     * 公钥加密
     * @param str
     * @param publicKey
     * @return
     */
    public static String publicKeyEncrypt(String str, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        return rsa.encryptBase64(str, KeyType.PublicKey);
    }

    /**
     * 私钥解密
     * @param str
     * @param privateKey
     * @return
     */
    public static String privateKeyDecrypt(String str, String privateKey) {
        RSA rsa = new RSA(privateKey, null);
        return rsa.decryptStr(str, KeyType.PrivateKey);
    }

    /**
     * 私钥加密
     * @param str
     * @param privateKey
     * @return
     */
    public static String privateKeyEncrypt(String str, String privateKey) {
        RSA rsa = new RSA(privateKey, null);
        return rsa.encryptBase64(str, KeyType.PrivateKey);
    }

    /**
     * 公钥解密
     * @param str
     * @param publicKey
     * @return
     */
    public static String publicKeyDecrypt(String str, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        return rsa.decryptStr(str, KeyType.PublicKey);
    }

}
