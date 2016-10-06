package com.eshop.dao.demo_0;

// версия 1) вручную создаем экземпляр драйвера:  java.sql.Driver driver = new com.mysql.jdbc.Driver();
// версия 2) Class.forName("com.mysql.jdbc.Driver").getName(); теперь он в DriverManager`e
// версия 3) JDBC 4.0 вы сразу вызываете DriverManager и там уже лежат все драйвера из classpath`a


import javax.sound.sampled.spi.AudioFileReader;
import java.nio.charset.spi.CharsetProvider;
import java.sql.Driver;
import java.util.ServiceLoader;

public class MySQLExample_40_final {


    public static void main(String[] args) {
//        загрузи мне и создай экзнмпляры всех наследников типа Driver из "(resources.jar/)META-INF/services/"
        ServiceLoader<Driver> drivers = ServiceLoader.load(Driver.class);
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
        System.out.println();
        ServiceLoader<CharsetProvider> charsetProviders = ServiceLoader.load(CharsetProvider.class);
        for (CharsetProvider charsetProvider : charsetProviders) {
            System.out.println(charsetProvider);
        }
        System.out.println();
        ServiceLoader<AudioFileReader> audioFile = ServiceLoader.load(AudioFileReader.class);
        for (AudioFileReader audioFileReader : audioFile) {
            System.out.println(audioFileReader);
        }



    }
}
