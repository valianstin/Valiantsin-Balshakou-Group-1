::Write a Command Line that starts a JavaApp using the Serial Collector with the following parameters

::the 6m initial heap size for when the JVM starts
::the18m maximum heap size
::the2m size of the Young Generation
::the 20m starting size of the Permanent Generation
::the 30 maximum size of the Permanent Generation

java -Xmx18m -Xms6m -Xmn2m -XX:PermSize=20m -XX:MaxPermSize=30m -XX:+UseSerialGC -jar f:\test\HelloWorld.jar