#!/bin/bash
CACERT_FILE=$(/usr/libexec/java_home 1.8)/jre/lib/security/cacert
# Incluir a chave na keystore cacert
keytool -import -trustcacerts -file ipvigilante.crt -alias IPVIGILANTE -keystore $CACERT_FILE -storepass CHANGEIT

# Apagar a chave criada na keystore cacert
#keytool -delete -alias IPVIGILANTE -keystore $CACERT_FILE -storepass CHANGEIT

# Listar as chaves do keystore cacert
#keytool -list -keystore $CACERT_FILE -storepass CHANGEIT
