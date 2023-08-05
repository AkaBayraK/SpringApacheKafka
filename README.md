# SpringApacheKafka


Apache Kafka, binlerce şirket tarafından yüksek performanslı data pipelines, streaming analytics, data integration, and mission-critical applicationlar için kullanılan açık kaynaklı bir distributed event streaming platformudur.

Kafka çalışabilmesi için java bilgisayarınıza java jak kurulu olması gerekiyor.

Dikkat

Apache Kafka 3.0 sürümü ve üzerinde Zookeeper kullanımına gerek kalmamıştır. Kraft kullanılmaktadır.

KAFKA => Topic => Partition (n) den oluşur

Producer => Top (Partition1 (Message)) => Consumer (Kafka Listener+Business Operations)

Consumer (Kafka Listener+Business Operations) => ((Message)Dışa aklarım => (FİLE veya DB veya KAFKA)) olarak olur.

Topic(n)(Partition(n) tane ise ) => Consumer Group (Consumer(n)) tane olması gerekiyor. Yani P(5 adet ise) <=>C(5 adet) olması en iyi çalışaması anlamındadır.

https://kafka.apache.org/downloads adresinden son sürüm indirilir. 	•	Source download: kafka_2.12-3.5.1.tgz (asc, sha512)

User (abdulkadir.altunbayrak) dizinine Developers klasörü oluşturulur

Developers klasörüne Download klasörünün altında indirilen kafka_2.12-3.5.1 uzantılı dosya açılarak Developers klasörüne kopyalanır.

Terminalden,
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro ~ % cd Developers/kafka_2.12-3.5.1 
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 %   

Klasörün içerisine gidilir.

Kafkayı ayağa kaldırmadan önce, hafta Zookeeper uygulamasına ihtiyaç duyan ne işe yara;
Apache ZooKeeper, bulut uygulamalarının son derece güvenilir dağıtılmış koordinasyonu için açık kaynaklı bir sunucudur. Apache Software Foundation'ın bir projesidir.


bin/zookeeper-server-start.sh config/zookeeper.properties 

Eğer zookeeper uygulaması ayağa kalmış ise Yeni bir terminal açarak  (Zookeeper terminalin uygulamanın ayakta olması gerekiyor) Kafka serverini ayağa kaldırıyoruz.


abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro ~ % cd Developers/kafka_2.12-3.5.1 
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % bin/kafka-server-start.sh config/server.properties

NOT: Önce zookeeper uygulaması ayağa kaldırılacak sonra Kafka uygulaması ayağa kaldırılacak.


Topik oluşturulacak (Yeni bir terminal açılacak)

abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro ~ % cd Developers/kafka_2.12-3.5.1 
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % bin/kafka-topics.sh --create --topic my-fisrt-topic --bootstrap-server localhost:9092
Created topic my-fisrt-topic.
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % 


Topik oluşup oluşmadığı kontrol etmek için
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % bin/kafka-topics.sh --describe --topic my-fisrt-topic --bootstrap-server localhost:9092
Topic: my-fisrt-topic	TopicId: cI3CSlS5SCKBPI4WIxfvNA	PartitionCount: 1	ReplicationFactor: 1	Configs: 
	Topic: my-fisrt-topic	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % 


Topice message yazmak deneme komutu

abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % bin/kafka-console-producer.sh --topic my-first-topic --bootstrap-server localhost:9092
>Merhaba kafka
>bu benim ilk mesajım
>herşey güzel olacak


Topic mesajları okumak

abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro ~ % cd Developers/kafka_2.12-3.5.1 
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % bin/kafka-console-consumer.sh --topic my-fisrt-topic --from-beginning --bootstrap-server localhost:9092

Topic listeleme 
bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

Topic silme
bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic my-fisrt-topic


ZOOKEEPER yerine KRAFT 

Random id alınacak komut

abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % bin/kafka-storage.sh random-uuid
99r9G3j9TyWYk7TsXTwSrg
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % 

KRAFT 
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % bin/kafka-storage.sh format -t 99r9G3j9TyWYk7TsXTwSrg -c config/kraft/server.properties 
Formatting /tmp/kraft-combined-logs with metadata.version 3.5-IV2.
abdulkadiraltunbayrak@Abdulkadir-MacBook-Pro kafka_2.12-3.5.1 % 


Windows 
Zookeeper'ı başlatmak için :
C:/kafka/bin/windows/zookeeper-server-start.bat C:/kafka/config/zookeeper.properties
Kafka-Server başlatmak için :
C:/kafka/bin/windows/kafka-server-start.bat C:/kafka/config/server.properties
Topic yaratmak için :
C:/kafka/bin/windows/kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic-test
--list ile oluşturduğunuz topicleri görebilirsiniz.
Producer başlatmak için :
C:/kafka/bin/windows/kafka-console-producer.bat --broker-list localhost:9092 --topic topic-test
Consumer başlatmak için :
C:/kafka/bin/windows/kafka-console-consumer.bat --bootstrap-server localho
