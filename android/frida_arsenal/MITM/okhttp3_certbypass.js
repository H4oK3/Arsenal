setTimeout(function() {
  Java.perform(function() {
    var X509TrustManager = Java.use('javax.net.ssl.X509TrustManager');
    var EmptyTrustManager = Java.registerClass({
      name: 'trust.nccgroup.empirehackingdemo.EmptyTrustManager',
      implements: [X509TrustManager],
      methods: {
        checkClientTrusted: function(chain, authType) {},
        checkServerTrusted: function(chain, authType) {},
        getAcceptedIssuers: function() { return null; }
      }
    });

    var SSLContext = Java.use('javax.net.ssl.SSLContext');
    var CertificatePinner = Java.use('okhttp3.CertificatePinner');

    SSLContext.init.overload('[Ljavax.net.ssl.KeyManager;', '[Ljavax.net.ssl.TrustManager;', 'java.security.SecureRandom').implementation = function(keyManager, trustManager, secRandom) {
      var manager = EmptyTrustManager.$new();
      return this.init.overload('[Ljavax.net.ssl.KeyManager;', '[Ljavax.net.ssl.TrustManager;', 'java.security.SecureRandom').call(this, keyManager, [manager], secRandom);
    };

    CertificatePinner.check.overload('java.lang.String', 'java.util.List').implementation = function(host, chain) {
      return null;
    };
  });

  send({});
}, 0);
