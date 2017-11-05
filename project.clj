(defproject psalaberria002/clj-kafka-prometheus-collector "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-kafka.franzy/core "2.0.7" :exclusions [prismatic/schema org.apache.kafka/kafka-clients]]
                 [org.apache.kafka/kafka-clients "0.11.0.0"]
                 [io.prometheus/simpleclient "0.1.0"]])
