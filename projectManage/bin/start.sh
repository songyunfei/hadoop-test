###启动  
nohup java -jar -Dloader.path=lib/ projectManage-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod -server -Xms1024m -Xmx2048m -Xss256k &  
tail -f nohup.out