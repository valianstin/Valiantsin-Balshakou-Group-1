::Write a Command Line that starts a JavaApp using the G1 Garbage Collector with the following parameters

::the 4m initial heap size for when the JVM starts
::the16m maximum heap size
::them 2m size of the Young Generation
::the 12m starting size of the Permanent Generation
::the 18 maximum size of the Permanent Generation

java -Xmx16m -Xms4m -Xmn2m -XX:PermSize=12m -XX:MaxPermSize=18m -XX:+UseG1GC -jar f:\test\HelloWorld.jar