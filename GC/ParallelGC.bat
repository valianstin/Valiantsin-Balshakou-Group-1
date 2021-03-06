::Write a Command Line that starts a JavaApp using the Parallel Collector with 2 Parallel CMS Threads with the following parameters

::the 4m initial heap size for when the JVM starts
::the16m maximum heap size
::the3m size of the Young Generation
::the 24m starting size of the Permanent Generation
::the 32 maximum size of the Permanent Generation

java -Xmx16m -Xms4m -Xmn3m -XX:PermSize=24m -XX:MaxPermSize=32m -XX:+UseParallelGC -XX:ParallelGCThreads=2 -jar f:\test\HelloWorld.jar