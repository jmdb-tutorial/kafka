# kafka

Example of interacting with Kafka in clojure

Using [https://github.com/pyr/kinsky](https://github.com/pyr/kinsky)

## Prerequisites (OSX)

```
brew install kafka
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties
```

Tail a topic:

```
kafka-console-consumer --zookeeper localhost:2181 --topic t1
```

Write something to the topic

```
echo "{ \"foo\": \"hello\"}" | kafka-console-producer --broker-list localhost:9092 --topic t1
```


You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2017 FIXME
