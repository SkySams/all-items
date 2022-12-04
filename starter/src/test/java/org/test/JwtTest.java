package org.test;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;

/**
 * @author: zyh
 * @date: 2022/3/23
 */
public class JwtTest {
//    private static final byte[] SECRET = "6MNSobBRCHGIO0fS6MNSobBRCHGIO0fS".getBytes();
//    private static final long EXPIRE_TIME = 1000 * 5;
//
//
//    public static void main(String[] args) throws JOSEException {
//        MACSigner macSigner = new MACSigner(SECRET);
//        /**
//         * 2. 建立payload 载体
//         */
//        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                .subject("doi")
//                .issuer("http://www.doiduoyi.com")
//                .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
//                .claim("ACCOUNT","123456")
//                .build();
//
//        /**
//         * 3. 建立签名
//         */
//        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
//        signedJWT.sign(macSigner);
//        System.out.println(signedJWT.serialize());
//
//    }

    public static void main(String[] args) {
        System.out.println(inss("helloworldsadweq",'&',3));
    }

    public static String inss(String ors,char ins,int count) {
        String regex = "(.{"+count+"})";
        ors = ors.replaceAll(regex, "$1"+ins);
        ors = ors.charAt(ors.length()-1)==ins ? ors.substring(0,ors.length()-1) : ors;
        return ors;
    }


}
