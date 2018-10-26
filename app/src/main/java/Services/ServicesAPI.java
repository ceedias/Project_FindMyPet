package Services;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import Interfaces.ServicesREST;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import Utils.Utils;

/**
 * Created by mtzcpd705 on 18/11/2015.
 */
public class ServicesAPI {
    private static Context context;

    public ServicesAPI(Context current) {
        this.context = current;
    }

    public static ServicesREST get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.ROOT_URL)
                .client(setServiceSSL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ServicesREST.class);

    }

    private static OkHttpClient setServiceSSL() {
        OkHttpClient client = new OkHttpClient();

        try {
            //InputStream is = context.getResources().openRawResource(R.raw.cvcnovogp);
            //KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            //keyStore.load(is, "cvc2016".toCharArray());
            //SSLContext sslContext = SSLContext.getInstance("SSL");
            //TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            //trustManagerFactory.init(keyStore);
            //KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            //keyManagerFactory.init(keyStore, "cvc2016".toCharArray());
            //sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            //client.setSslSocketFactory(sslContext.getSocketFactory());
            HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            client.setHostnameVerifier(hostnameVerifier);
            //is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        client.setConnectTimeout(240,  TimeUnit.SECONDS);
        client.setReadTimeout(240, TimeUnit.SECONDS);

        return client;
    }

}
