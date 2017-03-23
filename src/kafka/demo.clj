(ns kafka.demo
  (:require [kinsky.client      :as client]
            [kinsky.async       :as async]
            [clojure.core.async :as a :refer [go <!! <! >! go-loop >!! timeout close! chan alts!!]])
  (:gen-class))

;; http://www.braveclojure.com/core-async/
;; https://github.com/clojure/core.async/wiki/Getting-Started
;; https://github.com/clojure/core.async/blob/master/examples/walkthrough.clj

;; Some simple examples:
(defn simple-example []
  (let [ch (chan 1)]
    (go ())))


(defn run-consumer []
  (println "Registering a consumer to a topic")
  (go-loop ))

;;http://kafka.apache.org/082/javadoc/org/apache/kafka/clients/producer/ProducerRecord.html#key()
(defn push-message []
  (println "Pushing a message to the account topic")
  (let [p (client/producer {:bootstrap.servers "localhost:9092"}
                         (client/keyword-serializer)
                         (client/edn-serializer))]
    (client/send! p "account" :account-a {:action :login})))

(defn debug-push-response [response]
  (let [data  {:done?  (.isDone response)
               :cancelled? (.isCancelled response)
               :relativeOffset (.relativeOffset response)}
        md (.get response)
        meta-data {:offset (.offset md)
                   :partition (.partition md)
                   :topic (.topic md)}]
    (println data)
    (println meta-data)))

(defn -main [& args]
  (println "This is a kafka demonstration...")
  (run-consumer)
  (debug-push-response (push-message)))
