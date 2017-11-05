# kafka-clients-prometheus-collector

Kafka Producer Prometheus Collector implemented in Clojure.

## Usage

Easy to use with [iapetos](https://github.com/xsc/iapetos).

```$clojure
(defn kafka-producer-collector
   "Kafka Producer metrics collector.
    Can be attached to a iapetos registry using `iapetos.core/register`."
   [producer]
   (collector/named
     {:namespace "iapetos_internal"
      :name      "kafka_producer"}
     (KafkaProducerCollector. producer)))
```
## AOT and import

The class needs to be compiled before we can import it.
Add `:aot [clj_kafka.prometheus.collector.KafkaProducerCollector]` in your `project.clj`,
and `(:import (com.telenordigital.privacy KafkaProducerCollector))` in a namespace.

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
