(ns clj_kafka.prometheus.collector.KafkaProducerCollector
  (:require [franzy.clients.codec :as codec])
  (:import (franzy.clients.codec FranzCodec)
           (org.apache.kafka.clients.producer Producer)
           (io.prometheus.client GaugeMetricFamily)
           (java.util ArrayList Arrays Set))
  (:gen-class :init myconstructor
              :name clj_kafka.prometheus.collector.KafkaProducerCollector
              :state state
              :extends io.prometheus.client.Collector
              :methods [[collect [] java.util.ArrayList]]
              :constructors {[org.apache.kafka.clients.producer.Producer] []
                             [org.apache.kafka.clients.producer.Producer java.util.Set] []}))

(defn as-array [l]
  (Arrays/asList (to-array (into [] l))))

(defn replace-with-underscore [name]
  (clojure.string/replace name #"-" "_"))

(defn -myconstructor
  ([^Producer p] [[] {:producer p
                      :metric-groups-set #{"producer-metrics"}}])
  ([^Producer p ^Set metric-groups-set] [[] {:producer p
                                             :metric-groups-set metric-groups-set}]))

(defn -collect [this]
  (let [{:keys [producer metric-groups-set]} (.state this)
        metrics (codec/decode (.metrics producer))
        producer-metrics (filter (comp metric-groups-set :group first) metrics)]
    (ArrayList.
      (for [[{:keys [name description tags]} {:keys [value]}] producer-metrics]
        (let [tag-keys (map (comp replace-with-underscore clojure.core/name) (keys tags))
              gauge (GaugeMetricFamily. (str "kafka_producer_" (replace-with-underscore name))
                                        description
                                        (as-array tag-keys))]
          (.addMetric gauge (as-array (vals tags)) (or value 0.0)))))))