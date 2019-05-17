package id.ac.its.notification_api.library;

import io.jsonwebtoken.Jwts;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTests {
    @Value("classpath:files/jwt.pub")
    private Resource publicKey;

    @Test
    public void parseToken() throws IOException {
        var key = retrievePublicKey();
        var claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJyb2xlIjoiQWRtaW4iLCJwaG9uZSI6IjEyMzQ1Njc4OTAzIiwiaXNzIjoidGNkZWxpdmVyeSIsIm5hbWUiOiJBZG1pblRDIiwiZXhwIjoxNTU4MTAwOTU4LCJ1c2VyaWQiOjEwLCJlbWFpbCI6ImFkbWluQGVtYWlsLmNvbSIsInVzZXJuYW1lIjoiYWRtaW4ifQ.XmZrfIwkREAe461OOWdoQz0d6PNCOQ0lnUhGcznQPvyaH76K6u8Bgy1cobidg8nEFJx6nVFJtLixlDmaqf8dUBF5wT8p3TJ2IglWl57pLHRvzHXjLpPY6QPLJEo-JZKsaonUa4RbzkcWeqqn7sUmSo2Uobk3JqdamGNYHjLgzr35W8cIFe47OZ2xt8D-QyL-EJ7neuaDJZUDDHdzBqnAuAxC2kzC01_vX-R4C_W02hnNIhIIkFylL3cbRJfPLezRjMIpgYg3gJqspm1-yHMbDN7ssBQXfbtrUQQ1KaTeb9c5gYpbrIKleplOrLADLbOBrNFTu0Nv8sR54hKheOwZ9IdLkjOqa58hOrim6hLoLKX-jnXHB7ZYSTsm6m1iv4oHKGOJtOE2CnG6kc0grbMejKhcl-lygvQyoDCgWsImsv-duYfqL7GjCFfYPiqf7YAjb2n0F0RvYrW0Kn9Do4KFVsmv-CQSaJb6KK23_odscqKVR4D5RSoLtMq99lWshYZIQtWnWxvsBKcwCdbH9FAGGeaSYZazBCvNme0EYBm9AdKGWq9F-_6kb-wCk466myL-fEsxSsGeJFQ_rVSxqAqLrQS-8H-uXXPCK1nXukjaDXAnMNKwyzlfyG1urRtI6ceBsZ6qbenB-yzLDG2TOaw7D2BJhH5L_XdkuK1aZnSTI6c");
        var id = claims.getBody().get("userid", Integer.class);
        Assert.assertNotNull(id);
    }

    private PublicKey retrievePublicKey() throws IOException {
        var reader = new FileReader(publicKey.getFile());
        var parser = new PEMParser(reader);
        var info = (SubjectPublicKeyInfo) parser.readObject();
        var converter = new JcaPEMKeyConverter();
        return converter.getPublicKey(info);
    }
}
